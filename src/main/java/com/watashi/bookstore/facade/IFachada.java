package com.watashi.bookstore.facade;

import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.domain.Resultado;

public interface IFachada {
	
	Resultado salvar(EntidadeDominio entidade);
	Resultado alterar(EntidadeDominio entidade);
	Resultado excluir(EntidadeDominio entidade);
	Resultado consultar(EntidadeDominio entidade);
}
