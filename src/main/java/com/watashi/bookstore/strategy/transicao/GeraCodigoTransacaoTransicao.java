package com.watashi.bookstore.strategy.transicao;

import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.domain.Transicao;
import com.watashi.bookstore.strategy.IStrategy;
import com.watashi.bookstore.util.GeradorCodigo;
import org.springframework.stereotype.Component;

@Component
public class GeraCodigoTransacaoTransicao implements IStrategy {

	@Override
	public String processar(final EntidadeDominio entidade) {
		if(entidade instanceof Transicao){
			Transicao transicao = (Transicao) entidade;
			transicao.setCodigo(GeradorCodigo.gerarCodigoTransacao());
		}
		return null;
	}
}
