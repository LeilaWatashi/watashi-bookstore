package com.watashi.bookstore.dao;

import com.watashi.bookstore.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watashi.bookstore.domain.Carrinho;
import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.domain.Item;
import com.watashi.bookstore.repository.CarrinhoRepository;

import java.util.List;

@Service
public class ItemDAO implements IDAO {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        return null;
    }

    @Override
    public void alterar(EntidadeDominio entidade) {

    }

    @Override
    public void excluir(EntidadeDominio entidade) {
        if(entidade instanceof Item) {
            Item item = (Item) entidade;

            livroRepository.save(item.getLivro());
            Carrinho carrinho = carrinhoRepository.findByItemListContaining(item);
            carrinho.getItemList().removeIf( itemResultado -> itemResultado.getId() == item.getId());
            carrinhoRepository.save(carrinho);
        }
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        return null;
    }
}
