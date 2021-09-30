package com.watashi.bookstore.endpoint;

import com.watashi.bookstore.domain.GrupoPrecificacao;
import com.watashi.bookstore.domain.Resultado;
import com.watashi.bookstore.facade.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/grupo-precificacao")
public class GrupoPrecificacaoEndpoint {
    
    @Autowired
    private Fachada fachada;

    @PostMapping()
    public ResponseEntity<Resultado> consultar(@RequestBody GrupoPrecificacao grupoPrecificacao){
        return ResponseEntity.ok().body(fachada.consultar(grupoPrecificacao));
    }

    @PostMapping("/cria")
    public ResponseEntity<?> salvar(@RequestBody GrupoPrecificacao grupoPrecificacao){
        return ResponseEntity.ok().body(fachada.salvar(grupoPrecificacao));
    }
}
