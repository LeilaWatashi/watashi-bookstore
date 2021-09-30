package com.watashi.bookstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.watashi.bookstore.domain.StatusPedido;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusPedidoRepository extends CrudRepository<StatusPedido, Integer> {

}
