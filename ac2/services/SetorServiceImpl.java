package com.example.ac2.services;

import com.example.ac2.dtos.DadosFuncionarioDTO;
import com.example.ac2.dtos.DadosSetorDTO;
import com.example.ac2.dtos.SetorDTO;
import com.example.ac2.exceptions.RegraNegocioException;
import com.example.ac2.models.Setor;
import com.example.ac2.repositories.SetorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class SetorServiceImpl implements SetorService {

    private final SetorRepository setorRepository;

    public SetorServiceImpl(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    @Override
    @Transactional
    public DadosSetorDTO adicionar(SetorDTO dto) {
        if (dto.getNome() == null || dto.getNome().isBlank()) {
            throw new RegraNegocioException("O nome do setor é obrigatório.");
        }
        Setor setor = Setor.builder()
                .nome(dto.getNome())
                .build();
        setor = setorRepository.save(setor);
        return toDTO(setor);
    }

    @Override
    public DadosSetorDTO buscarPorId(Long id) {
        Setor setor = setorRepository.findSetorFetchFuncionarios(id)
                .orElseThrow(() -> new RegraNegocioException("Setor não encontrado com id: " + id));
        return toDTO(setor);
    }

    private DadosSetorDTO toDTO(Setor setor) {
        return DadosSetorDTO.builder()
                .id(setor.getId())
                .nome(setor.getNome())
                .funcionarioIds(setor.getFuncionarios().stream().map(f -> f.getId()).collect(Collectors.toList()))
                .build();
    }
}