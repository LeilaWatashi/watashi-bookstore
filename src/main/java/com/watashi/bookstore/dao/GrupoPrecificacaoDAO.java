package com.watashi.bookstore.dao;

import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.domain.GrupoPrecificacao;
import com.watashi.bookstore.repository.GrupoPrecificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GrupoPrecificacaoDAO implements IDAO {

    @Autowired
    private GrupoPrecificacaoRepository grupoPrecificacaoRepository;

    @Autowired
    private LivroDAO livroDAO;

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        GrupoPrecificacao grupoPrecificacao = (GrupoPrecificacao) entidade;
        return grupoPrecificacaoRepository.save(grupoPrecificacao);
    }

    @Override
    public void alterar(EntidadeDominio entidade) {

    }

    @Override
    public void excluir(EntidadeDominio entidade) {

    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        GrupoPrecificacao grupoPrecificacao = (GrupoPrecificacao) entidade;
        List<EntidadeDominio> gruposPrecificacao = new ArrayList<>();

        grupoPrecificacaoRepository.findAll().forEach(transacao -> gruposPrecificacao.add(transacao));

        return gruposPrecificacao;
    }
}
