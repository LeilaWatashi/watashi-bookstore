package com.watashi.bookstore.repository;

import com.watashi.bookstore.domain.Livro;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends CrudRepository<Livro, Integer> {

    List<Livro> findByStatus_Id(Integer id);

    Boolean existsByImagemPath(String imagemPath);
}
