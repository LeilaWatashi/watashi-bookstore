package com.watashi.bookstore.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.watashi.bookstore.domain.Item;
import com.watashi.bookstore.domain.Resultado;
import com.watashi.bookstore.facade.Fachada;

@RestController
@CrossOrigin
@RequestMapping("/itens")
public class ItemEndpoint {
    
    @Autowired
    private Fachada fachada;

    @DeleteMapping("/{id}")
    public ResponseEntity<Resultado> delete(@PathVariable Integer id){
        Item item = new Item();
        item.setId(id);
        return ResponseEntity.ok().body(fachada.excluir(item));
    }

    @PutMapping()
    public ResponseEntity<Resultado> update(@RequestBody Item item){
        return ResponseEntity.ok().body(fachada.alterar(item));
    }
}
