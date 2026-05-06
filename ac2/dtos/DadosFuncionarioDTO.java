package com.example.ac2.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DadosFuncionarioDTO {
    private Long id;
    private String nome;
    private String email;
    private String cargo;
    private Long setorId;
    private List<Long> projetoIds; // ids dos projetos vinculados
}