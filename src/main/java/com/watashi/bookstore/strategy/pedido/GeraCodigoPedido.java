package com.watashi.bookstore.strategy.pedido;

import com.watashi.bookstore.strategy.IStrategy;
import org.springframework.stereotype.Component;

import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.domain.Pedido;
import com.watashi.bookstore.util.GeradorCodigo;
import com.watashi.bookstore.util.Util;

@Component
public class GeraCodigoPedido implements IStrategy {
    @Override
    public String processar(EntidadeDominio entidade) {

        if(entidade instanceof Pedido){
            Pedido pedido = (Pedido) entidade;

            if(Util.isNotNull(pedido)){
                pedido.setCodigoPedido(GeradorCodigo.gerarCodigoPedido());
            }
        }
        return null;
    }
}
