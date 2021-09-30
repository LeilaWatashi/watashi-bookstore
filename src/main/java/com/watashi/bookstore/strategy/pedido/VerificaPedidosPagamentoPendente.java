package com.watashi.bookstore.strategy.pedido;

import com.watashi.bookstore.dao.LivroDAO;
import com.watashi.bookstore.strategy.IStrategy;
import com.watashi.bookstore.strategy.email.pedido.EnviaEmailPedidoPagamentoAprovado;
import com.watashi.bookstore.strategy.email.pedido.EnviaEmailPedidoPagamentoRejeitado;
import com.watashi.bookstore.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.watashi.bookstore.domain.*;
import com.watashi.bookstore.dao.PedidoDAO;
import com.watashi.bookstore.repository.PedidoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class VerificaPedidosPagamentoPendente implements IStrategy {

    @Autowired
    private EnviaEmailPedidoPagamentoAprovado enviaEmailPedidoPagamentoAprovado;

    @Autowired
    private EnviaEmailPedidoPagamentoRejeitado enviaEmailPedidoPagamentoRejeitado;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoDAO pedidoDAO;

    @Autowired
    private LivroDAO livroDAO;

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();
        
        List<String> numeroCartoesPermitidosParaCompra = new ArrayList<>();

    	numeroCartoesPermitidosParaCompra.add("9999999999999999");
    	numeroCartoesPermitidosParaCompra.add("7777777777777777");
    	numeroCartoesPermitidosParaCompra.add("5555555555555555");
    	numeroCartoesPermitidosParaCompra.add("1111111111111111");
    	numeroCartoesPermitidosParaCompra.add("3333333333333333");

        if(entidade instanceof Pedido){

        	Set<Pedido> pedidosPagamentoPendente = pedidoRepository
                    .findByStatusPedido_Id(Pedido.builder().statusPedido(StatusPedido.builder().id(1).build()).build().getStatusPedido().getId());

            for (Pedido pedido : pedidosPagamentoPendente) {

                Set<FormaPagamento> formaPagamentoList = pedido.getFormaPagamentoList();

                for (FormaPagamento formaPagamento : formaPagamentoList) {

                    Boolean naoEValido = false;

                    if(Util.isNotNull(formaPagamento.getCartaoCredito())
                        && !numeroCartoesPermitidosParaCompra.contains(formaPagamento.getCartaoCredito().getNumero())){
                        naoEValido = true;
                    }

                    if(naoEValido){
                        enviaEmailPedidoPagamentoRejeitado.processar(pedido);
                        pedido.setStatusPedido(StatusPedido.builder().id(2).build());
                        pedidoDAO.alterar(pedido);
                        for (Item item : pedido.getItemList()) {
                            Livro livroDevolvido = item.getLivro();
                            livroDevolvido.setQuantidadeDisponivel(livroDevolvido.getQuantidadeDisponivel() + item.getQuantidade());
                            livroDevolvido.setQuantidadeEstoque(livroDevolvido.getQuantidadeEstoque() + item.getQuantidade());
                            if(livroDevolvido.getStatus().getId().equals(2))
                                livroDevolvido.setStatus(Status.builder().id(1).build());
                            livroDAO.alterar(livroDevolvido);
                        }
                    } else {
                        enviaEmailPedidoPagamentoAprovado.processar(pedido);
                        pedido.setStatusPedido(StatusPedido.builder().id(3).build());
                        pedidoDAO.alterar(pedido);
                    }
                }
            }
        }

        return msg.toString();
    }
}
