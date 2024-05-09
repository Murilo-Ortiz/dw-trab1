package com.example.MuriLeo.control;

import com.example.MuriLeo.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.MuriLeo.repository.ProdutoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/prod")
public class ProdutoController {

    @Autowired
    ProdutoRepository rep;

    //GET /prod/produto : listar todos os produtos
    @GetMapping("/produto")
    public ResponseEntity<List<Produto>> getProdutos() {
        ResponseEntity<List<Produto>> result;
        try {
            List<Produto> lp = new ArrayList<>(rep.findAll());
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

    //POST /prod/produto: cadastrar novo produto
    @PostMapping(value = "/produto", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Produto> createProduto(@RequestBody Produto produto){
        try{
            Produto _p = rep.save(new Produto(produto.getCodigo(), produto.getDescricao(), produto.getValor_custo(), produto.getValor_venda()));
            return new ResponseEntity<>(_p, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //GET /prod/produtoCod/{codigo} : listar produto por código
    @GetMapping("/produtoCod/{codigo}")
    public ResponseEntity<Produto> getProdutoByCodigo(@PathVariable("codigo") String codigo) {
        try {
            Optional<Produto> data = Optional.ofNullable(rep.findByCodigo(codigo));
            if (!data.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //PUT /prod/produto/{codigo} : atualiza produto baseado no código
    @PutMapping("/produto/{codigo}")
    public ResponseEntity<Produto> updateProduto(@PathVariable("codigo") String codigo, @RequestBody Produto produto) {
        Optional<Produto> data = Optional.ofNullable(rep.findByCodigo(codigo));
        if (data.isPresent()) {
            Produto p = data.get();
            p.setCodigo(produto.getCodigo());
            p.setDescricao(produto.getDescricao());
            p.setValor_custo(produto.getValor_custo());
            p.setValor_venda(produto.getValor_venda());

            return new ResponseEntity<>(rep.save(p), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //DELETE /prod/produto/{codigo} : deleta produto baseado no código
    @DeleteMapping("/produto/{codigo}")
    public ResponseEntity<HttpStatus> deleteProduto(@PathVariable("codigo") String codigo) {
        try {
            rep.deleteByCodigo(codigo);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //DELETE /prod/produto : deleta todos os produtos
    @DeleteMapping("/produto")
    public ResponseEntity<HttpStatus> deleteAllProdutos() {
        try {
            rep.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
