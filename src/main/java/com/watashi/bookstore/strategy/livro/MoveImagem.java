package com.watashi.bookstore.strategy.livro;

import com.watashi.bookstore.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.watashi.bookstore.domain.Livro;
import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.repository.LivroRepository;
import com.watashi.bookstore.util.validador.ValidadorString;

@Component
public class MoveImagem implements IStrategy {

    @Autowired
    ValidadorString validadorString;

    @Autowired
    LivroRepository livroRepository;

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if (entidade instanceof Livro){

            Livro livro = (Livro) entidade;

            if(livro.getImagemPath() != null
                && !livro.getImagemPath().contains("/assets/imagens/livros/")){

                livro.setImagemPath("/assets/imagens/livros/" + livro.getImagemPath());
            }
        }
        return msg.toString();
    }
}
