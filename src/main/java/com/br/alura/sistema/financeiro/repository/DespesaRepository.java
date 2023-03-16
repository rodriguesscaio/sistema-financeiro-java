package com.br.alura.sistema.financeiro.repository;

import com.br.alura.sistema.financeiro.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, String> {
    public List<Despesa> findByDescricao(String descricao);
}
