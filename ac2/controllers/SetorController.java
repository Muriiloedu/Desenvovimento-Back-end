package com.example.ac2.controllers;

import com.example.ac2.dtos.DadosSetorDTO;
import com.example.ac2.dtos.SetorDTO;
import com.example.ac2.services.SetorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/setores")
public class SetorController {

    private final SetorService setorService;

    public SetorController(SetorService setorService) {
        this.setorService = setorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DadosSetorDTO adicionar(@RequestBody SetorDTO dto) {
        return setorService.adicionar(dto);
    }

    @GetMapping("/{idSetor}")
    public DadosSetorDTO buscarSetorPorId(@PathVariable Long idSetor) {
        return setorService.buscarPorId(idSetor);
    }
}
