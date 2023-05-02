package com.br.alura.sistema.financeiro.controller;

import com.br.alura.sistema.financeiro.dto.DespesaRequest;
import com.br.alura.sistema.financeiro.dto.DespesaResponse;
import com.br.alura.sistema.financeiro.service.GerenciaDespesasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/despesas")
public class GerenciarDespesasController {

    @Autowired
    private GerenciaDespesasService gerenciaDespesasService;

    @PostMapping
    public ResponseEntity<?> criarReceita(@RequestBody @Valid DespesaRequest request) throws ParseException {
        var despesa = gerenciaDespesasService.criarDespesa(request.toModel());

        return new ResponseEntity(despesa, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DespesaResponse>> listarDespesas(@RequestParam(required = false) String descricao) {
        List<DespesaResponse> despesas = gerenciaDespesasService.listarDespesas(descricao);

        return ResponseEntity.ok(despesas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DespesaResponse> detalheDespesa(@PathVariable String id) {
        DespesaResponse despesaResponse = gerenciaDespesasService.detalheDespesa(id);

        return ResponseEntity.ok(despesaResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarDespesa(@PathVariable String id, @RequestBody DespesaRequest request) throws ParseException {
        DespesaResponse despesaResponse = gerenciaDespesasService.atualizarDespesa(request.toModel(id));

        return ResponseEntity.ok(despesaResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarDespesa(@PathVariable String id){
        gerenciaDespesasService.apagarDespesa(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{ano}/{mes}")
    public ResponseEntity<?> listarDespesasPorMes(@PathVariable Integer ano, @PathVariable Integer mes){
        List<DespesaResponse> despesaResponses = gerenciaDespesasService.listarDespesasPorMes(ano, mes);

        return ResponseEntity.ok(despesaResponses);
    }
}
