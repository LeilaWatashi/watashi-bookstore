package com.watashi.bookstore.dao;

import org.springframework.stereotype.Service;

import com.watashi.bookstore.domain.EntidadeDominio;

import java.util.List;

@Service
public class EstoqueDAO implements IDAO {

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        return null;
    }

    @Override
    public void alterar(EntidadeDominio entidade) {

    }

    @Override
    public void excluir(EntidadeDominio entidade) {

    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        return null;
    }
}
