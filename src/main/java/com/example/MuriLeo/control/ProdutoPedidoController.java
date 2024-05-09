package com.example.MuriLeo.control;

import com.example.MuriLeo.model.Produto_Pedido;
import com.example.MuriLeo.repository.ProdutoPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produto-pedido")
public class ProdutoPedidoController {

    @Autowired
    ProdutoPedidoRepository produtoPedidoRepository;

    // GET /produto-pedido
    @GetMapping
    public ResponseEntity<List<Produto_Pedido>> getAllProdutoPedidos() {
        List<Produto_Pedido> produtoPedidos = produtoPedidoRepository.findAll();
        if (produtoPedidos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(produtoPedidos, HttpStatus.OK);
    }

    // GET /produto-pedido/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Produto_Pedido> getProdutoPedidoById(@PathVariable("id") long id) {
        Optional<Produto_Pedido> produtoPedido = produtoPedidoRepository.findById(id);
        return produtoPedido.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST /produto-pedido
    @PostMapping
    public ResponseEntity<Produto_Pedido> createProdutoPedido(@RequestBody Produto_Pedido produtoPedido) {
        try {
            Produto_Pedido _produtoPedido = produtoPedidoRepository.save(produtoPedido);
            return new ResponseEntity<>(_produtoPedido, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PUT /produto-pedido/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Produto_Pedido> updateProdutoPedido(@PathVariable("id") long id, @RequestBody Produto_Pedido produtoPedido) {
        Optional<Produto_Pedido> produtoPedidoData = produtoPedidoRepository.findById(id);
        if (produtoPedidoData.isPresent()) {
            Produto_Pedido _produtoPedido = produtoPedidoData.get();
            _produtoPedido.setQuantidade(produtoPedido.getQuantidade());
            _produtoPedido.setPrecoUnitario(produtoPedido.getPrecoUnitario());
            _produtoPedido.setDesconto(produtoPedido.getDesconto());
            _produtoPedido.setProduto(produtoPedido.getProduto());
            _produtoPedido.setPedido(produtoPedido.getPedido());
            return new ResponseEntity<>(produtoPedidoRepository.save(_produtoPedido), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE /produto-pedido/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProdutoPedido(@PathVariable("id") long id) {
        try {
            produtoPedidoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE /produto-pedido
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllProdutoPedidos() {
        try {
            produtoPedidoRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
