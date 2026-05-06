package com.example.ac2.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioDTO {
    private String nome;
    private String email;
    private String cargo;
    private Long setorId;  // referência ao setor pelo id
}

