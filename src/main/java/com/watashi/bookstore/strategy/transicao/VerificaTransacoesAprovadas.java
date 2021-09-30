package com.watashi.bookstore.strategy.transicao;

import java.util.Set;

import com.watashi.bookstore.dao.PedidoDAO;
import com.watashi.bookstore.dao.TransacaoDAO;
import com.watashi.bookstore.strategy.IStrategy;
import com.watashi.bookstore.strategy.cupom.GeraCodigoCupom;
import com.watashi.bookstore.strategy.email.devolucao.EnviaEmailDevolucaoAprovadaComCupom;
import com.watashi.bookstore.strategy.email.troca.EnviaEmailTrocaAprovadaComCupom;
import com.watashi.bookstore.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.watashi.bookstore.domain.*;
import com.watashi.bookstore.dao.CupomDAO;
import com.watashi.bookstore.repository.TransicaoRepository;

@Component
public class VerificaTransacoesAprovadas implements IStrategy {

	@Autowired
	private TransicaoRepository transicaoRepository;

	@Autowired
	private TransacaoDAO transacaoDAO;

	@Autowired
	private PedidoDAO pedidoDAO;

	@Autowired
	private CupomDAO cupomDAO;

	@Autowired
	private GeraCodigoCupom geraCodigoCupom;

	@Autowired
	private EnviaEmailTrocaAprovadaComCupom enviaEmailTrocaAprovadaComCupom;

	@Autowired
	private EnviaEmailDevolucaoAprovadaComCupom enviaEmailDevolucaoAprovadaComCupom;

	@Override
	public String processar(final EntidadeDominio entidade) {

		StringBuilder msg = new StringBuilder();

		if(entidade instanceof Transicao){

			Set<Transicao> trocasAprovadas = transicaoRepository.getTransacoesAprovadas();

			for (Transicao transicaoAprovada : trocasAprovadas) {
				if(Util.isNull(transicaoAprovada.getCupom())){
					Cupom cupom = Cupom.builder()
							.valor(transicaoAprovada.getSubTotal())
							.pessoa(transicaoAprovada.getPedido().getCliente())
							.build();
					geraCodigoCupom.processar(cupom);

					if(transicaoAprovada.getTipoTransicao().getId() == 1){
						cupom.setTipoCupom(TipoCupom.builder().id(1).build());
					} else if(transicaoAprovada.getTipoTransicao().getId() == 2) {
						cupom.setTipoCupom(TipoCupom.builder().id(2).build());
					}

					transicaoAprovada.setCupom((Cupom) cupomDAO.salvar(cupom));

					if(transicaoAprovada.getTipoTransicao().getId() == 1){
						enviaEmailTrocaAprovadaComCupom.processar(transicaoAprovada);
					} else if(transicaoAprovada.getTipoTransicao().getId() == 2){
						enviaEmailDevolucaoAprovadaComCupom.processar(transicaoAprovada);
					}

					transicaoAprovada.getPedido().setStatusPedido(StatusPedido.builder().id(6).build());
					pedidoDAO.alterar(transicaoAprovada.getPedido());
					transicaoAprovada.setStatusTransacao(StatusTransacao.builder().id(2).build());
					transacaoDAO.alterar(transicaoAprovada);
				}
			}
		}

		return msg.toString();
	}
}
