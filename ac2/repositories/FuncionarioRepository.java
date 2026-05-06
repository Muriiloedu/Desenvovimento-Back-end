package com.example.ac2.repositories;

import com.example.ac2.models.Funcionario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    // c. Todos os projetos vinculados ao funcionário (retornamos o Funcionario com projetos)
    @Query("SELECT f FROM Funcionario f LEFT JOIN FETCH f.projetos WHERE f.id = :id")
    Optional<Funcionario> findFuncionarioFetchProjetos(@Param("id") Long id);

    Optional<Funcionario> findByEmail(String email);
}
