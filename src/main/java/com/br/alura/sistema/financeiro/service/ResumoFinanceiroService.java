package com.br.alura.sistema.financeiro.service;

import com.br.alura.sistema.financeiro.dto.DespesaResponse;
import com.br.alura.sistema.financeiro.dto.ReceitaResponse;
import com.br.alura.sistema.financeiro.dto.ResumoMensalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ResumoFinanceiroService {
    
    @Autowired
    private GerenciaReceitasService gerenciaReceitasService;
    
    @Autowired 
    private GerenciaDespesasService gerenciaDespesasService;

    public ResumoMensalDTO obterResumoMensal(Integer ano, Integer mes) {
        BigDecimal valorTotalReceitas = gerenciaReceitasService.listarReceitasPorMes(ano, mes).stream()
                .map(ReceitaResponse::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<DespesaResponse> despesas = gerenciaDespesasService.listarDespesasPorMes(ano, mes);

        BigDecimal valorTotalDespesas = despesas.stream().map(DespesaResponse::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal valorTotal = valorTotalReceitas.subtract(valorTotalDespesas);

        Map<String, BigDecimal> somaGastosPorCategoria = despesas.stream().collect(Collectors.groupingBy(DespesaResponse::getCategoria, Collectors.mapping(DespesaResponse::getValor, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));

        return new ResumoMensalDTO(valorTotalReceitas, valorTotalDespesas, valorTotal, somaGastosPorCategoria);
    }
}
