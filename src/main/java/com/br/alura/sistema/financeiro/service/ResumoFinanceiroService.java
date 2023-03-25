package com.br.alura.sistema.financeiro.service;

import com.br.alura.sistema.financeiro.dto.DespesaResponse;
import com.br.alura.sistema.financeiro.dto.ReceitaResponse;
import com.br.alura.sistema.financeiro.dto.ResumoMensalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ResumoFinanceiroService {
    
    @Autowired
    private GerenciaReceitasService gerenciaReceitasService;
    
    @Autowired 
    private GerenciaDespesasService gerenciaDespesasService;

    public ResumoMensalDTO obterResumoMensal(Integer ano, Integer mes) {
        List<ReceitaResponse> receitas = gerenciaReceitasService.listarReceitasPorMes(ano, mes);
        List<DespesaResponse> despesas = gerenciaDespesasService.listarDespesasPorMes(ano, mes);

        BigDecimal totalReceitas = BigDecimal.valueOf(0.0);
        for (ReceitaResponse receita: receitas){
            totalReceitas = totalReceitas.add(receita.getValor());
        }

        BigDecimal valorTotalDespesas = BigDecimal.valueOf(0.0);
        for (DespesaResponse despesa: despesas){
            valorTotalDespesas = valorTotalDespesas.add(despesa.getValor());
        }

        BigDecimal valorTotal = totalReceitas.subtract(valorTotalDespesas);

        return new ResumoMensalDTO(totalReceitas, valorTotalDespesas, valorTotal);
    }
}
