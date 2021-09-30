package com.watashi.bookstore.strategy.carrinho;

import com.watashi.bookstore.repository.LivroRepository;
import com.watashi.bookstore.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.watashi.bookstore.domain.EntidadeDominio;

@Component
public class ValidaDadosCarrinho implements IStrategy {

    @Autowired
    LivroRepository cartaRepository;

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

//        if(entidade instanceof Carrinho){
//
//            Carrinho carrinho = (Carrinho) entidade;
//
//            if(!carrinho.getItemList().isEmpty()){
//                carrinho.getItemList().forEach( item -> {
//                    if (cartaRepository.findById(item.getCarta().getId()).get()
//                            .getStatus().getId() == 2) {
//                         msg.append("Produto " + item.getCarta().getNome() + " está inativo no momento.");
//                    }
//                });
//            }else{
//                msg.append("Carrinho está vazio");
//            }
//        }

        return msg.toString();
    }
}
