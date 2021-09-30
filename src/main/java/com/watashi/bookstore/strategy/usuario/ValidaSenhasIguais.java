package com.watashi.bookstore.strategy.usuario;

import com.watashi.bookstore.strategy.IStrategy;
import org.springframework.stereotype.Component;

import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.domain.Pessoa;
import com.watashi.bookstore.domain.Usuario;
import com.watashi.bookstore.util.Util;

@Component
public class ValidaSenhasIguais implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if (entidade instanceof Pessoa
            || entidade instanceof Usuario) {

            Usuario usuario = null;

            if(entidade instanceof Pessoa) usuario = ((Pessoa) entidade).getUsuario();

            if(entidade instanceof Usuario) usuario = (Usuario) entidade;

            if(Util.isNotNull(usuario.getPassword()) && Util.isNotNull(usuario.getRePassword())) {
                if(!usuario.getPassword().equals(usuario.getRePassword())) {
                    msg.append("Senhas estão diferentes.");
                }
            }
        }
        return msg.toString();
    }
}
