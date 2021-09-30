package com.watashi.bookstore.strategy.pedido;

import com.watashi.bookstore.strategy.IStrategy;
import org.springframework.stereotype.Component;

import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.domain.Pedido;
import com.watashi.bookstore.util.Util;
import com.watashi.bookstore.util.correio.WebServiceCorreio;

import java.time.LocalDate;

@Component
public class CalcularDataEntrega implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {

        if(entidade instanceof Pedido){

            Pedido pedido = (Pedido) entidade;
            
            Integer diasParaEntrega = new WebServiceCorreio().calculaPrecoPrazo(pedido.getEndereco().getCep()).getQuantidadeDiasEntrega();
            
            if(Util.isNotNull(diasParaEntrega))
            	pedido.setDataEstimada(LocalDate.now().plusDays(diasParaEntrega));
        	else
        		pedido.setDataEstimada(LocalDate.now().plusDays(7));
        }

        return null;
    }
}
