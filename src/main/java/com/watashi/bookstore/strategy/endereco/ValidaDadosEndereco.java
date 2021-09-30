package com.watashi.bookstore.strategy.endereco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.watashi.bookstore.domain.Endereco;
import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.strategy.IStrategy;
import com.watashi.bookstore.util.validador.ValidadorString;

@Component
public class ValidaDadosEndereco implements IStrategy {

    @Autowired
    ValidadorString validadorString;

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if(entidade instanceof Endereco){
            Endereco endereco = (Endereco) entidade;
            msg.append(validadorString.validar(endereco.getLogradouro(),"logradouro"));
            msg.append(validadorString.validar(endereco.getNumero(), "numero"));
            msg.append(validadorString.validar(endereco.getBairro(), "bairro"));
            msg.append(validadorString.validar(endereco.getCep(), "cep"));
            if(endereco.getCidade() != null) {
                msg.append(validadorString.validar(endereco.getCidade().getNome(), "cidade"));
                if(endereco.getCidade().getEstado() != null){
                    msg.append(validadorString.validar(endereco.getCidade().getEstado().getNome(), "estado"));
                } else msg.append(validadorString.validar("", "estado"));
            } else {
                msg.append(validadorString.validar("", "cidade"));
                msg.append(validadorString.validar("", "estado"));
            }
        }

        return msg.toString();
    }
}
