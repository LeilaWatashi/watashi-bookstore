package com.watashi.bookstore.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.watashi.bookstore.domain.Bandeira;
import com.watashi.bookstore.domain.Resultado;
import com.watashi.bookstore.facade.Fachada;

@RestController
@CrossOrigin
@RequestMapping("/bandeiras")
public class BandeiraEndpoint {
    
    @Autowired
    private Fachada fachada;

    @PostMapping()
    public ResponseEntity<Resultado> consultar(@RequestBody Bandeira bandeira){
        return ResponseEntity.ok().body(fachada.consultar(bandeira));
    }
}
