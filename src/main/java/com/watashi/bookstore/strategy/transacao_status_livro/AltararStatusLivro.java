package com.watashi.bookstore.strategy.transacao_status_livro;

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
            TransacaoStatusLivro transacaoStatusLivro = (TransacaoStatusLivro) entidade;
            if(Util.isNotNull(transacaoStatusLivro.getLivro())){
                Livro livro = transacaoStatusLivro.getLivro();
                Status status;
                if(livro.getStatus().getId() == 1){
                    status = Status.builder().id(2).build();
                } else {
                    status = Status.builder().id(1).build();
                }
                livro.setStatus(status);
                transacaoStatusLivro.setStatus(status);
            }
        }

        return null;
    }
}
