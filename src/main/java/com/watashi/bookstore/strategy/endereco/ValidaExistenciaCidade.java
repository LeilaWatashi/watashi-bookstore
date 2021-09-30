package com.watashi.bookstore.strategy.endereco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.watashi.bookstore.domain.Endereco;
import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.strategy.IStrategy;
import com.watashi.bookstore.repository.CidadeRepository;
import com.watashi.bookstore.util.validador.ValidadorString;

@Component
public class ValidaExistenciaCidade implements IStrategy {

    @Autowired
    ValidadorString validadorString;

    @Autowired
    CidadeRepository cidadeRepository;

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if(entidade instanceof Endereco){
            Endereco endereco = (Endereco) entidade;
            if(endereco.getCidade().getNome() != null && endereco.getCidade().getEstado().getId() != null){
                if(!cidadeRepository
                        .existsByEstado_IdAndAndNome(endereco.getCidade().getEstado().getId(), endereco.getCidade().getNome())){
                    msg.append("Cidade não existe dentro do estado de " + endereco.getCidade().getEstado().getNome());
                }
            }
        }

        return msg.toString();
    }
}
