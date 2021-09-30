package com.watashi.bookstore.strategy.devolucao;

import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.domain.Transicao;
import com.watashi.bookstore.strategy.IStrategy;
import com.watashi.bookstore.strategy.email.devolucao.EnviaEmailDevolucaoCodigoRastreio;
import com.watashi.bookstore.strategy.email.devolucao.EnviaEmailDevolucaoRecusada;
import com.watashi.bookstore.strategy.email.devolucao.EnviaEmailSolicitacaoDevolucao;
import com.watashi.bookstore.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EnviaEmailStatusDaDevolucao implements IStrategy {

	@Autowired
	private EnviaEmailSolicitacaoDevolucao enviaEmailSolicitacaoDevolucao;

	@Autowired
	private EnviaEmailDevolucaoRecusada enviaEmailDevolucaoRecusada;

	@Autowired
	private EnviaEmailDevolucaoCodigoRastreio enviaEmailDevolucaoCodigoRastreio;

	@Override
	public String processar(final EntidadeDominio entidade) {

		if(entidade instanceof Transicao){

			Transicao transicao = (Transicao) entidade;

			if(transicao.getPedido() != null
				&& transicao.getPedido().getStatusPedido() != null
				&& transicao.getPedido().getStatusPedido().getId() != null
				&& Util.isNotNull(transicao.getTipoTransicao())
				&& transicao.getTipoTransicao().getId().equals(2)){

				Map<Integer, IStrategy> mapaEnvioDeEmail = new HashMap<>();

				mapaEnvioDeEmail.put(11, enviaEmailSolicitacaoDevolucao);
				mapaEnvioDeEmail.put(12, enviaEmailDevolucaoRecusada);
				mapaEnvioDeEmail.put(9, enviaEmailDevolucaoCodigoRastreio);

				Integer statusPedidoId = transicao.getPedido().getStatusPedido().getId();

				if(mapaEnvioDeEmail.containsKey(statusPedidoId)){
					mapaEnvioDeEmail.get(statusPedidoId).processar(transicao);
				}
			}
		}

		return null;
	}
}
