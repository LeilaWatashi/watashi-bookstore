package com.watashi.bookstore.endpoint;

import com.watashi.bookstore.domain.Bandeira;
import com.watashi.bookstore.domain.CartaoCredito;
import com.watashi.bookstore.domain.Cupom;
import com.watashi.bookstore.domain.Resultado;
import com.watashi.bookstore.facade.Fachada;
import com.watashi.bookstore.util.DTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/cupons")
public class CupomEndpoint {
    
    @Autowired
    private Fachada fachada;

    @PostMapping()
    public ResponseEntity<Resultado> consultar(@RequestBody Cupom cupom){
        return ResponseEntity.ok().body(fachada.consultar(cupom));
    }

    @PostMapping(path = "/cria")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> salvar(@RequestBody Cupom cupom){
        return ResponseEntity.ok().body(fachada.salvar(cupom));
    }
}
