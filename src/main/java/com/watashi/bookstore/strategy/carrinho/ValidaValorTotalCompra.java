package com.watashi.bookstore.strategy.carrinho;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.watashi.bookstore.domain.Carrinho;
import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.strategy.IStrategy;
import com.watashi.bookstore.repository.LivroRepository;

@Component
public class ValidaValorTotalCompra implements IStrategy {

    @Autowired
    LivroRepository livroRepository;

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if(entidade instanceof Carrinho){

            Carrinho carrinho = (Carrinho) entidade;

            if(!carrinho.getItemList().isEmpty()){
                carrinho.getItemList().forEach( item -> {
                    if (livroRepository.findById(item.getLivro().getId()).get()
                            .getStatus().getId() == 2) {
                         msg.append("Produto " + item.getLivro().getNome() + " está inativo no momento.");
                    }
                });
            }else{
                msg.append("Carrinho está vazio");
            }
        }

        return msg.toString();
    }
}
