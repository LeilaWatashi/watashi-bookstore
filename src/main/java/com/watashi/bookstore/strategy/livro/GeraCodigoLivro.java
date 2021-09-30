package com.watashi.bookstore.strategy.livro;

import com.watashi.bookstore.domain.Livro;
import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.strategy.IStrategy;
import com.watashi.bookstore.util.GeradorCodigo;
import org.springframework.stereotype.Component;

@Component
public class GeraCodigoLivro implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {
        if(entidade instanceof Livro){
            Livro carta = (Livro) entidade;
            carta.setCodigo(GeradorCodigo.gerarCodigoCarta());
        }
        return null;
    }
}
