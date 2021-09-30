package com.watashi.bookstore.strategy.carrinho;

import com.watashi.bookstore.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.watashi.bookstore.domain.Carrinho;
import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.repository.CarrinhoRepository;
import com.watashi.bookstore.util.Util;

@Component
public class PegaCarrinhoSeExistir implements IStrategy {

    @Autowired
    CarrinhoRepository carrinhoRepository;

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if(entidade instanceof Carrinho){

            Carrinho carrinho = (Carrinho) entidade;

            if(carrinho.getPessoa() != null
                    && carrinho.getPessoa().getId() != null){

                Carrinho carrinhoResultado = carrinhoRepository.findByPessoa_Id(carrinho.getPessoa().getId());

                if(Util.isNotNull(carrinhoResultado)){

                    carrinho.setId(carrinhoResultado.getId());
                    carrinho.getItemList().addAll(carrinhoResultado.getItemList());
                }

            }

        }

        return msg.toString();
    }
}
