package com.example.ac2.repositories;

import com.example.ac2.models.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    //Retorna projeto com lista de funcionários vinculados (JOIN FETCH resolve Lazy)
    @Query("SELECT p FROM Projeto p LEFT JOIN FETCH p.funcionarios WHERE p.id = :id")
    Optional<Projeto> findProjetoFetchFuncionarios(@Param("id") Long id);

    // b. Projetos com dataInicio entre início e fim
    List<Projeto> findByDataInicioBetween(LocalDate inicio, LocalDate fim);
}
