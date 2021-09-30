package com.watashi.bookstore.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watashi.bookstore.domain.Cidade;
import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.repository.CidadeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CidadeDAO implements IDAO {

    @Autowired
    CidadeRepository cidadeRepository;

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) { return null; }

    @Override
    public void alterar(EntidadeDominio entidade) { }

    @Override
    public void excluir(EntidadeDominio entidade) { }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        if (entidade instanceof Cidade){
            List<EntidadeDominio> cidades = new ArrayList<>();
            Cidade cidade = (Cidade) entidade;
            cidadeRepository.findByEstado_Id(cidade.getEstado().getId())
                    .forEach( resultadoCidade -> cidades.add(resultadoCidade));
            return cidades;
        } return null;
    }
}
