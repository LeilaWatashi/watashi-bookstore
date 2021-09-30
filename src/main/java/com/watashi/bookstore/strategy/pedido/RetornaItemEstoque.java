package com.watashi.bookstore.strategy.pedido;

import com.watashi.bookstore.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.watashi.bookstore.domain.*;
import com.watashi.bookstore.dao.LivroDAO;
import com.watashi.bookstore.util.Util;

import java.util.ArrayList;
import java.util.List;

@Component
public class RetornaItemEstoque implements IStrategy {

    @Autowired
    private LivroDAO livroDAO;

    @Override
    public String processar(EntidadeDominio entidade) {

        if(entidade instanceof Pedido || entidade instanceof Transicao){

            Pedido pedido = null;

            if(entidade instanceof Pedido)
                pedido = (Pedido) entidade;
            else if(entidade instanceof Transicao)
                pedido = ((Transicao) entidade).getPedido();

            List<Integer> listaStatusPermitidos = new ArrayList<>();

            listaStatusPermitidos.add(2);
            listaStatusPermitidos.add(4);
            listaStatusPermitidos.add(10);

            if(Util.isNotNull(pedido)
                    && Util.isNotNull(pedido.getStatusPedido())
                    && Util.isNotNull(pedido.getStatusPedido().getId())
                    && listaStatusPermitidos.contains(pedido.getStatusPedido().getId())) {

                for (Item item : pedido.getItemList()) {
                    Livro livroDevolvido = item.getLivro();
                    livroDevolvido.setQuantidadeDisponivel(livroDevolvido.getQuantidadeDisponivel() + item.getQuantidade());
                    livroDevolvido.setQuantidadeEstoque(livroDevolvido.getQuantidadeEstoque() + item.getQuantidade());
                    if(livroDevolvido.getStatus().getId().equals(2))
                        livroDevolvido.setStatus(Status.builder().id(1).build());
                    livroDAO.alterar(livroDevolvido);
                }

            }

        }

        return null;
    }
}
