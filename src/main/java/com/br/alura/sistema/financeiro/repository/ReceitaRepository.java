package com.br.alura.sistema.financeiro.repository;

import com.br.alura.sistema.financeiro.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, String> {
    public List<Receita> findByDescricao(String descricao);
}
