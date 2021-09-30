package com.watashi.bookstore.strategy.cupom;

import com.watashi.bookstore.domain.Cupom;
import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.repository.CupomRepository;
import com.watashi.bookstore.strategy.IStrategy;
import com.watashi.bookstore.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaCupomAtivo implements IStrategy {

    @Autowired
    private CupomRepository cupomRepository;

    @Override
    public String processar(EntidadeDominio entidade) {
        StringBuilder msg = new StringBuilder();
        if(entidade instanceof Cupom){
            Cupom cupom = (Cupom) entidade;
            if(Util.isNotNull(cupom.getCodigo())){
                cupom = cupomRepository.findCupomByCodigo(((Cupom) entidade).getCodigo());
                if(Util.isNotNull(cupom) && cupom.getStatus().getId() == 2){
                    msg.append("Cupom inativo.");
                }
            }
        }
        return msg.toString();
    }
}
