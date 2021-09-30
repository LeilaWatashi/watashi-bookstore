package com.watashi.bookstore.strategy.usuario;

import com.watashi.bookstore.strategy.IStrategy;
import com.watashi.bookstore.strategy.pessoa.ValidaExistenciaPessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.domain.Pessoa;
import com.watashi.bookstore.domain.Usuario;
import com.watashi.bookstore.repository.UsuarioRepository;

@Component
public class ValidaExistenciaUsuario implements IStrategy {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ValidaExistenciaPessoa validaExistenciaPessoa;

    @Override
    public String processar(EntidadeDominio entidade) {

        Pessoa pessoa = (Pessoa) entidade;
        StringBuilder msg = new StringBuilder();

        Usuario usuarioRecebido = pessoa.getUsuario();

        Usuario usuarioValidador = usuarioRepository.findByEmail(usuarioRecebido.getEmail());

        if(usuarioValidador != null){
            if(usuarioRecebido.getId() != usuarioValidador.getId()){
                msg.append("Email já cadastrado.");
            }
        }

        return msg.toString();
    }
}
