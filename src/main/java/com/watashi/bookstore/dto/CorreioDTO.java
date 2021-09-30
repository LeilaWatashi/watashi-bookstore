package com.watashi.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import com.watashi.bookstore.domain.EntidadeDominio;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Component
public class CorreioDTO extends EntidadeDominio{
    private Double valorCusto;
    private Integer quantidadeDiasEntrega;
}
