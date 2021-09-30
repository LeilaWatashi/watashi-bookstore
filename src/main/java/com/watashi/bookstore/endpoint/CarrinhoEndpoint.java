package com.watashi.bookstore.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.watashi.bookstore.domain.Carrinho;
import com.watashi.bookstore.domain.Livro;
import com.watashi.bookstore.domain.Resultado;
import com.watashi.bookstore.dto.CarrinhoDTO;
import com.watashi.bookstore.dto.LivroDTO;
import com.watashi.bookstore.facade.Fachada;
import com.watashi.bookstore.util.DTOUtil;

@RestController
@CrossOrigin
@RequestMapping("/carrinhos")
public class CarrinhoEndpoint {
    
    @Autowired
    private Fachada fachada;

    @Autowired
    private CarrinhoDTO carrinhoDTO;

    @PostMapping()
    public ResponseEntity<Resultado> consultar(@RequestBody Carrinho carrinho){
        return ResponseEntity.ok().body(DTOUtil.tranfereParaDTO(fachada.consultar(carrinho),carrinhoDTO));
    }

    @PostMapping(path = "/cria")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> salvar(@RequestBody Carrinho carrinho){
        return ResponseEntity.ok().body(DTOUtil.tranfereParaDTO(fachada.salvar(carrinho), carrinhoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Carrinho carrinho = new Carrinho();
        carrinho.setId(id);
        return ResponseEntity.ok().body(DTOUtil.tranfereParaDTO(fachada.excluir(carrinho), carrinhoDTO));
    }

    @PutMapping()
    public ResponseEntity<?> alterar(@RequestBody Carrinho carrinho){
        return ResponseEntity.ok().body(DTOUtil.tranfereParaDTO(fachada.alterar(carrinho), carrinhoDTO));
    }
}
