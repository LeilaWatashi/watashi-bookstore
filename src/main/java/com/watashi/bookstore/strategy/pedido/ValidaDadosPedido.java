package com.watashi.bookstore.strategy.pedido;

import com.watashi.bookstore.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.domain.Pedido;
import com.watashi.bookstore.util.Util;
import com.watashi.bookstore.util.validador.ValidadorString;

@Component
public class ValidaDadosPedido implements IStrategy {

    @Autowired
    ValidadorString validadorString;

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if(entidade instanceof Pedido){

            Pedido pedido = (Pedido) entidade;

            msg.append(validadorString.validar(pedido.getCliente(), "cliente"));
            msg.append(validadorString.validar(pedido.getFormaPagamentoList(), "forma de pagamento"));
            msg.append(validadorString.validar(pedido.getEndereco(), "endereço"));
            msg.append(validadorString.validar(pedido.getItemList(), "itens"));
            msg.append(validadorString.validar(pedido.getStatusPedido(), "status do pedido"));
            msg.append(validadorString.validar(pedido.getValorTotal(), "valor total"));

        }

        return msg.toString();
    }
}
