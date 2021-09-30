package com.watashi.bookstore.strategy.carrinho;

import com.watashi.bookstore.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.watashi.bookstore.domain.*;
import com.watashi.bookstore.repository.CarrinhoRepository;
import com.watashi.bookstore.repository.ItemRepository;
import com.watashi.bookstore.util.Util;

import java.util.List;

@Component
public class RetiraItemDisponivel implements IStrategy {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Override
    public String processar(EntidadeDominio entidade) {

        if(entidade instanceof Carrinho){

            List<Item> itemList = ((Carrinho) entidade).getItemList();

            for(Item item : itemList){

                Integer diferencaDeQuantidadeItemCarrinho;
                Integer quantidadeItemDisponivel;

                if(item.getId() != null){
                    Item itemNoCarrinho = itemRepository.findById(item.getId()).get();
                    if(Util.isNotNull(itemNoCarrinho)){
                        diferencaDeQuantidadeItemCarrinho = item.getQuantidade() - itemNoCarrinho.getQuantidade();
                        quantidadeItemDisponivel = itemNoCarrinho.getLivro().getQuantidadeDisponivel();
                    } else {
                        diferencaDeQuantidadeItemCarrinho = item.getQuantidade();
                        quantidadeItemDisponivel = item.getLivro().getQuantidadeDisponivel();
                    }
                } else {
                    diferencaDeQuantidadeItemCarrinho = item.getQuantidade();
                    quantidadeItemDisponivel = item.getLivro().getQuantidadeDisponivel();
                }


                item.getLivro().setQuantidadeDisponivel(quantidadeItemDisponivel - diferencaDeQuantidadeItemCarrinho);

                if(item.getLivro().getQuantidadeDisponivel() == 0){
                    item.getLivro().setStatus(Status.builder().id(2).build());
                } else {
                    item.getLivro().setStatus(Status.builder().id(1).build());
                }
            }
        }

        return null;
    }
}
