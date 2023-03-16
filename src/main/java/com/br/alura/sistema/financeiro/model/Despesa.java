package com.br.alura.sistema.financeiro.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "despesas")
public class Despesa {
    @Id
    @JsonProperty
    private String id;
    @JsonProperty
    private String descricao;
    @JsonProperty
    private BigDecimal valor;
    @JsonProperty String categoria;
    @JsonProperty
    private Date data;

    public Despesa(){}

    public Despesa(String id, String descricao, BigDecimal valor, String categoria, Date data) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public Date getData() {
        return data;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getId() {
        return id;
    }

    public String getCategoria() {
        return categoria;
    }
}
