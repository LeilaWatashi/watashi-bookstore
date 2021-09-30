package com.watashi.bookstore.strategy.pedido;

import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.domain.Item;
import com.watashi.bookstore.domain.Pedido;
import com.watashi.bookstore.repository.LivroRepository;
import com.watashi.bookstore.strategy.IStrategy;
import com.watashi.bookstore.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class AtualizaItensPedidos implements IStrategy {

    @Autowired
    private LivroRepository livroRepository;

    @Override
    public String processar(EntidadeDominio entidade) {

        if(entidade instanceof Pedido){

            Pedido pedido = (Pedido) entidade;

            if(Util.isNotNull(pedido.getItemList())){
                Set<Item> itemList = pedido.getItemList();
                for (Item item : itemList) {
                    if(Util.isNotNull(item.getId()))
                    item.setLivro(livroRepository.findById(item.getLivro().getId()).get());
                }
            }

        }

        return null;
    }
}
