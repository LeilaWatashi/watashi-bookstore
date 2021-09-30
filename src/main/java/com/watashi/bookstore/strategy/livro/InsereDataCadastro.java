package com.watashi.bookstore.strategy.livro;

import com.watashi.bookstore.domain.Livro;
import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.strategy.IStrategy;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class InsereDataCadastro implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {
        if(entidade instanceof Livro){
            Livro livro = (Livro) entidade;
            livro.setDataCadastro(LocalDate.now());
        }
        return null;
    }
}
