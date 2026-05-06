package com.example.ac2.services;

import com.example.ac2.dtos.*;
import com.example.ac2.exceptions.RegraNegocioException;
import com.example.ac2.models.Funcionario;
import com.example.ac2.models.Projeto;
import com.example.ac2.repositories.FuncionarioRepository;
import com.example.ac2.repositories.ProjetoRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
public class ProjetoServiceImpl implements ProjetoService {

    private final ProjetoRepository projetoRepository;
    private final FuncionarioRepository funcionarioRepository;

    public ProjetoServiceImpl(ProjetoRepository projetoRepository,
                               FuncionarioRepository funcionarioRepository) {
        this.projetoRepository = projetoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    @Transactional
    public DadosProjetoDTO adicionar(ProjetoDTO dto) {
        if (dto.getDescricao() == null || dto.getDescricao().isBlank()) {
            throw new RegraNegocioException("A descrição do projeto é obrigatória.");
        }
        if (dto.getDataInicio() == null) {
            throw new RegraNegocioException("A data de início do projeto é obrigatória.");
        }
        if (dto.getDataFim() != null && dto.getDataFim().isBefore(dto.getDataInicio())) {
            throw new RegraNegocioException("A data de fim não pode ser anterior à data de início.");
        }

        Projeto projeto = Projeto.builder()
                .descricao(dto.getDescricao())
                .dataInicio(dto.getDataInicio())
                .dataFim(dto.getDataFim())
                .build();
        projeto = projetoRepository.save(projeto);

        return toDTO(projeto);
    }

    @Override
    public DadosProjetoDTO buscarProjetoPorId(Long id) {
        Projeto projeto = projetoRepository.findProjetoFetchFuncionarios(id)
                .orElseThrow(() -> new RegraNegocioException("Projeto não encontrado com id: " + id));
        return toDTO(projeto);
    }

    @Override
    @Transactional
    public void vincularFuncionario(Long idProjeto, Long idFuncionario) {
        Projeto projeto = projetoRepository.findProjetoFetchFuncionarios(idProjeto)
                .orElseThrow(() -> new RegraNegocioException("Projeto não encontrado com id: " + idProjeto));

        Funcionario funcionario = funcionarioRepository.findById(idFuncionario)
                .orElseThrow(() -> new RegraNegocioException("Funcionário não encontrado com id: " + idFuncionario));

        boolean jaVinculado = projeto.getFuncionarios().stream()
                .anyMatch(f -> f.getId().equals(idFuncionario));
        if (jaVinculado) {
            throw new RegraNegocioException("Funcionário já está vinculado a este projeto.");
        }

        projeto.getFuncionarios().add(funcionario);
        projetoRepository.save(projeto);
    }

    private DadosProjetoDTO toDTO(Projeto projeto) {
        return DadosProjetoDTO.builder()
                .id(projeto.getId())
                .descricao(projeto.getDescricao())
                .dataInicio(projeto.getDataInicio())
                .dataFim(projeto.getDataFim())
                .funcionarioIds(projeto.getFuncionarios().stream().map(f -> f.getId()).collect(Collectors.toList()))
                .build();
    }
}
