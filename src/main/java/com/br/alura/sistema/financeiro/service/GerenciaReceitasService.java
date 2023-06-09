package com.br.alura.sistema.financeiro.service;

import com.br.alura.sistema.financeiro.dto.ReceitaResponse;
import com.br.alura.sistema.financeiro.exception.ReceitaDuplicadaException;
import com.br.alura.sistema.financeiro.exception.ReceitaNaoEncontradaException;
import com.br.alura.sistema.financeiro.model.Receita;
import com.br.alura.sistema.financeiro.repository.ReceitaRepository;
import com.br.alura.sistema.financeiro.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class GerenciaReceitasService {

    @Autowired
    private ReceitaRepository receitaRepository;

    public Receita criarReceita(Receita receita) {
        verificaDuplicidadeDeReceitaNoMesmoMes(receita);

        return receitaRepository.save(receita);
    }

    public List<ReceitaResponse> listarReceitas(String descricao) {
        if (descricao != null) {
            return receitaRepository.findByDescricao(descricao).stream().map(ReceitaResponse::new).toList();
        }

        List<Receita> receitas = receitaRepository.findAll();

        return receitas.stream().map(ReceitaResponse::new).toList();
    }

    public ReceitaResponse detalheReceita(String id) {
        return new ReceitaResponse(this.buscarReceitaPorId(id));
    }

    public ReceitaResponse atualizarReceita(Receita receita) {
        this.buscarReceitaPorId(receita.getId());

        this.verificaDuplicidadeDeReceitaNoMesmoMes(receita);

        return new ReceitaResponse(receitaRepository.save(receita));
    }

    public void apagarReceita(String id) {
        Receita receita = this.buscarReceitaPorId(id);

        receitaRepository.deleteById(receita.getId());
    }

    public List<ReceitaResponse> listarReceitasPorMes(Integer ano, Integer mes) {
        return receitaRepository.findAll().stream().filter(r -> {
            LocalDate data = DateUtils.convertToLocalDate(r.getData());

            return data.getYear() == ano && data.getMonthValue() == mes;

        }).map(ReceitaResponse::new).toList();
    }

    private void verificaDuplicidadeDeReceitaNoMesmoMes(Receita receita) {
        var receitas = receitaRepository.findByDescricao(receita.getDescricao());

        var receitaNoMesmoMes = receitas.stream().filter(r -> r.getData().getMonth() == receita.getData().getMonth()).toList();

        if (!receitaNoMesmoMes.isEmpty()) {
            throw new ReceitaDuplicadaException();
        }
    }

    private Receita buscarReceitaPorId(String id) {
        Optional<Receita> possivelReceita = receitaRepository.findById(id);

        if(possivelReceita.isEmpty()) {
            throw new ReceitaNaoEncontradaException();
        }

        return possivelReceita.get();
    }
}
