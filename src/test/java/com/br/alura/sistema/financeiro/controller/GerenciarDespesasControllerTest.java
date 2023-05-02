package com.br.alura.sistema.financeiro.controller;

import com.br.alura.sistema.financeiro.dto.DespesaRequest;
import com.br.alura.sistema.financeiro.dto.DespesaResponse;
import com.br.alura.sistema.financeiro.service.GerenciaDespesasService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

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
    }

    @Test
    public void test_deve_listar_despesas() {
        Mockito.when(gerenciaDespesasService.listarDespesas(Mockito.anyString())).thenReturn(List.of(createDespesaResponse()));

        ResponseEntity<List<DespesaResponse>> responseEntity = gerenciarDespesasController.listarDespesas("");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, Objects.requireNonNull(responseEntity.getBody()).size());
        assertEquals(DespesaResponse.class, responseEntity.getBody().get(0).getClass());
    }

    @Test
    public void test_deve_retornar_despesa_especifica_por_id() {
        String id = UUID.randomUUID().toString();

        Mockito.when(gerenciaDespesasService.detalheDespesa(id)).thenReturn(createDespesaResponse());

        ResponseEntity<DespesaResponse> responseEntity = gerenciarDespesasController.detalheDespesa(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(BigDecimal.ZERO, Objects.requireNonNull(responseEntity.getBody()).getValor());
    }

    private DespesaRequest createDespesaRequest() {
        return new DespesaRequest(
                "teste",
                BigDecimal.ZERO,
                "04/05/2023",
                ""
        );
    }

    private DespesaResponse createDespesaResponse() {
        return new DespesaResponse(
                "teste",
                BigDecimal.ZERO,
                "",
                "04/05/2023"
        );
    }
}