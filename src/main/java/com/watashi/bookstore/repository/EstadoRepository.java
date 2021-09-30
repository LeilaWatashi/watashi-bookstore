package com.watashi.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.watashi.bookstore.domain.Estado;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoRepository extends CrudRepository<Estado, Integer> {}
