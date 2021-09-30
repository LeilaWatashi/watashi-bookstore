package com.watashi.bookstore.dao;

import com.watashi.bookstore.domain.Livro;
import com.watashi.bookstore.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.repository.StatusRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class LivroDAO implements IDAO {

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    StatusRepository statusRepository;

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        if(entidade instanceof Livro) return livroRepository.save((Livro) entidade);
        else return null;
    }

    @Override
    public void alterar(EntidadeDominio entidade) {
        if(entidade instanceof Livro) entidade = livroRepository.save((Livro) entidade);
        else entidade = null;
    }

    @Override
    public void excluir(EntidadeDominio entidade) {
        if(entidade instanceof Livro) {
            Livro livro = (Livro) entidade;
            livro = livroRepository.findById(livro.getId()).get();
            livro.setStatus( statusRepository.findById(livro.getStatus().getId() == 1 ? 2 : 1).get());
            livroRepository.save(livro);
        }
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        List<EntidadeDominio> livros = new ArrayList<>();
        if (entidade instanceof Livro){
            Livro livro = (Livro) entidade;
            if(livro.getStatus() != null){
                if (livro.getStatus().getId()  >= 1 && livro.getStatus().getId() <= 2){
                    livroRepository.findByStatus_Id(livro.getStatus().getId())
                            .forEach( resultado -> livros.add(resultado));
                }
            }
            if (livro.getId() != null){
                livros.add(livroRepository.findById(livro.getId()).get());
                return livros;
            }
            return livros;
        }
        return null;
    }
}
