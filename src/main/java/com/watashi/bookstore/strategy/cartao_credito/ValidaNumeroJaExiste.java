package com.watashi.bookstore.strategy.cartao_credito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.watashi.bookstore.domain.CartaoCredito;
import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.strategy.IStrategy;
import com.watashi.bookstore.repository.CartaoCreditoRepository;
import com.watashi.bookstore.util.Util;

@Component
public class ValidaNumeroJaExiste implements IStrategy {
	
	@Autowired
	private CartaoCreditoRepository cartaoCreditoRepository;

    @Override
    public String processar(EntidadeDominio entidade) {
    	
    	StringBuilder msg = new StringBuilder();
    	
    	if(entidade instanceof CartaoCredito){

            CartaoCredito cartaoCredito = (CartaoCredito) entidade;

            if(Util.isNotNull(cartaoCredito.getNumero())){
                if(cartaoCreditoRepository.existsByNumero(cartaoCredito.getNumero())){
                    msg.append("Número de cartão de crédito já existe.");
                }
            }
        }

        return msg.toString();
    }
}
