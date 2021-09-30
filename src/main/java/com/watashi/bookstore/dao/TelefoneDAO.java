package com.watashi.bookstore.dao;

import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.domain.Telefone;
import com.watashi.bookstore.repository.TelefoneRepository;
import com.watashi.bookstore.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TelefoneDAO implements IDAO {

    @Autowired
    private TelefoneRepository telefoneRepository;

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        return telefoneRepository.save((Telefone) entidade);
    }

    @Override
    public void alterar(EntidadeDominio entidade) {}

    @Override
    public void excluir(EntidadeDominio entidade) {}

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
    	
    	List<EntidadeDominio> telefones = new ArrayList<>();
        Telefone telefone = (Telefone) entidade;

        if(Util.isNotNull(telefone.getPessoa()) && Util.isNotNull(telefone.getPessoa().getId())){
            telefones.addAll(telefoneRepository.findByPessoa_Id(telefone.getPessoa().getId()));
            return telefones;
        }
    	
    	telefoneRepository.findAll().forEach(tipoTelefone -> telefones.add(tipoTelefone));
    	
        return telefones;
    }
}
