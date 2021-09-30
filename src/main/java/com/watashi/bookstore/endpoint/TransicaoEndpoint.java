package com.watashi.bookstore.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.watashi.bookstore.domain.Resultado;
import com.watashi.bookstore.domain.Transicao;
import com.watashi.bookstore.facade.Fachada;

@RestController
@CrossOrigin
@RequestMapping("/transicoes")
public class TransicaoEndpoint {
    
    @Autowired
    private Fachada fachada;

    @PostMapping()
    public ResponseEntity<Resultado> consultar(@RequestBody Transicao transicao){
        return ResponseEntity.ok().body(fachada.consultar(transicao));
    }

    @PostMapping(path = "/cria")
    public ResponseEntity<Resultado> salvar(@RequestBody Transicao transicao){
        return ResponseEntity.ok().body(fachada.salvar(transicao));
    }
    
    @PutMapping()
    public ResponseEntity<Resultado> alterar(@RequestBody Transicao transicao){
    	return ResponseEntity.ok().body(fachada.alterar(transicao));
    }
}
