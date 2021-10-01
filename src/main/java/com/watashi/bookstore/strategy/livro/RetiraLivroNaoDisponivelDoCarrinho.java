package com.watashi.bookstore.strategy.livro;

import com.watashi.bookstore.dao.LivroDAO;
import com.watashi.bookstore.dao.ItemDAO;
import com.watashi.bookstore.domain.Carrinho;
import com.watashi.bookstore.domain.Livro;
import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.domain.Item;
import com.watashi.bookstore.repository.CarrinhoRepository;
import com.watashi.bookstore.repository.ItemRepository;
import com.watashi.bookstore.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RetiraLivroNaoDisponivelDoCarrinho implements IStrategy {

    @Autowired
    private LivroDAO livroDAO;

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemDAO itemDAO;

    @Override
    public String processar(EntidadeDominio entidade) {

        if(entidade instanceof Livro){
            Livro livro = (Livro) entidade;
            if(livro.getQuantidadeDisponivel() <= 0 || livro.getStatus().getId() == 2){
                Integer quantidadeRetornada = 0;
                List<Carrinho> carrinhos = carrinhoRepository.findByItemListContainingLivroId(livro.getId());
                for (Carrinho carrinho : carrinhos) {
                    List<Item> itemsRemovidos = new ArrayList<>();
                    for (Item item : carrinho.getItemList()) {
                        if(item.getLivro().getId() == livro.getId() && livro.getQuantidadeDisponivel() < item.getQuantidade()){
                            quantidadeRetornada += item.getQuantidade();
                            itemsRemovidos.add(item);
                        }
                    }
                    if(itemsRemovidos.size() > 0){
                        carrinho.getItemList().removeAll(itemsRemovidos);
                        carrinhoRepository.save(carrinho);
                        itemRepository.deleteAll(itemsRemovidos);
                    }
                }
                if(quantidadeRetornada > 0 && quantidadeRetornada <= livro.getQuantidadeEstoque()){
                    livro.setQuantidadeDisponivel(quantidadeRetornada);
                } else {
                    livro.setQuantidadeDisponivel(0);
                }
            }
        }

        return null;
    }
}
