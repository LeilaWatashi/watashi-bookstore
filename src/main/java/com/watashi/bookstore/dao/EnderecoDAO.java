package com.watashi.bookstore.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watashi.bookstore.domain.Endereco;
import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.repository.CidadeRepository;
import com.watashi.bookstore.repository.EnderecoRepository;
import com.watashi.bookstore.util.Util;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnderecoDAO implements IDAO {

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    CidadeRepository cidadeRepository;

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        if (entidade instanceof Endereco) {
            Endereco endereco = (Endereco) entidade;
            endereco.setCidade(cidadeRepository.findByNome(endereco.getCidade().getNome()));
            return enderecoRepository.save(endereco);
        }
        else return null;
    }

    @Override
    public void alterar(EntidadeDominio entidade) {
        if (entidade instanceof Endereco) entidade = enderecoRepository.save((Endereco) entidade);
        else entidade = null;
    }

    @Override
    public void excluir(EntidadeDominio entidade) {
        Endereco endereco = (Endereco) entidade;
        enderecoRepository.inativaEndereco(endereco.getId());
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        if (entidade instanceof Endereco){
            List<EntidadeDominio> enderecos = new ArrayList<>();
            Endereco endereco = (Endereco) entidade;
            if(Util.isNotNull(endereco.getId())){
                enderecos.add(enderecoRepository.findById(endereco.getId()).get());
                return enderecos;
            }
            if(Util.isNotNull(endereco.getPessoa())
                    && Util.isNotNull(endereco.getPessoa().getId())
                    && Util.isNotNull(endereco.getStatus())){
                enderecos.addAll(enderecoRepository.findEnderecoByPessoaAndStatus(endereco.getPessoa().getId(), endereco.getStatus().getId()));
                return enderecos;
            }
            enderecoRepository.findByPessoa_Id(endereco.getPessoa().getId())
                    .forEach( resultadoEndereco -> enderecos.add(resultadoEndereco));
            return enderecos;
        } return null;
    }
}
