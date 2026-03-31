package com.example.att2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.att2.models.Diretor;

public interface DiretorRepository extends JpaRepository<Diretor, Long> {
    
}
