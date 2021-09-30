package com.watashi.bookstore.strategy.livro;

import com.watashi.bookstore.strategy.IStrategy;
import org.springframework.stereotype.Component;

import com.watashi.bookstore.domain.Livro;
import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.util.Util;

@Component
public class InseriItemDisponivelParaEstoque implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if(entidade instanceof Livro){
            Livro livro = (Livro) entidade;
            if(Util.isNotNull(livro) && Util.isNotNull(livro.getQuantidadeDisponivel())){
                livro.setQuantidadeEstoque(livro.getQuantidadeDisponivel());
            }
        }

        return null;
    }
}
