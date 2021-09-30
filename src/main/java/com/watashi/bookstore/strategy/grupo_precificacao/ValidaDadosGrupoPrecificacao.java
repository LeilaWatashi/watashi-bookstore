package com.watashi.bookstore.strategy.grupo_precificacao;

import com.watashi.bookstore.domain.Endereco;
import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.domain.GrupoPrecificacao;
import com.watashi.bookstore.strategy.IStrategy;
import com.watashi.bookstore.util.Util;
import com.watashi.bookstore.util.validador.ValidadorString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaDadosGrupoPrecificacao implements IStrategy {

    @Autowired
    ValidadorString validadorString;

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if(entidade instanceof GrupoPrecificacao){
            GrupoPrecificacao grupoPrecificacao = (GrupoPrecificacao) entidade;
            if (Util.isNotNull(grupoPrecificacao.getValor())) {
                msg.append("Campo valor da precifica��o");
            }
        }
        return msg.toString();
    }
}
