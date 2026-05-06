package com.example.ac2.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String nome;

    @Column(length = 500)
    private String descricao;

    @Column(nullable= false)
    private  LocalDate dataInicio;

    private LocalDate dataFim;

    private String status;

    @ManyToMany
    @JoinTable(
        name= "projeto_funcionario",
        joinColumns= @JoinColumn(name= "projeto_id"), inverseJoinColumns= @JoinColumn(name = "funcionario_id")
    )
    @Builder.Default
    private List<Funcionario> funcionarios = new ArrayList<>(); 



}
