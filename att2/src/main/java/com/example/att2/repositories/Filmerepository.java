package com.example.att2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.att2.models.Diretor;
import com.example.att2.models.Filme;

public interface Filmerepository extends JpaRepository<Filme, Long> {
    List<Filme> findByDuracaoGreaterThanEqual(Integer duracao);
    List<Filme> findByDiretor(Diretor diretor);
    List<Filme> findByTituloLike(String titulo);
}
