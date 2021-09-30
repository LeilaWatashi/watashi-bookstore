package com.watashi.bookstore.dto;

import com.watashi.bookstore.domain.Cupom;
import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.domain.FormaPagamento;
import com.watashi.bookstore.util.Util;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FormaPagamentoDTO extends EntidadeDominio implements IDTO{

    private Integer id;

    private Double valorPagamento;

    private Cupom cupom;

    private CartaoCreditoDTO cartaoCredito;

    @Override
    public EntidadeDominio parseEntityToDTO(EntidadeDominio dominio) {

        FormaPagamento formaPagamento = (FormaPagamento) dominio;

        CartaoCreditoDTO cartaoCreditoDTO = new CartaoCreditoDTO();

        FormaPagamentoDTO formaPagamentoDTO = new FormaPagamentoDTO();
        formaPagamentoDTO.setId(formaPagamento.getId());
        formaPagamentoDTO.setValorPagamento(formaPagamento.getValorPagamento());
        if(Util.isNotNull(formaPagamento.getCartaoCredito())){
            formaPagamentoDTO.setCartaoCredito( (CartaoCreditoDTO) cartaoCreditoDTO.parseEntityToDTO(formaPagamento.getCartaoCredito()));
        }
        if(Util.isNotNull(formaPagamento.getCupom())){
            formaPagamentoDTO.setCupom(formaPagamento.getCupom());
        }

        return formaPagamentoDTO;
    }

    @Override
    public EntidadeDominio parseDTOToEntity(IDTO dto) {
        return null;
    }
}
