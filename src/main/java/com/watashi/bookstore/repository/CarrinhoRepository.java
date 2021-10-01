package com.watashi.bookstore.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.watashi.bookstore.domain.Carrinho;
import com.watashi.bookstore.domain.Item;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarrinhoRepository extends CrudRepository<Carrinho, Integer> {
//    List<Carrinho> findByPessoa_Id(Integer id);

    Carrinho findByPessoa_Id(Integer id);

    Carrinho findByItemListContaining(Item item);

    @Query("select c from Carrinho c join fetch c.itemList i where i.livro.id = :id")
    List<Carrinho> findByItemListContainingLivroId(@Param("id") Integer id);

}
