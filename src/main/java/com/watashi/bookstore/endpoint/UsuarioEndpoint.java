package com.watashi.bookstore.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.watashi.bookstore.domain.Resultado;
import com.watashi.bookstore.domain.Usuario;
import com.watashi.bookstore.dto.PessoaDTO;
import com.watashi.bookstore.facade.Fachada;
import com.watashi.bookstore.util.DTOUtil;

@RestController
@CrossOrigin
@RequestMapping("/usuarios")
public class UsuarioEndpoint {
    
    @Autowired
    private Fachada fachada;

    @Autowired
    private PessoaDTO pessoaDTO;

    @PostMapping()
    public ResponseEntity<Resultado> consultar(@RequestBody Usuario usuario){
        return ResponseEntity.ok().body(DTOUtil.tranfereParaDTO(fachada.consultar(usuario), pessoaDTO));
    }

    @PutMapping()
    public ResponseEntity<?> alterar(@RequestBody Usuario usuario){
        return ResponseEntity.ok().body(DTOUtil.tranfereParaDTO(fachada.alterar(usuario), pessoaDTO));
    }
}
