package com.watashi.bookstore.strategy.pedido;

import com.watashi.bookstore.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.watashi.bookstore.domain.*;
import com.watashi.bookstore.repository.CarrinhoRepository;
import com.watashi.bookstore.repository.ItemRepository;
import com.watashi.bookstore.util.Util;

import java.util.Set;

@Component
public class RetiraItemEstoque implements IStrategy {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Override
    public String processar(EntidadeDominio entidade) {

        if(entidade instanceof Pedido){

            Set<Item> itemList = ((Pedido) entidade).getItemList();

            for(Item item : itemList){

                if(item.getId() != null){
                    Item itemNoCarrinho = itemRepository.findById(item.getId()).get();
                    if(Util.isNotNull(itemNoCarrinho)){
                        item.getLivro().setQuantidadeEstoque(item.getLivro().getQuantidadeEstoque() - item.getQuantidade());
                    }
                }
            }
        }

        return null;
    }
}
