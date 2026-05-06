package com.example.ac2.controllers;


import com.example.ac2.dtos.DadosFuncionarioDTO;
import com.example.ac2.dtos.DadosProjetoDTO;
import com.example.ac2.dtos.FuncionarioDTO;
import com.example.ac2.services.FuncionarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DadosFuncionarioDTO adicionar(@RequestBody FuncionarioDTO dto) {
        return funcionarioService.adicionar(dto);
    }

    @GetMapping("/{idFuncionario}/projetos")
    public List<DadosProjetoDTO> buscarProjetos(@PathVariable Long idFuncionario) {
        return funcionarioService.buscarProjetos(idFuncionario);
    }
}
