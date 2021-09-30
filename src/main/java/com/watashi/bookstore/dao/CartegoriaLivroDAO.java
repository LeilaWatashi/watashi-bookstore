package com.watashi.bookstore.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watashi.bookstore.domain.CategoriaLivro;
import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.repository.CategoriaLivroRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartegoriaLivroDAO implements IDAO {

    @Autowired
    CategoriaLivroRepository categoriaLivroRepository;

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) { return null; }

    @Override
    public void alterar(EntidadeDominio entidade) {}

    @Override
    public void excluir(EntidadeDominio entidade) {}

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        List<EntidadeDominio> categorias = new ArrayList<>();
        if(entidade instanceof CategoriaLivro){
            categoriaLivroRepository.findAll()
                    .forEach(resultado -> categorias.add(resultado));
            return categorias;
        } else return null;
    }
}
