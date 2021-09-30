package com.watashi.bookstore.strategy.livro;

import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.domain.Livro;
import com.watashi.bookstore.domain.Status;
import com.watashi.bookstore.strategy.IStrategy;
import org.springframework.stereotype.Component;

@Component
public class InativaLivroNaoDisponivel implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {
        if(entidade instanceof Livro){
            Livro livro = (Livro) entidade;
            if(livro.getQuantidadeDisponivel() <= 0){
                livro.setStatus(Status.builder().id(2).build());
            }
        }
        return null;
    }
}
