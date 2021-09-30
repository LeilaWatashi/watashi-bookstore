package com.watashi.bookstore.endpoint;

import com.watashi.bookstore.domain.Cidade;
import com.watashi.bookstore.domain.Resultado;
import com.watashi.bookstore.facade.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/cidades")
public class CidadeEndpoint {
    
    @Autowired
    private Fachada fachada;

    @PostMapping()
    public ResponseEntity<Resultado> consultar(@RequestBody Cidade cidade){
        return ResponseEntity.ok().body(fachada.consultar(cidade));
    }
}
