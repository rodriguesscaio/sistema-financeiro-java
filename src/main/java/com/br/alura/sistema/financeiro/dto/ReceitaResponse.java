package com.br.alura.sistema.financeiro.dto;

import com.br.alura.sistema.financeiro.model.Receita;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

public class ReceitaResponse {

    @JsonProperty
    private String descricao;
    @JsonProperty
    private BigDecimal valor;
    @JsonProperty
    private String data;

    public ReceitaResponse(Receita receita) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.descricao = receita.getDescricao();
        this.valor = receita.getValor();
        this.data = simpleDateFormat.format(receita.getData());
    }
}
