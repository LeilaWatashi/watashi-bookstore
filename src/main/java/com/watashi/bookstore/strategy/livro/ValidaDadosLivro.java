package com.watashi.bookstore.strategy.livro;

import com.watashi.bookstore.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.watashi.bookstore.domain.Livro;
import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.util.validador.DoubleValidador;
import com.watashi.bookstore.util.validador.ValidadorString;

@Component
public class ValidaDadosLivro implements IStrategy {

    @Autowired
    ValidadorString validadorString;

    @Autowired
    DoubleValidador doubleValidador;

    @Override
    public String processar(EntidadeDominio entidade) {
        StringBuilder msg = new StringBuilder();
        if (entidade instanceof Livro){
            Livro livro = (Livro) entidade;
            msg.append(validadorString.validar(livro.getNome(), "nome"));
            msg.append(validadorString.validar(livro.getDescricao(), "descrição"));
            msg.append(doubleValidador.validar(livro.getValorCompra(), "valor do produto"));
            msg.append(doubleValidador.validar(livro.getGrupoPrecificacao(), "valor de precificação"));
            msg.append(validadorString.validar(livro.getImagemPath(), "imagem"));
            msg.append(validadorString.validar(livro.getCategoriaLivro().getId(), "categoria"));
        }
        return msg.toString();
    }
}
