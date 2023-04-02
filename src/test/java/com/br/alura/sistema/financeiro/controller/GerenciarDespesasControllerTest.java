package com.br.alura.sistema.financeiro.controller;

import com.br.alura.sistema.financeiro.dto.DespesaRequest;
import com.br.alura.sistema.financeiro.dto.DespesaResponse;
import com.br.alura.sistema.financeiro.model.Despesa;
import com.br.alura.sistema.financeiro.service.GerenciaDespesasService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GerenciarDespesasControllerTest {

    @InjectMocks
    private GerenciarDespesasController gerenciarDespesasController;
    @Mock
    private GerenciaDespesasService gerenciaDespesasService;

    @Test
    public void test_deve_criar_uma_despesa() throws ParseException {

        ResponseEntity<?> responseEntity = gerenciarDespesasController.criarReceita(createDespesaRequest());

        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        System.out.println(responseEntity);
    }

    private DespesaRequest createDespesaRequest() {
        return new DespesaRequest(
                "teste",
                BigDecimal.ZERO,
                "04/05/2023",
                ""
        );
    }
}