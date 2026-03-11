package com.example.exercicio.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nome;
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
    private String descricao;
    
    
    
    public Produto(long id, String name, Categoria categoria) {
        this.id = id;
        this.nome = name;
        this.categoria = categoria;
    }

    public Produto() {
    }

    public Produto(Categoria categoria) {
        this.categoria = categoria;
    }
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return nome;
    }
    public void setName(String name) {
        this.nome = name;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Produto [id=" + id + ", nome=" + nome + ", categoria=" + categoria + ", descricao=" + descricao + "]";
    }

    
}
