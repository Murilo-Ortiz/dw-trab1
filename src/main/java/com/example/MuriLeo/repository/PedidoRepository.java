package com.example.MuriLeo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.MuriLeo.model.Pedido;
import java.time.LocalDate;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
