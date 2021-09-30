package com.watashi.bookstore.dto;

import com.watashi.bookstore.domain.EntidadeDominio;

public interface IDTO {
    EntidadeDominio parseEntityToDTO(EntidadeDominio dominio);
    EntidadeDominio parseDTOToEntity(IDTO dto);
}
