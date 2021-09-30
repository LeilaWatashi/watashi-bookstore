package com.watashi.bookstore.dao;

import java.util.List;

import com.watashi.bookstore.domain.EntidadeDominio;

public interface IDAO {
	
	EntidadeDominio salvar(EntidadeDominio entidade);
	void alterar(EntidadeDominio entidade);
	void excluir(EntidadeDominio entidade);
	List<EntidadeDominio> consultar(EntidadeDominio entidade);

}
