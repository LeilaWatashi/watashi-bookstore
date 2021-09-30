package com.watashi.bookstore.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.watashi.bookstore.domain.Endereco;
import com.watashi.bookstore.domain.Resultado;
import com.watashi.bookstore.facade.Fachada;
import com.watashi.bookstore.util.correio.WebServiceCorreio;

import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping("/correios")
public class CorreioEndpoint {
    
    @Autowired
    private Fachada fachada;
    
    @Autowired
    private Resultado resultado;

    @PostMapping()
    public ResponseEntity<Resultado> consultar(@RequestBody Endereco endereco){
    	resultado.setEntidades(new ArrayList<>());
    	resultado.addEntidade(new WebServiceCorreio().calculaPrecoPrazo(endereco.getCep()));
        return ResponseEntity.ok().body(resultado);
    }
}
