package com.watashi.bookstore.dao;

import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.domain.TransacaoStatusLivro;
import com.watashi.bookstore.repository.TransacaoStatusLivroRepository;
import com.watashi.bookstore.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransacaoStatusLivroDAO implements IDAO {

    @Autowired
    private TransacaoStatusLivroRepository transacaoStatusLivroRepository;

    @Autowired
    private LivroDAO livroDAO;

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        TransacaoStatusLivro transacaoStatusLivro = (TransacaoStatusLivro) entidade;
        livroDAO.alterar(transacaoStatusLivro.getLivro());
        return transacaoStatusLivroRepository.save(transacaoStatusLivro);
    }

    @Override
    public void alterar(EntidadeDominio entidade) {

    }

    @Override
    public void excluir(EntidadeDominio entidade) {

    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        TransacaoStatusLivro transacaoStatusLivro = (TransacaoStatusLivro) entidade;
        List<EntidadeDominio> transacoes = new ArrayList<>();

        if(Util.isNotNull(transacaoStatusLivro.getLivro())){
            transacoes.addAll(transacaoStatusLivroRepository.findByLivro_Id_OrderByLivro_Id(transacaoStatusLivro.getId()));
            return transacoes;
        }

        transacaoStatusLivroRepository.findAll().forEach(transacao -> transacoes.add(transacao));

        return transacoes;
    }
}
