package com.watashi.bookstore.strategy.cupom;

import com.watashi.bookstore.domain.Cupom;
import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.repository.CupomRepository;
import com.watashi.bookstore.strategy.IStrategy;
import com.watashi.bookstore.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaExistenciaCupom implements IStrategy {

	@Autowired
	private CupomRepository cupomRepository;

	@Override
	public String processar(final EntidadeDominio entidade) {
		StringBuilder msg = new StringBuilder();
		if(entidade instanceof Cupom){
			Cupom cupom = (Cupom) entidade;
			if(Util.isNotNull(cupom.getCodigo()) && cupom.getCodigo() != ""){
				if(Util.isNull(cupomRepository.findCupomByCodigo(cupom.getCodigo()))){
					msg.append("Cupom não existe.");
				}
			}
		}
		return msg.toString();
	}
}
