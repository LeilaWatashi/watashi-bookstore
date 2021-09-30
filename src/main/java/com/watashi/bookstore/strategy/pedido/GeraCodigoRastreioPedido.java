package com.watashi.bookstore.strategy.pedido;

import com.watashi.bookstore.dao.RastreioDAO;
import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.domain.Pedido;
import com.watashi.bookstore.domain.Rastreio;
import com.watashi.bookstore.strategy.IStrategy;
import com.watashi.bookstore.strategy.email.pedido.EnviaEmailPedidoCodigoRastreio;
import com.watashi.bookstore.util.GeradorCodigo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GeraCodigoRastreioPedido implements IStrategy {

	@Autowired
	private RastreioDAO rastreioDAO;

	@Autowired
	private EnviaEmailPedidoCodigoRastreio enviaEmailPedidoCodigoRastreio;

	@Override
	public String processar(final EntidadeDominio entidade) {

		if(entidade instanceof Pedido){

			Pedido pedido = (Pedido) entidade;

			if(pedido.getStatusPedido() != null
				&& pedido.getStatusPedido().getId().equals(5)){

				Rastreio rastreio = new Rastreio();

				rastreio.setCodigoRastreio(GeradorCodigo.gerarCodigoRastreio());
				rastreio = (Rastreio) rastreioDAO.salvar(rastreio);
				pedido.setRastreio(rastreio);
				enviaEmailPedidoCodigoRastreio.processar(pedido);
			}
		}

		return null;
	}
}
