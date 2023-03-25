package com.br.alura.sistema.financeiro.controller;


import com.br.alura.sistema.financeiro.dto.ResumoMensalDTO;
import com.br.alura.sistema.financeiro.service.ResumoFinanceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resumo")
public class ResumoFinanceiroController {

    @Autowired
    private ResumoFinanceiroService resumoFinanceiroService;

    @GetMapping("/{ano}/{mes}")
    public ResponseEntity<?> resumoPorMes(@PathVariable Integer ano, @PathVariable Integer mes) {
        ResumoMensalDTO resumoMensalDTO = resumoFinanceiroService.obterResumoMensal(ano, mes);

        return ResponseEntity.ok(resumoMensalDTO);
    }
}
