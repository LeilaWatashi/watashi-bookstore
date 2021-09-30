package com.watashi.bookstore.dto;

import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.domain.Pessoa;
import com.watashi.bookstore.domain.Telefone;
import com.watashi.bookstore.domain.TipoTelefone;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Component
public class TelefoneDTO extends EntidadeDominio implements IDTO{


    private Integer id;

    private String ddd;

    private String numero;

    private TipoTelefone tipoTelefone;

    @Override
    public EntidadeDominio parseEntityToDTO(EntidadeDominio dominio) {
        if(dominio instanceof Telefone){
            TelefoneDTO telefoneDTO = new TelefoneDTO();
            Telefone telefone = (Telefone) dominio;

            telefoneDTO.setId(telefone.getId());
            telefoneDTO.setDdd(telefone.getDdd());
            telefoneDTO.setNumero(telefone.getNumero());
            telefoneDTO.setTipoTelefone(telefone.getTipoTelefone());

            return telefoneDTO;
        }
        return null;
    }

    @Override
    public EntidadeDominio parseDTOToEntity(IDTO dto) {
        return null;
    }
}
