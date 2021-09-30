package com.watashi.bookstore.strategy.usuario;

import com.watashi.bookstore.domain.Usuario;
import com.watashi.bookstore.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.domain.Pessoa;
import com.watashi.bookstore.util.Criptografia;
import com.watashi.bookstore.util.validador.ValidadorString;

@Component
public class CriptografaSenha implements IStrategy {

    @Autowired
    ValidadorString validadorString;

    @Override
    public String processar(EntidadeDominio entidade) {

        if(entidade instanceof Pessoa || entidade instanceof Usuario) {
            Usuario usuario = null;
            if(entidade instanceof Pessoa) usuario = ((Pessoa) entidade).getUsuario();
            if(entidade instanceof Usuario) usuario = (Usuario) entidade;

            if(validadorString.validar(usuario.getPassword(), "senha") == ""){
                usuario.setPassword(Criptografia.criptografar(usuario.getPassword()));
            }
        }

        return null;
    }
}
