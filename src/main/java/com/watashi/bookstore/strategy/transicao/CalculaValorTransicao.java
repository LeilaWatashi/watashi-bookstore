package com.watashi.bookstore.strategy.transicao;

import com.watashi.bookstore.domain.*;
import com.watashi.bookstore.repository.ItemRepository;
import com.watashi.bookstore.strategy.IStrategy;
import com.watashi.bookstore.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CalculaValorTransicao implements IStrategy {

	@Autowired
	private ItemRepository itemRepository;

	@Override
	public String processar(final EntidadeDominio entidade) {
		if(entidade instanceof Transicao){
			Transicao transicao = (Transicao) entidade;
			if(Util.isNotNull(transicao.getItemTransicaoList())){
				Double subTotal = 0.0;
				for (ItemTransacao itemTransacao : transicao.getItemTransicaoList()) {
					if(Util.isNotNull(itemTransacao.getItem().getId())){
						Item item = itemRepository.findById(itemTransacao.getItem().getId()).get();
						Livro livro = item.getLivro();
						Double precificacao = item.getLivro().getGrupoPrecificacao().getValor();
						subTotal += (livro.getValorCompra() + (livro.getValorCompra() * precificacao)) * itemTransacao.getQuantidade();
					}
				}
				transicao.setSubTotal(subTotal);
			}
		}
		return null;
	}
}
