package com.watashi.bookstore.strategy.cupom;

import com.watashi.bookstore.domain.Cupom;
import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.strategy.IStrategy;
import com.watashi.bookstore.util.Util;
import com.watashi.bookstore.util.validador.ValidadorString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaDadosCupomConsulta implements IStrategy {

	@Autowired
	private ValidadorString validadorString;

	@Override
	public String processar(final EntidadeDominio entidade) {
		StringBuilder msg = new StringBuilder();
		if(entidade instanceof Cupom){
			Cupom cupom = (Cupom) entidade;
			if(Util.isNotNull(cupom.getStatus())){
				msg.append(validadorString.validar(cupom.getCodigo(), "código do cupom"));
			}
		}
		return msg.toString();
	}
}
