package com.watashi.bookstore.strategy.troca;

import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.domain.Transicao;
import com.watashi.bookstore.strategy.IStrategy;
import com.watashi.bookstore.strategy.email.troca.EnviaEmailSolicitacaoTroca;
import com.watashi.bookstore.strategy.email.troca.EnviaEmailTrocaCodigoRastreio;
import com.watashi.bookstore.strategy.email.troca.EnviaEmailTrocaRecusada;
import com.watashi.bookstore.strategy.transicao.GeraCodigoRastreioTransicao;
import com.watashi.bookstore.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EnviaEmailStatusDaTroca implements IStrategy {

	@Autowired
	private ValidaDadosTroca validaDadosTroca;

	@Autowired
	private EnviaEmailSolicitacaoTroca enviaEmailSolicitacaoTroca;

	@Autowired
	private EnviaEmailTrocaCodigoRastreio enviaEmailTrocaCodigoRastreio;

	@Autowired
	private EnviaEmailTrocaRecusada enviaEmailTrocaRecusada;

	@Override
	public String processar(final EntidadeDominio entidade) {

		if(entidade instanceof Transicao){

			Transicao transicao = (Transicao) entidade;

			if(transicao.getPedido() != null
				&& transicao.getPedido().getStatusPedido() != null
				&& transicao.getPedido().getStatusPedido().getId() != null
				&& Util.isNotNull(transicao.getTipoTransicao())
				&& transicao.getTipoTransicao().getId().equals(1)){

				Map<Integer, IStrategy> mapaEnvioDeEmail = new HashMap<>();

				mapaEnvioDeEmail.put(7, enviaEmailSolicitacaoTroca);
				mapaEnvioDeEmail.put(8, enviaEmailTrocaRecusada);
				mapaEnvioDeEmail.put(9, enviaEmailTrocaCodigoRastreio);

				Integer statusPedidoId = transicao.getPedido().getStatusPedido().getId();

				String msg = validaDadosTroca.processar(transicao);

				if(mapaEnvioDeEmail.containsKey(statusPedidoId)){
					mapaEnvioDeEmail.get(statusPedidoId).processar(transicao);
				}
			}
		}

		return null;
	}
}
