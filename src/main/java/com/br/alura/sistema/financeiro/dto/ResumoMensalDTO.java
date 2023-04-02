package com.br.alura.sistema.financeiro.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Map;

public class ResumoMensalDTO {

    @JsonProperty("valor_total_receitas")
    private BigDecimal valorTotalReceitas;

    @JsonProperty("valor_total_despesas")
    private BigDecimal valorTotalDespesas;

    @JsonProperty("saldo_final")
    private BigDecimal saldoFinal;

    @JsonProperty("valor_total_por_categoria")
    private Map<String, BigDecimal> valorTotalPorCategoria;

    public ResumoMensalDTO(BigDecimal valorTotalReceitas, BigDecimal valorTotalDespesas, BigDecimal saldoFinal, Map<String, BigDecimal> valorTotalPorCategoria) {
        this.valorTotalReceitas = valorTotalReceitas;
        this.valorTotalDespesas = valorTotalDespesas;
        this.saldoFinal = saldoFinal;
        this.valorTotalPorCategoria = valorTotalPorCategoria;
    }
}