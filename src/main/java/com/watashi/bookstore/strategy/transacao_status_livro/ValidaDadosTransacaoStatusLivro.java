package com.watashi.bookstore.strategy.transacao_status_livro;

import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.domain.TransacaoStatusLivro;
import com.watashi.bookstore.strategy.IStrategy;
import com.watashi.bookstore.util.validador.ValidadorString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaDadosTransacaoStatusLivro implements IStrategy {

    @Autowired
    ValidadorString validadorString;

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if(entidade instanceof TransacaoStatusLivro){
            TransacaoStatusLivro transacaoStatusLivro = (TransacaoStatusLivro) entidade;
            validadorString.validar(transacaoStatusLivro.getLivro(), "livro");
            validadorString.validar(transacaoStatusLivro.getMotivo(), "motivo");
        }
        return msg.toString();
    }
}
