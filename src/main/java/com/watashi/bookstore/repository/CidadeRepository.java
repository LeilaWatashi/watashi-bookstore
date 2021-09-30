package com.watashi.bookstore.repository;

import com.watashi.bookstore.domain.Cidade;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CidadeRepository extends CrudRepository<Cidade, Integer> {

    ArrayList<Cidade> findByEstado_Id(Integer id);

    Cidade findByNome(String nome);

    Boolean existsByEstado_IdAndAndNome(Integer id, String nome);
}
