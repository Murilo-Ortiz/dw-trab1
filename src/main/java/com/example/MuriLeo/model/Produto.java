package com.example.MuriLeo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column private String codigo;

    @Column private String descricao;

    @Column private float valor_custo;

    @Column private float valor_venda;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "produto", cascade = CascadeType.ALL)
    private List<Produto_Pedido> produtoPedidos;

    public Produto(String codigo, String descricao, float valor_custo, float valor_venda) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor_custo = valor_custo;
        this.valor_venda = valor_venda;
    }

    public Produto() {
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public float getValor_custo() {
        return valor_custo;
    }

    public float getValor_venda() {
        return valor_venda;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setValor_custo(float valor_custo) {
        this.valor_custo = valor_custo;
    }

    public void setValor_venda(float valor_venda) {
        this.valor_venda = valor_venda;
    }
}
