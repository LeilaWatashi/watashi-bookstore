package com.watashi.bookstore.strategy.cupom;

import com.watashi.bookstore.domain.Cupom;
import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.repository.UsuarioRepository;
import com.watashi.bookstore.strategy.IStrategy;
import com.watashi.bookstore.util.Util;
import com.watashi.bookstore.util.validador.ValidadorString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaDadosCupom implements IStrategy {

	@Autowired
	private ValidadorString validadorString;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public String processar(final EntidadeDominio entidade) {
		StringBuilder msg = new StringBuilder();
		if(entidade instanceof Cupom){
			Cupom cupom = (Cupom) entidade;
			if(Util.isNull(cupom.getValor())){
				msg.append(validadorString.validar(null, "valor do cupom"));
			}
			if(Util.isNotNull(cupom.getPessoa()) && Util.isNotNull(cupom.getPessoa().getUsuario())
				&& Util.isNotNull(cupom.getPessoa().getUsuario().getEmail())){
				if(!usuarioRepository.existsByEmail(((Cupom) entidade).getPessoa().getUsuario().getEmail())){
					msg.append("Email não existe.");
				}
			} else {
				msg.append(validadorString.validar(null, "email"));
			}
		}
		return msg.toString();
	}
}
