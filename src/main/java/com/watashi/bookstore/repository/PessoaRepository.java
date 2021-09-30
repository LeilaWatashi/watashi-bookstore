package com.watashi.bookstore.repository;

import com.watashi.bookstore.domain.Pessoa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Integer> {

    boolean existsPessoaByCpf(String id);

    Pessoa findPessoaByUsuario_Id(Integer id);

    Pessoa findByUsuario_Email(String email);

}
