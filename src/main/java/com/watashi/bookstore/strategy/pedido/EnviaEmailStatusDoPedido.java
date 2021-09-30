package com.watashi.bookstore.strategy.pedido;

import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.domain.Pedido;
import com.watashi.bookstore.strategy.IStrategy;
import com.watashi.bookstore.strategy.email.pedido.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EnviaEmailStatusDoPedido implements IStrategy {

	@Autowired
	private GeraCodigoRastreioPedido geraCodigoRastreioPedido;

	@Autowired
	private EnviaEmailPedidoEntregue enviaEmailPedidoEntregue;

	@Autowired
	private EnviaEmailPedidoPendentePagamento enviaEmailPedidoPendentePagamento;

	@Autowired
	private EnviaEmailPedidoRecusada enviaEmailPedidoRecusada;

	@Override
	public String processar(final EntidadeDominio entidade) {

		if(entidade instanceof Pedido){

			Pedido pedido = (Pedido) entidade;

			if(pedido.getStatusPedido() != null
				&& pedido.getStatusPedido().getId() != null){

				Map<Integer, IStrategy> mapaEnvioDeEmail = new HashMap<>();

				mapaEnvioDeEmail.put(1, enviaEmailPedidoPendentePagamento);
				mapaEnvioDeEmail.put(4, enviaEmailPedidoRecusada);
				mapaEnvioDeEmail.put(5, geraCodigoRastreioPedido);
				mapaEnvioDeEmail.put(6, enviaEmailPedidoEntregue);

				Integer statusPedidoId = pedido.getStatusPedido().getId();

				if(mapaEnvioDeEmail.containsKey(statusPedidoId)){
					mapaEnvioDeEmail.get(statusPedidoId).processar(pedido);
				}
			}
		}

		return null;
	}
}
