package com.watashi.bookstore.strategy.transacao_status_carta;

import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.domain.Livro;
import com.watashi.bookstore.domain.Status;
import com.watashi.bookstore.domain.TransacaoStatusLivro;
import com.watashi.bookstore.strategy.IStrategy;
import com.watashi.bookstore.util.Util;
import org.springframework.stereotype.Component;

@Component
public class AltararStatusLivro implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {

        if(entidade instanceof TransacaoStatusLivro){
            TransacaoStatusLivro transacaoStatusCarta = (TransacaoStatusLivro) entidade;
            if(Util.isNotNull(transacaoStatusCarta.getLivro())){
                Livro livro = transacaoStatusCarta.getLivro();
                Status status;
                if(livro.getStatus().getId() == 1){
                    status = Status.builder().id(2).build();
                } else {
                    status = Status.builder().id(1).build();
                }
                livro.setStatus(status);
                transacaoStatusCarta.setStatus(status);
            }
        }

        return null;
    }
}
