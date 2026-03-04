package com.example.exemplojpa.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String titulacao;
    
    public Professor(Long id, String nome, String titulacao) {
        this.id = id;
        this.nome = nome;
        this.titulacao = titulacao;
    }
    public Professor() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getTitulacao() {
        return titulacao;
    }
    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }
    @Override
    public String toString() {
        return "Professor [id=" + id + ", nome=" + nome + ", titulacao=" + titulacao + "]";
    }

    
}
