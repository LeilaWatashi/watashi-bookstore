package com.watashi.bookstore.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import com.watashi.bookstore.domain.EntidadeDominio;
import com.watashi.bookstore.domain.Status;
import com.watashi.bookstore.domain.TipoUsuario;
import com.watashi.bookstore.domain.Usuario;

@NoArgsConstructor
@Setter
@Getter
@Component
public class UsuarioDTO extends EntidadeDominio implements IDTO{

    private Integer id;

    private String codigo;

    private String email;

    private Boolean isAdmin;

    @Override
    public EntidadeDominio parseEntityToDTO(EntidadeDominio dominio) {

        if(dominio instanceof Usuario){

            Usuario usuario = (Usuario) dominio;
            UsuarioDTO usuarioDTO = new UsuarioDTO();

            usuarioDTO.setId(usuario.getId());
            usuarioDTO.setCodigo(usuario.getCodigo());
            usuarioDTO.setEmail(usuario.getEmail());

            usuarioDTO.setIsAdmin(usuario.getTipoUsuario() != null && usuario.getTipoUsuario().getTipo() != null
                    && usuario.getTipoUsuario().getTipo().trim().equalsIgnoreCase("administrador"));

            return usuarioDTO;
        }
        return null;
    }

    @Override
    public EntidadeDominio parseDTOToEntity(IDTO dto) {
        return null;
    }
}
