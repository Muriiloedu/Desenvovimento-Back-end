package com.example.ac2.services;

import com.example.ac2.dtos.DadosSetorDTO;
import com.example.ac2.dtos.SetorDTO;

public interface SetorService {
    DadosSetorDTO adicionar(SetorDTO dto);
    DadosSetorDTO buscarPorId(Long id);
}