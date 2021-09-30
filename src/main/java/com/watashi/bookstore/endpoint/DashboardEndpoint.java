package com.watashi.bookstore.endpoint;


import com.watashi.bookstore.domain.Resultado;
import com.watashi.bookstore.domain.grafico.Dashboard;
import com.watashi.bookstore.facade.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/dashboard")
public class DashboardEndpoint {
    
    @Autowired
    private Fachada fachada;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Resultado> consultar(@RequestBody Dashboard dashboard){
        return ResponseEntity.ok().body(fachada.consultar(dashboard));
    }
}
