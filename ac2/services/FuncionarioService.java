package com.example.ac2.services;

import com.example.ac2.dtos.DadosFuncionarioDTO;
import com.example.ac2.dtos.DadosProjetoDTO;
import com.example.ac2.dtos.FuncionarioDTO;
import java.util.List;

public interface FuncionarioService {
    DadosFuncionarioDTO adicionar(FuncionarioDTO dto);
    List<DadosProjetoDTO> buscarProjetos(Long idFuncionario);
}
    