package com.watashi.bookstore.strategy.usuario;

import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.domain.Pessoa;
import com.watashi.bookstore.domain.Usuario;
import com.watashi.bookstore.strategy.IStrategy;
import com.watashi.bookstore.util.Criptografia;
import com.watashi.bookstore.util.GeradorCodigo;
import com.watashi.bookstore.util.Util;
import com.watashi.bookstore.util.validador.ValidadorString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GeraCodigoUsuario implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {

        if(entidade instanceof Pessoa || entidade instanceof Usuario) {
            Usuario usuario = null;
            if(entidade instanceof Pessoa && Util.isNotNull(((Pessoa) entidade).getUsuario())) usuario = ((Pessoa) entidade).getUsuario();
            else return null;
            if(entidade instanceof Usuario) usuario = (Usuario) entidade;

            usuario.setCodigo(GeradorCodigo.gerarCodigoUsuario());
        }
        return null;
    }
}
