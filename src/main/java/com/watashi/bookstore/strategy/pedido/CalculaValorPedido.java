package com.watashi.bookstore.strategy.pedido;

import com.watashi.bookstore.strategy.IStrategy;
import org.springframework.stereotype.Component;

import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.domain.Item;
import com.watashi.bookstore.domain.Pedido;
import com.watashi.bookstore.util.Util;
import com.watashi.bookstore.util.correio.WebServiceCorreio;

@Component
public class CalculaValorPedido implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {

        if(entidade instanceof Pedido){

            Pedido pedido = (Pedido) entidade;
            
            Double custoFrete = new WebServiceCorreio().calculaPrecoPrazo(pedido.getEndereco().getCep()).getValorCusto();

            if(Util.isNotNull(pedido.getItemList())){

                Double valorTotal = 0.0;

                Double valorGrupoPrecificacao;

                for (Item item: pedido.getItemList()) {
                    valorGrupoPrecificacao = item.getLivro().getGrupoPrecificacao().getValor() / 100;
                    valorTotal += (item.getLivro().getValorCompra() + (item.getLivro().getValorCompra() * valorGrupoPrecificacao)) * item.getQuantidade();
                }
                
                if(Util.isNotNull(custoFrete))
                	valorTotal += custoFrete;

                pedido.setValorTotal(valorTotal);

            }

        }

        return null;
    }
}
