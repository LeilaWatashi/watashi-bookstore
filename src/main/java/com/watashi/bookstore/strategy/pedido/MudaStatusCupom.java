package com.watashi.bookstore.strategy.pedido;

import com.watashi.bookstore.domain.*;
import com.watashi.bookstore.repository.CupomRepository;
import com.watashi.bookstore.strategy.IStrategy;
import com.watashi.bookstore.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MudaStatusCupom implements IStrategy {

    @Autowired
    private CupomRepository cupomRepository;

    @Override
    public String processar(EntidadeDominio entidade) {
        if(entidade instanceof Pedido){
            Pedido pedido = (Pedido) entidade;
            if(Util.isNotNull(pedido.getFormaPagamentoList())){
                for (FormaPagamento formaPagamento : pedido.getFormaPagamentoList()) {
                    if(Util.isNotNull(formaPagamento.getCupom())){
                        Cupom cupom = cupomRepository.findCupomByCodigo(formaPagamento.getCupom().getCodigo());
                        formaPagamento.setCupom(cupom);
                        formaPagamento.getCupom().setStatus(Status.builder().id(2).build());
                    }
                }
            }
        }
        return null;
    }
}
