package com.br.alura.sistema.financeiro.service;

import com.br.alura.sistema.financeiro.dto.DespesaResponse;
import com.br.alura.sistema.financeiro.exception.DespesaDuplicadaException;
import com.br.alura.sistema.financeiro.exception.DespesaNaoEncontradaException;
import com.br.alura.sistema.financeiro.model.Despesa;
import com.br.alura.sistema.financeiro.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GerenciaDespesasService {

    @Autowired
    private DespesaRepository despesaRepository;

    public Despesa criarDespesa(Despesa despesa) {
        this.verificaDuplicidadeDeDespesaNoMesmoMes(despesa);

        return despesaRepository.save(despesa);
    }

    public List<DespesaResponse> listarDespesas() {
        List<Despesa> despesas = despesaRepository.findAll();

        return despesas.stream().map(DespesaResponse::new).toList();
    }

    public DespesaResponse detalheDespesa(String id) {
        return new DespesaResponse(this.buscarDespesaPorId(id));
    }

    public DespesaResponse atualizarDespesa(Despesa despesa) {
        this.buscarDespesaPorId(despesa.getId());

        this.verificaDuplicidadeDeDespesaNoMesmoMes(despesa);

        return new DespesaResponse(despesaRepository.save(despesa));
    }

    public void apagarDespesa(String id) {
        Despesa despesa = this.buscarDespesaPorId(id);

        despesaRepository.deleteById(despesa.getId());
    }

    private void verificaDuplicidadeDeDespesaNoMesmoMes(Despesa despesa) {
        var despesas = despesaRepository.findByDescricao(despesa.getDescricao());

        var despesaNoMesmoMes = despesas.stream().filter(d -> d.getData().getMonth() == despesa.getData().getMonth()).toList();

        if (!despesaNoMesmoMes.isEmpty()) {
            throw new DespesaDuplicadaException();
        }
    }

    private Despesa buscarDespesaPorId(String id) {
        Optional<Despesa> possivelDespesa = despesaRepository.findById(id);

        if(possivelDespesa.isEmpty()) {
            throw new DespesaNaoEncontradaException();
        }

        return possivelDespesa.get();
    }
}
