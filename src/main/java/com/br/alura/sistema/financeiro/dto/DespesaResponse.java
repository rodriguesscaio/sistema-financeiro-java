package com.br.alura.sistema.financeiro.dto;

import com.br.alura.sistema.financeiro.model.Despesa;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

public class DespesaResponse {
    @JsonProperty
    private String descricao;
    @JsonProperty
    private BigDecimal valor;

    @JsonProperty
    private String categoria;
    @JsonProperty
    private String data;

    public DespesaResponse(Despesa despesa) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.descricao = despesa.getDescricao();
        this.valor = despesa.getValor();
        this.categoria = despesa.getCategoria();
        this.data = simpleDateFormat.format(despesa.getData());
    }

    public DespesaResponse(String descricao, BigDecimal valor, String categoria, String data) {
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
        this.data = data;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getCategoria() {
        return categoria;
    }
}
