package com.watashi.bookstore.strategy.transicao;

import com.watashi.bookstore.strategy.IStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.watashi.bookstore.domain.*;
import com.watashi.bookstore.strategy.email.troca.EnviaEmailTrocaCodigoRastreio;
import com.watashi.bookstore.dao.RastreioDAO;
import com.watashi.bookstore.util.GeradorCodigo;

@Component
public class GeraCodigoRastreioTransicao implements IStrategy {

	@Autowired
	private RastreioDAO rastreioDAO;

	@Override
	public String processar(final EntidadeDominio entidade) {

		if(entidade instanceof Transicao){

			Transicao transicao = (Transicao) entidade;

			if(transicao.getPedido() != null
				&& transicao.getPedido().getStatusPedido() != null
				&& transicao.getPedido().getStatusPedido().getId().equals(9)){

				Rastreio rastreio = new Rastreio();

				rastreio.setCodigoRastreio(GeradorCodigo.gerarCodigoRastreio());
				rastreio = (Rastreio) rastreioDAO.salvar(rastreio);
				transicao.setRastreio(rastreio);
			}
		}

		return null;
	}
}
