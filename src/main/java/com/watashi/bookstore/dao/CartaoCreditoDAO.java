package com.watashi.bookstore.dao;

import com.watashi.bookstore.domain.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watashi.bookstore.domain.CartaoCredito;
import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.repository.CartaoCreditoRepository;
import com.watashi.bookstore.util.Util;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartaoCreditoDAO implements IDAO {

    @Autowired
    CartaoCreditoRepository cartaoCreditoRepository;

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        if(entidade instanceof CartaoCredito) return cartaoCreditoRepository.save((CartaoCredito) entidade);
        else return null;
    }

    @Override
    public void alterar(EntidadeDominio entidade) {
        if(entidade instanceof CartaoCredito) entidade = cartaoCreditoRepository.save((CartaoCredito) entidade);
        else entidade = null;
    }

    @Override
    public void excluir(EntidadeDominio entidade) {
        CartaoCredito cartaoCredito = (CartaoCredito) entidade;
        cartaoCreditoRepository.inativaCartaoCredito(cartaoCredito.getId());
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        if(entidade instanceof CartaoCredito){
            List<EntidadeDominio> cartoesCredito = new ArrayList<>();
            CartaoCredito cartaoCredito = (CartaoCredito) entidade;
            if(Util.isNotNull(cartaoCredito.getId())){
                cartoesCredito.add(cartaoCreditoRepository.findById(cartaoCredito.getId()).get());
                return cartoesCredito;
            }
            if(Util.isNotNull(cartaoCredito.getPessoa()) && Util.isNotNull(cartaoCredito.getPessoa().getId())
                && Util.isNotNull(cartaoCredito.getStatus()) && Util.isNotNull(cartaoCredito.getStatus().getId())){

                cartoesCredito.addAll(cartaoCreditoRepository.findEnderecoByPessoaAndStatus(cartaoCredito.getPessoa().getId(), cartaoCredito.getStatus().getId()));
                return cartoesCredito;
            }
            cartaoCreditoRepository.findByPessoa_Id(cartaoCredito.getPessoa().getId())
                    .forEach(resultado -> cartoesCredito.add(resultado));
            return cartoesCredito;
        } else return null;
    }
}
