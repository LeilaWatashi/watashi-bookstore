package com.watashi.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.watashi.bookstore.domain.FormaPagamento;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagamentoRepository extends CrudRepository<FormaPagamento, Integer> { }
