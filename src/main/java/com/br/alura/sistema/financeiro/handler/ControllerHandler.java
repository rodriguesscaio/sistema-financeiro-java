package com.br.alura.sistema.financeiro.handler;

import com.br.alura.sistema.financeiro.exception.DespesaDuplicadaException;
import com.br.alura.sistema.financeiro.exception.DespesaNaoEncontradaException;
import com.br.alura.sistema.financeiro.exception.ReceitaDuplicadaException;
import com.br.alura.sistema.financeiro.exception.ReceitaNaoEncontradaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerHandler {

    @ExceptionHandler(ReceitaDuplicadaException.class)
    public ResponseEntity<?> receitaDuplicada(ReceitaDuplicadaException exception, WebRequest webRequest) {
        Map<String, String> error = new HashMap<>();

        error.put("message", "Receita já existente neste mes.");

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(ReceitaNaoEncontradaException.class)
    public ResponseEntity<?> receitaNaoEncontrada(ReceitaNaoEncontradaException exception, WebRequest webRequest) {
        Map<String, String> error = new HashMap<>();

        error.put("message", "Receita nao encontrada.");

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DespesaDuplicadaException.class)
    public ResponseEntity<?> receitaDuplicada(DespesaDuplicadaException exception, WebRequest webRequest) {
        Map<String, String> error = new HashMap<>();

        error.put("message", "Despesa já existente neste mes.");

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(DespesaNaoEncontradaException.class)
    public ResponseEntity<?> despesaNaoEncontrada(DespesaNaoEncontradaException exception, WebRequest webRequest) {
        Map<String, String> error = new HashMap<>();

        error.put("message", "Despesa nao encontrada.");

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
