package com.watashi.bookstore.dao;

import com.watashi.bookstore.domain.Carrinho;
import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.domain.Item;
import com.watashi.bookstore.repository.CarrinhoRepository;
import com.watashi.bookstore.repository.ItemRepository;
import com.watashi.bookstore.repository.LivroRepository;
import com.watashi.bookstore.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarrinhoDAO implements IDAO {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        if(entidade instanceof Carrinho){
            Carrinho carrinho = (Carrinho) entidade;
            for(Item item : carrinho.getItemList()) {
                livroRepository.save(item.getLivro());
                itemRepository.save(item);
            }
            carrinho = carrinhoRepository.save(carrinho);
            return null;
        }
        return null;
    }

    @Override
    public void alterar(EntidadeDominio entidade) {
        if(entidade instanceof Carrinho) this.salvar(entidade);
    }

    @Override
    public void excluir(EntidadeDominio entidade) {}

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        List<EntidadeDominio> carrinhos = new ArrayList<>();
        Carrinho carrinho = (Carrinho) entidade;
        if(Util.isNotNull(carrinho.getPessoa()) && Util.isNotNull(carrinho.getPessoa().getId())){
            carrinhos.add(carrinhoRepository.findByPessoa_Id(carrinho.getPessoa().getId()));
            return carrinhos;
        }
        carrinhoRepository.findAll().forEach(carrinhoResultado -> carrinhos.add(carrinhoResultado));
        return carrinhos;
    }
}
