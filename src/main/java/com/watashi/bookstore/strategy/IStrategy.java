package com.watashi.bookstore.strategy;

import org.springframework.stereotype.Component;

import com.watashi.bookstore.domain.EntidadeDominio;

@Component
public interface IStrategy {
	String processar(EntidadeDominio entidade);
}
