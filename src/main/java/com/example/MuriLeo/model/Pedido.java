package com.example.MuriLeo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pedido")

public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column private int id_funcionario;

    @Column private int id_cliente;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column private LocalDate data_pedido;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column private LocalDate data_remessa;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<Produto_Pedido> produtoPedidos;

    public Pedido(int id_funcionario, int id_cliente, LocalDate data_pedido, LocalDate data_remessa) {
        this.id_funcionario = id_funcionario;
        this.id_cliente = id_cliente;
        this.data_pedido = data_pedido;
        this.data_remessa = data_remessa;
    }

    public Pedido() {
    }

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public int getId_pedido() {
        return id;
    }

    public void setId_pedido(int id_pedido) {
        this.id = id_pedido;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public LocalDate getData_pedido() {
        return data_pedido;
    }

    public void setData_pedido(LocalDate data_pedido) {
        this.data_pedido = data_pedido;
    }

    public LocalDate getData_remessa() {
        return data_remessa;
    }

    public void setData_remessa(LocalDate data_remessa) {
        this.data_remessa = data_remessa;
    }
}
