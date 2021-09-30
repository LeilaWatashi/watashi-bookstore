package com.watashi.bookstore.strategy.transicao;

import com.watashi.bookstore.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.domain.Item;
import com.watashi.bookstore.domain.Pedido;
import com.watashi.bookstore.domain.Transicao;
import com.watashi.bookstore.util.Util;
import com.watashi.bookstore.util.validador.ValidadorString;

@Component
public class RetiraQuantidadeItemDoPedido implements IStrategy {

	@Autowired
	ValidadorString validadorString;

	@Override
	public String processar(final EntidadeDominio entidade) {

		if(entidade instanceof Transicao){

			Transicao transicao = (Transicao) entidade;
			
			Pedido pedido = transicao.getPedido();
			
			if(Util.isNotNull(pedido)
				&& Util.isNotNull(pedido.getStatusPedido())
				&& Util.isNotNull(pedido.getStatusPedido().getId())
				&& (pedido.getStatusPedido().getId().equals(7) || pedido.getStatusPedido().getId().equals(11))) {
				
				transicao.getItemTransicaoList().forEach(itemTransacao -> {
				
					Item item = itemTransacao.getItem();
	
					Integer quantidadeAtual = item.getQuantidade();
	
					Integer quantidadeEstorno = itemTransacao.getQuantidade();
	
					item.setQuantidade(quantidadeAtual - quantidadeEstorno);

					if(transicao.getTipoTransicao().getId().equals(1)){
						item.setQuantidadeTroca(item.getQuantidadeTroca() + quantidadeEstorno);
					}
					if(transicao.getTipoTransicao().getId().equals(2)){
						item.setQuantidadeDevolucao(item.getQuantidadeDevolucao() + quantidadeEstorno);
					}
				});
			}
		}
		return null;
	}
}
