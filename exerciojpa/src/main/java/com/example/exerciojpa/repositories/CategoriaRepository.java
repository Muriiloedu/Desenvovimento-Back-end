package com.example.exerciojpa.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.exerciojpa.models.Categoria;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class CategoriaRepository {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Categoria salvar(Categoria categoria) {
        return entityManager.merge(categoria);
    }

    public List<Categoria> listar() {
        return entityManager.createQuery(
                "FROM Categoria p", Categoria.class).getResultList();
    }

     public Categoria buscarPorId(Long id){
        return entityManager.find(Categoria.class, id);
    }

}   
