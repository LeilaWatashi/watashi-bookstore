package com.watashi.bookstore.dto;

import com.watashi.bookstore.domain.Bandeira;
import com.watashi.bookstore.domain.CartaoCredito;
import com.watashi.bookstore.domain.EntidadeDominio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
public class CartaoCreditoDTO extends EntidadeDominio implements IDTO{

    private Integer id;

    private String numero;

    private Bandeira bandeira;

    @Override
    public EntidadeDominio parseEntityToDTO(EntidadeDominio dominio) {

        if(dominio instanceof CartaoCredito){

            CartaoCredito cartaoCredito = (CartaoCredito) dominio;
            CartaoCreditoDTO cartaoCreditoDTO = new CartaoCreditoDTO();

            if(cartaoCredito.getId() != null){
                cartaoCreditoDTO.setId(cartaoCredito.getId());
                cartaoCreditoDTO.setNumero(cartaoCredito.getNumero()
                        .substring(cartaoCredito.getNumero().length()-4));
                cartaoCreditoDTO.setBandeira(cartaoCredito.getBandeira());

                return cartaoCreditoDTO;
            }
        }
        return null;
    }

    @Override
    public EntidadeDominio parseDTOToEntity(IDTO dto) {
        return null;
    }
}
