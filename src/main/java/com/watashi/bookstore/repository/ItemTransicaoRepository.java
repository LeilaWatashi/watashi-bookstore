package com.watashi.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.watashi.bookstore.domain.ItemTransacao;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemTransicaoRepository extends CrudRepository<ItemTransacao, Integer> { }
