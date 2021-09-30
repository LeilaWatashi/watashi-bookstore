package com.watashi.bookstore.strategy.usuario;

import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.domain.Pessoa;
import com.watashi.bookstore.domain.Usuario;
import com.watashi.bookstore.repository.UsuarioRepository;
import com.watashi.bookstore.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaUsuarioAtivo implements IStrategy {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public String processar(EntidadeDominio entidade) {

        StringBuilder msg = new StringBuilder();

        if(entidade instanceof Pessoa || entidade instanceof Usuario){

            Usuario usuarioRecebido = null;

            if(entidade instanceof Pessoa) usuarioRecebido = ((Pessoa) entidade).getUsuario();

            if(entidade instanceof Usuario) usuarioRecebido = (Usuario) entidade;

            Usuario usuarioValidador = usuarioRepository.findByEmail(usuarioRecebido.getEmail());

            if(usuarioValidador != null && usuarioValidador.getStatus().getId() == 2){
                msg.append("Conta inativada.");
            }
        }

        return msg.toString();
    }
}
