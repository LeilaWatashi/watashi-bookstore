package com.watashi.bookstore.repository;

import com.watashi.bookstore.domain.GrupoPrecificacao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoPrecificacaoRepository extends CrudRepository<GrupoPrecificacao, Integer> {
}
