package com.watashi.bookstore.strategy.livro;

import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.domain.Livro;
import com.watashi.bookstore.strategy.IStrategy;
import org.springframework.stereotype.Component;

@Component
public class ValidaQuantidadeEstoque implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if(entidade instanceof Livro){
            Livro carta = (Livro) entidade;
            if(carta.getQuantidadeEstoque() < 0){
                msg.append("Valor n�o pode ser maior que o est� em estoque atualmente");
            }
        }

        return msg.toString();
    }
}
