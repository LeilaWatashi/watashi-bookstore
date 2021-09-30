package com.watashi.bookstore.endpoint;

import com.watashi.bookstore.domain.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.watashi.bookstore.domain.Resultado;
import com.watashi.bookstore.dto.LivroDTO;
import com.watashi.bookstore.facade.Fachada;
import com.watashi.bookstore.util.DTOUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/livros")
public class LivroEndpoint {
    
    @Autowired
    private Fachada fachada;

    @Autowired
    private LivroDTO livroDTO;

    @Autowired
    private Resultado resultado;

    @PostMapping()
    public ResponseEntity<Resultado> consultar(@RequestBody Livro livro){
        resultado = fachada.consultar(livro);
        if(!resultado.getEntidades().isEmpty()){
            List<Livro> livros = new ArrayList<>();
            resultado.getEntidades().forEach(entidadeDominio -> livros.add((Livro) entidadeDominio));
            Collections.sort(livros);
            resultado.setEntidades(new ArrayList<>());
            livros.forEach( cartaOrdenada -> resultado.addEntidade(cartaOrdenada));
        }
        return ResponseEntity.ok().body(resultado);
    }

    @PostMapping(path = "/cria")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> salvar(@RequestBody Livro livro){
        return ResponseEntity.ok().body(DTOUtil.tranfereParaDTO(fachada.salvar(livro), livroDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Livro livro = new Livro();
        livro.setId(id);
        return ResponseEntity.ok().body(fachada.excluir(livro));
    }

    @PutMapping()
    public ResponseEntity<?> alterar(@RequestBody Livro livro){
        return ResponseEntity.ok().body(fachada.alterar(livro));
    }
}
