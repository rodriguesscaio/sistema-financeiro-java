package com.br.alura.sistema.financeiro.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ResumoMensalDTO {

    @JsonProperty("valor_total_receitas")
    private BigDecimal valorTotalReceitas;

    @JsonProperty("valor_total_despesas")
    private BigDecimal valorTotalDespesas;

    @JsonProperty("saldo_final")
    private BigDecimal saldoFinal;

    public ResumoMensalDTO(BigDecimal valorTotalReceitas, BigDecimal valorTotalDespesas, BigDecimal saldoFinal) {
        this.valorTotalReceitas = valorTotalReceitas;
        this.valorTotalDespesas = valorTotalDespesas;
        this.saldoFinal = saldoFinal;
    }
}