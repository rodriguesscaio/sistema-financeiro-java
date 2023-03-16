package com.br.alura.sistema.financeiro.dto;

import com.br.alura.sistema.financeiro.model.Receita;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class ReceitaRequest {
    @JsonProperty
    @NotNull
    @NotBlank
    private String descricao;
    @JsonProperty
    @NotNull
    private BigDecimal valor;
    @JsonProperty
    @NotNull
    @NotBlank
    private String data;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public Receita toModel() throws ParseException {
        return new Receita(UUID.randomUUID().toString(), descricao, valor, this.simpleDateFormat.parse(data));
    }

    public Receita toModel(String id) throws ParseException {
        return new Receita(id, descricao, valor, this.simpleDateFormat.parse(data));
    }
}
