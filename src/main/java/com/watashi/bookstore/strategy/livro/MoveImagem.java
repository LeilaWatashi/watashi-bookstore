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
    LivroRepository cartaRepository;

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if (entidade instanceof Livro){

            Livro carta = (Livro) entidade;

            if(carta.getImagemPath() != null
                && !carta.getImagemPath().contains("/assets/images/cartas/")){

                carta.setImagemPath("/assets/images/cartas/" + carta.getImagemPath());
            }
        }
        return msg.toString();
    }
}
