package com.watashi.bookstore.endpoint;

import com.watashi.bookstore.domain.Resultado;
import com.watashi.bookstore.domain.TransacaoStatusLivro;
import com.watashi.bookstore.facade.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/transacaos/status/livros")
public class TransacaoStatusLivroEndpoint {
    
    @Autowired
    private Fachada fachada;

    @PostMapping()
    public ResponseEntity<Resultado> consultar(@RequestBody TransacaoStatusLivro transacaoStatusLivro){
        return ResponseEntity.ok().body(fachada.consultar(transacaoStatusLivro));
    }

    @PostMapping("/cria")
    public ResponseEntity<?> salvar(@RequestBody TransacaoStatusLivro transacaoStatusLivro){
        return ResponseEntity.ok().body(fachada.salvar(transacaoStatusLivro));
    }
}
