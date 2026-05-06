package com.example.ac2.services;

import com.example.ac2.dtos.*;
import com.example.ac2.exceptions.RegraNegocioException;
import com.example.ac2.models.Funcionario;
import com.example.ac2.models.Setor;
import com.example.ac2.repositories.FuncionarioRepository;
import com.example.ac2.repositories.SetorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final SetorRepository setorRepository;

    public FuncionarioServiceImpl(FuncionarioRepository funcionarioRepository,
                                  SetorRepository setorRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.setorRepository = setorRepository;
    }

    @Override
    @Transactional
    public DadosFuncionarioDTO adicionar(FuncionarioDTO dto) {
        if (dto.getNome() == null || dto.getNome().isBlank()) {
            throw new RegraNegocioException("O nome do funcionário é obrigatório.");
        }
        if (dto.getEmail() == null || dto.getEmail().isBlank()) {
            throw new RegraNegocioException("O e-mail do funcionário é obrigatório.");
        }
        funcionarioRepository.findByEmail(dto.getEmail()).ifPresent(f -> {
            throw new RegraNegocioException("Já existe um funcionário com o e-mail: " + dto.getEmail());
        });

        Setor setor = null;
        if (dto.getSetorId() != null) {
            setor = setorRepository.findById(dto.getSetorId())
                    .orElseThrow(() -> new RegraNegocioException("Setor não encontrado com id: " + dto.getSetorId()));
        }

        Funcionario funcionario = Funcionario.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .cargo(dto.getCargo())
                .setor(setor)
                .build();
        funcionario = funcionarioRepository.save(funcionario);

        return DadosFuncionarioDTO.builder()
                .id(funcionario.getId())
                .nome(funcionario.getNome())
                .email(funcionario.getEmail())
                .cargo(funcionario.getCargo())
                .setorId(setor != null ? setor.getId() : null)
                .projetoIds(Collections.emptyList())
                .build();
    }

    @Override
    public List<DadosProjetoDTO> buscarProjetos(Long idFuncionario) {
        Funcionario funcionario = funcionarioRepository.findFuncionarioFetchProjetos(idFuncionario)
                .orElseThrow(() -> new RegraNegocioException("Funcionário não encontrado com id: " + idFuncionario));

        if (funcionario.getProjetos() == null) return Collections.emptyList();

        return funcionario.getProjetos().stream()
                .map(p -> DadosProjetoDTO.builder()
                        .id(p.getId())
                        .descricao(p.getDescricao())
                        .dataInicio(p.getDataInicio())
                        .dataFim(p.getDataFim())
                        .funcionarioIds(Collections.emptyList()) // evita recursão
                        .build())
                .collect(Collectors.toList());
    }
}
