package com.br.alura.sistema.financeiro.controller;

import com.br.alura.sistema.financeiro.dto.ReceitaRequest;
import com.br.alura.sistema.financeiro.dto.ReceitaResponse;
import com.br.alura.sistema.financeiro.model.Receita;
import com.br.alura.sistema.financeiro.service.GerenciaReceitasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/receitas")
public class GerenciarReceitasController {

    @Autowired
    private GerenciaReceitasService gerenciaReceitasService;

    @PostMapping
    public ResponseEntity<?> criarReceita(@RequestBody @Valid ReceitaRequest request) throws ParseException {
        var receita = gerenciaReceitasService.criarReceita(request.toModel());

        return new ResponseEntity(receita, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> listarReceitas(@RequestParam(required = false) String descricao) {
        List<ReceitaResponse> receitas = gerenciaReceitasService.listarReceitas(descricao);

        return ResponseEntity.ok(receitas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalheReceita(@PathVariable String id) {
        ReceitaResponse receitaResponse = gerenciaReceitasService.detalheReceita(id);

        return ResponseEntity.ok(receitaResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarReceita(@PathVariable String id, @RequestBody ReceitaRequest request) throws ParseException {
        ReceitaResponse receitaResponse = gerenciaReceitasService.atualizarReceita(request.toModel(id));

        return ResponseEntity.ok(receitaResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarReceita(@PathVariable String id){
        gerenciaReceitasService.apagarReceita(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("{ano}/{mes}")
    public ResponseEntity<?> listarReceitasPorMes(@PathVariable Integer ano, @PathVariable Integer mes) {

        List<ReceitaResponse> receitaResponses = gerenciaReceitasService.listarReceitasPorMes(ano, mes);

        return ResponseEntity.ok(receitaResponses);
    }
}
