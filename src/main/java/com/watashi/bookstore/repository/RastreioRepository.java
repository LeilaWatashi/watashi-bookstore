package com.watashi.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.watashi.bookstore.domain.Rastreio;
import org.springframework.stereotype.Repository;

@Repository
public interface RastreioRepository extends CrudRepository<Rastreio, Integer> {
}
