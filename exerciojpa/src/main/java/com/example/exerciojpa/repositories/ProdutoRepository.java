package com.example.exerciojpa.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.exerciojpa.models.Produto;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class ProdutoRepository {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Produto salvar(Produto produto) {
        return entityManager.merge(produto);
    }

    public List<Produto> listar() {
        return entityManager.createQuery(
                "FROM Produto p", Produto.class).getResultList();
    }
}
