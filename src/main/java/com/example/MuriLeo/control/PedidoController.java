package com.example.MuriLeo.control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.MuriLeo.model.Pedido;
import com.example.MuriLeo.repository.PedidoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ped")
public class PedidoController {

    @Autowired
    PedidoRepository rep;

    //GET /ped/pedido : listar todos os pedidos
    @GetMapping("/pedido")
    public ResponseEntity<List<Pedido>> getPedidos() {
        ResponseEntity<List<Pedido>> result;
        try {
            List<Pedido> lp = new ArrayList<>(rep.findAll());
            if (lp.isEmpty()) {
                result = new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                result = new ResponseEntity<>(lp, HttpStatus.OK);
            }
        } catch (Exception e) {
            result = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    //POST /ped/pedido: cadastrar novo pedido
    @PostMapping(value = "/pedido", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pedido> createPedido(@RequestBody Pedido ped) {
        try {
            Pedido _p = rep.save(new Pedido(ped.getId_funcionario(), ped.getId_cliente(), ped.getData_pedido(), ped.getData_remessa()));
            return new ResponseEntity<>(_p, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //GET /ped/pedidoid/{id} : listar pedido por id
    @GetMapping("/pedidoid/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable("id") long id) {
        try {
            Optional<Pedido> data = rep.findById(id);
            if (!data.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //PUT /ped/pedido/{id_pedido} : atualiza pedido baseado no id_pedido
    @PutMapping("/pedido/{id_pedido}")
    public ResponseEntity<Pedido> updatePedido(@PathVariable("id_pedido") long id_pedido, @RequestBody Pedido pedido) {
        Optional<Pedido> data = rep.findById(id_pedido);
        if (data.isPresent()) {
            Pedido p = data.get();
            p.setId_funcionario(pedido.getId_funcionario());
            p.setId_cliente(pedido.getId_cliente());
            p.setData_pedido(pedido.getData_pedido());
            p.setData_remessa(pedido.getData_remessa());

            return new ResponseEntity<>(rep.save(p), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //DELETE /ped/pedido/{id_pedido} : deleta pedido baseado no id_pedido
    @DeleteMapping("/pedido/{id_pedido}")
    public ResponseEntity<HttpStatus> deletePedido(@PathVariable("id_pedido") long id_pedido) {
        try {
            rep.deleteById(id_pedido);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //DELETE /ped/pedido : deleta todos os pedidos
    @DeleteMapping("/pedido")
    public ResponseEntity<HttpStatus> deleteAllPedidos() {
        try {
            rep.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

