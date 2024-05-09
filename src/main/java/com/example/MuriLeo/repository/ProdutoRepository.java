package com.example.MuriLeo.repository;

import com.example.MuriLeo.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.MuriLeo.model.Pedido;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Produto findByCodigo(String codigo);
    List<Produto> findByDescricaoContaining(String descricao);
    void deleteByCodigo(String codigo);
}
