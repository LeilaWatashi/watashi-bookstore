package com.watashi.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.watashi.bookstore.domain.TipoUsuario;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Integer> {

    TipoUsuario findTipoUsuarioById(Integer id);
}
