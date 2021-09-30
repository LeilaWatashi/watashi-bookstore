package com.watashi.bookstore.strategy.pessoa;

import com.watashi.bookstore.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.domain.Pessoa;
import com.watashi.bookstore.repository.PessoaRepository;

@Service
public class ValidaExistenciaPessoa implements IStrategy {

    @Autowired
    PessoaRepository pessoaRepository;

    @Override
    public String processar(final EntidadeDominio entidade) {
        Pessoa pessoa = (Pessoa) entidade;
        StringBuilder msg = new StringBuilder();
        if(pessoa.getCpf() != null){
            if(pessoaRepository.existsPessoaByCpf(pessoa.getCpf())){
                msg.append("CPF já cadastrado.");
            }
        }
        return msg.toString();
    }
}
