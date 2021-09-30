package com.watashi.bookstore.strategy.carrinho;

import com.watashi.bookstore.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.watashi.bookstore.domain.Carrinho;
import com.watashi.bookstore.domain.Livro;
import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.domain.Item;
import com.watashi.bookstore.repository.CarrinhoRepository;
import com.watashi.bookstore.repository.LivroRepository;
import com.watashi.bookstore.util.Util;

import java.util.List;

@Component
public class ValidaQuantidadeItemDisponivel implements IStrategy {

    @Autowired
    CarrinhoRepository carrinhoRepository;

    @Autowired
    LivroRepository livroRepository;

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if(entidade instanceof Carrinho || entidade instanceof Item){

            if(entidade instanceof Carrinho){
                List<Item> itemList = ((Carrinho) entidade).getItemList();

                if(Util.isNotNull(itemList)){

                    itemList.forEach( item -> {

                        Livro livro = livroRepository.findById(item.getLivro().getId()).get();

                        if(item.getQuantidade() > livro.getQuantidadeEstoque())
                            msg.append("Quantidade pedido não está disponível no estoque.");

                        if(item.getQuantidade() < 1)
                            msg.append("Quantidade pedido não é válida para incluir no carrinho.");

                    });
                }
            }

            if(entidade instanceof Item) {

                Item item = (Item) entidade;

                Livro livro = livroRepository.findById(item.getLivro().getId()).get();

                if(item.getQuantidade() > livro.getQuantidadeEstoque())
                    msg.append("Quantidade pedido não está disponível no estoque.");
            }

        }
        return msg.toString();
    }
}
