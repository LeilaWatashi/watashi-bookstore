package com.watashi.bookstore.strategy.usuario;

import com.watashi.bookstore.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.domain.Usuario;
import com.watashi.bookstore.repository.UsuarioRepository;
import com.watashi.bookstore.util.Criptografia;
import com.watashi.bookstore.util.validador.ValidadorString;

@Component
public class ValidaSenhaUsuario implements IStrategy {

	@Autowired
	ValidadorString validadorString;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Override
	public String processar(EntidadeDominio entidade) {

		StringBuilder msg = new StringBuilder();

		if(entidade instanceof Usuario){
			Usuario usuario = (Usuario) entidade;
			if(validadorString.validar(usuario.getPassword(), "senha").equals("")){
				usuario.setPassword(Criptografia.criptografar(usuario.getPassword()));
				if(!usuarioRepository.existsByEmailAndPassword(usuario.getEmail(), usuario.getPassword())){
					msg.append("Login ou senha incorreto.");
				}
			}
		}
		
		return msg.toString();
	}

}
