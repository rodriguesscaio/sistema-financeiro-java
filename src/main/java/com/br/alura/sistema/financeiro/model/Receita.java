package com.br.alura.sistema.financeiro.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Receita {
    @Id
    @JsonProperty
    private String id;
    @JsonProperty
    private String descricao;
    @JsonProperty
    private BigDecimal valor;
    @JsonProperty
    private Date data;

    public Receita(){}

    public Receita(String id, String descricao, BigDecimal valor, Date data) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
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
}
