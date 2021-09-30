package com.watashi.bookstore.endpoint;

import com.watashi.bookstore.dto.PedidoDTO;
import com.watashi.bookstore.util.DTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.watashi.bookstore.domain.Pedido;
import com.watashi.bookstore.domain.Resultado;
import com.watashi.bookstore.facade.Fachada;

@RestController
@CrossOrigin
@RequestMapping("/pedidos")
public class PedidoEndpoint {
    
    @Autowired
    private Fachada fachada;

    @Autowired
    private PedidoDTO pedidoDTO;

    @PostMapping()
    public ResponseEntity<Resultado> consultar(@RequestBody Pedido pedido){
        return ResponseEntity.ok().body(DTOUtil.tranfereParaDTO(fachada.consultar(pedido), pedidoDTO));
    }

    @PostMapping(path = "/cria")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> salvar(@RequestBody Pedido pedido){
        return ResponseEntity.ok().body(fachada.salvar(pedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Pedido pedido = new Pedido();
        pedido.setId(id);
        return ResponseEntity.ok().body(fachada.excluir(pedido));
    }

    @PutMapping()
    public ResponseEntity<?> alterar(@RequestBody Pedido pedido){
        return ResponseEntity.ok().body(fachada.alterar(pedido));
    }
}
