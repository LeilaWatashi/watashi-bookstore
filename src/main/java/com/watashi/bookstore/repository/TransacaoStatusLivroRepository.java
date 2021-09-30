package com.watashi.bookstore.repository;

import com.watashi.bookstore.domain.TransacaoStatusLivro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoStatusLivroRepository extends CrudRepository<TransacaoStatusLivro, Integer> {
    List<TransacaoStatusLivro> findByLivro_Id_OrderByLivro_Id(Integer livroId);
}
