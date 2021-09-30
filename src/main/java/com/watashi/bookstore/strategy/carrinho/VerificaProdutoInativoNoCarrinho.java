package com.watashi.bookstore.strategy.carrinho;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.watashi.bookstore.domain.Carrinho;
import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.strategy.IStrategy;
import com.watashi.bookstore.repository.CarrinhoRepository;
import com.watashi.bookstore.repository.ItemRepository;
import com.watashi.bookstore.util.Util;

@Component
public class VerificaProdutoInativoNoCarrinho implements IStrategy {

    @Autowired
    CarrinhoRepository carrinhoRepository;

    @Autowired
    ItemRepository itemRepository;

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if(entidade instanceof Carrinho){

            Carrinho carrinho = (Carrinho) entidade;

            if(Util.isNotNull(carrinho.getPessoa())
                && Util.isNotNull(carrinho.getPessoa().getId())){

                Carrinho carrinhoEncontrado  = carrinhoRepository.findByPessoa_Id(carrinho.getPessoa().getId());

                if(Util.isNotNull(carrinhoEncontrado)){
                    carrinhoEncontrado.getItemList().removeIf( item -> item.getLivro().getQuantidadeEstoque() <= 0 );
                    carrinhoRepository.save(carrinhoEncontrado);
                }
            }
        }
        return msg.toString();
    }
}
