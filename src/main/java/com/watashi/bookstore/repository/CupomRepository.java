package com.watashi.bookstore.repository;

import com.watashi.bookstore.domain.Cupom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CupomRepository extends CrudRepository<Cupom, Integer> {
    List<Cupom> findByStatus_Id(Integer id);
    List<Cupom> findByPessoa_Id(Integer id);
    Cupom findCupomByCodigo(String codigo);
}
