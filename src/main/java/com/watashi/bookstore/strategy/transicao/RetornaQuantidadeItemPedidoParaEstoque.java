package com.watashi.bookstore.strategy.transicao;

import com.watashi.bookstore.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.watashi.bookstore.domain.*;
import com.watashi.bookstore.util.Util;
import com.watashi.bookstore.util.validador.ValidadorString;

@Component
public class RetornaQuantidadeItemPedidoParaEstoque implements IStrategy {

	@Autowired
	ValidadorString validadorString;

	@Override
	public String processar(final EntidadeDominio entidade) {

		StringBuilder msg = new StringBuilder();

		if(entidade instanceof Transicao){

			Transicao transicao = (Transicao) entidade;
			
			Pedido pedido = transicao.getPedido();
			
			if(Util.isNotNull(pedido)
				&& Util.isNotNull(pedido.getStatusPedido())
				&& Util.isNotNull(pedido.getStatusPedido().getId())
				&& (pedido.getStatusPedido().getId().equals(10) || pedido.getStatusPedido().getId().equals(13))) {
				
				transicao.getItemTransicaoList().forEach(itemTransacao -> {
				
					Item item = itemTransacao.getItem();

					Livro livro = item.getLivro();

					livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() + itemTransacao.getQuantidade());
					livro.setQuantidadeEstoque(livro.getQuantidadeEstoque() + itemTransacao.getQuantidade());

					if(livro.getStatus().getId().equals(2)){
						livro.setStatus(Status.builder().id(1).build());
					}
				});
			}
		}

		return msg.toString();
	}
}
