package com.watashi.bookstore.endpoint;

import com.watashi.bookstore.domain.Resultado;
import com.watashi.bookstore.domain.TipoTelefone;
import com.watashi.bookstore.facade.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/tipos-telefone")
public class TipoTelefoneEndpoint {
    
    @Autowired
    private Fachada fachada;

    @PostMapping()
    public ResponseEntity<Resultado> consultar(@RequestBody TipoTelefone tipoTelefone){
        return ResponseEntity.ok().body(fachada.consultar(tipoTelefone));
    }
}
