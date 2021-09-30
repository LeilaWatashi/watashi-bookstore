package com.watashi.bookstore.strategy.cartao_credito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.watashi.bookstore.domain.CartaoCredito;
import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.strategy.IStrategy;
import com.watashi.bookstore.util.Util;
import com.watashi.bookstore.util.validador.ValidadorString;

import java.time.LocalDate;

@Component
public class ValidaDataValidadeCartao implements IStrategy {

    @Autowired
    ValidadorString validadorString;

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if(entidade instanceof CartaoCredito){

            CartaoCredito cartaoCredito = (CartaoCredito) entidade;

            if(Util.isNotNull(cartaoCredito.getVencimentoAno())
                && Util.isNotNull(cartaoCredito.getVencimentoMes())){

                Integer mesVencimento = Integer.parseInt(cartaoCredito.getVencimentoMes());
                Integer anoVencimento = Integer.parseInt(cartaoCredito.getVencimentoAno());

                Integer anoAtual = LocalDate.now().getYear();
                Integer mesAtual = LocalDate.now().getMonth().getValue();

                if(anoVencimento < anoAtual || (anoVencimento.equals(anoAtual) && mesVencimento < mesAtual)){
                    msg.append("Cartão está vencido.");
                }
            }
        }

        return msg.toString();
    }
}
