package com.watashi.bookstore.strategy.pedido;

import com.watashi.bookstore.dao.CupomDAO;
import com.watashi.bookstore.domain.*;
import com.watashi.bookstore.strategy.IStrategy;
import com.watashi.bookstore.strategy.cupom.GeraCodigoCupom;
import com.watashi.bookstore.strategy.email.pedido.EnviaEmailCupomComValorRestante;
import com.watashi.bookstore.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class GeraCupomComValorRestante implements IStrategy {

    @Autowired
    private CupomDAO cupomDAO;

    @Autowired
    private GeraCodigoCupom geraCodigoCupom;

    @Autowired
    private EnviaEmailCupomComValorRestante enviaEmailCupomComValorRestante;

    @Override
    public String processar(EntidadeDominio entidade) {
        if(entidade instanceof Pedido){
            Pedido pedido = (Pedido) entidade;
            if(Util.isNotNull(pedido.getFormaPagamentoList())){
                for (FormaPagamento formaPagamento : pedido.getFormaPagamentoList()) {
                    if(Util.isNotNull(formaPagamento.getCupom())){
                        if(formaPagamento.getCupom().getValor() > pedido.getValorTotal()){
                            Cupom cupom = Cupom.builder()
                                    .valor(formaPagamento.getCupom().getValor() - pedido.getValorTotal())
                                    .pessoa(pedido.getCliente())
                                    .status(Status.builder().id(1).build())
                                    .tipoCupom(formaPagamento.getCupom().getTipoCupom())
                                    .dataCriacao(LocalDate.now())
                                    .build();
                            geraCodigoCupom.processar(cupom);
                            cupomDAO.salvar(cupom);
                            enviaEmailCupomComValorRestante.processar(cupom);
                        }
                    }
                }
            }
        }
        return null;
    }
}
