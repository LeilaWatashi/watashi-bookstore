package com.watashi.bookstore.strategy.carrinho;

import com.watashi.bookstore.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.domain.Item;
import com.watashi.bookstore.domain.Status;
import com.watashi.bookstore.repository.ItemRepository;
import com.watashi.bookstore.util.Util;

@Component
public class RetornaItemDisponivel implements IStrategy {

    @Autowired
    ItemRepository itemRepository;

    /*
     *  Método resposável por retirar a quantidade de itens que estava ocupada no carrinho e disponibilizala-la ser
     *  comercializada novamente, assim espero.
     */

    @Override
    public String processar(EntidadeDominio entidade) {

        if(entidade instanceof Item){

            Item item = (Item) entidade;

            Item itemConsultado = itemRepository.findById(item.getId()).get();

            if(Util.isNotNull(itemConsultado)) {

                Integer quantidadeDisponivelAtualmente = itemConsultado.getLivro().getQuantidadeDisponivel();

                itemConsultado.getLivro().setQuantidadeDisponivel( quantidadeDisponivelAtualmente + itemConsultado.getQuantidade());

                if(itemConsultado.getLivro().getStatus().getStatus().trim().equals("Inativo"))
                    itemConsultado.getLivro().setStatus(Status.builder().id(1).build());

                item.setLivro(itemConsultado.getLivro());

            }

        }

        return null;
    }
}
