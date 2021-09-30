package com.watashi.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.watashi.bookstore.domain.Bandeira;
import org.springframework.stereotype.Repository;

@Repository
public interface BandeiraRepository extends CrudRepository<Bandeira, Integer> { }
