package com.watashi.bookstore.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.domain.Rastreio;
import com.watashi.bookstore.repository.RastreioRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class RastreioDAO implements IDAO {

    @Autowired
    private RastreioRepository rastreioRepository;

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        return rastreioRepository.save((Rastreio) entidade);
    }

    @Override
    public void alterar(EntidadeDominio entidade) {}

    @Override
    public void excluir(EntidadeDominio entidade) {}

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        List<EntidadeDominio> rastrios = new ArrayList<>();
        if(entidade instanceof Rastreio){
            Rastreio rastreio = (Rastreio) entidade;
            rastreioRepository.findAll().forEach( bandeira -> rastrios.add(bandeira));
            return rastrios;
        } else return null;
    }
}
