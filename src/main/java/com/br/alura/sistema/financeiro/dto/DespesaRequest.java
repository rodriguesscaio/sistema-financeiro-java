package com.br.alura.sistema.financeiro.dto;

import com.br.alura.sistema.financeiro.model.Despesa;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

public class DespesaRequest {
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
    @JsonProperty
    private String categoria;

    public DespesaRequest(String descricao, BigDecimal valor, String data, String categoria) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.categoria = categoria;
    }

    private List<String> categorias = List.of("Alimentação", "Saúde", "Moradia", "Transporte", "Educação", "Lazer", "Imprevistos");

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public Despesa toModel() throws ParseException {
        return new Despesa(descricao, valor, this.obterCategoria(), this.simpleDateFormat.parse(data));
    }

    public Despesa toModel(String id) throws ParseException {
        return new Despesa(id, descricao, valor, this.obterCategoria(), this.simpleDateFormat.parse(data));
    }

    private String obterCategoria() {
        String categoriaEscolhida = "Other";

        if (categoria != null && categorias.contains(categoria)) {
            return categoria;
        }

        return categoriaEscolhida;
    }
}
