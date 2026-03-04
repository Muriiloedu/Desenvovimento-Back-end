package com.example.calculadora.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/calculadora")
public class CalculadoraController {
    @GetMapping("somar")
    public Double getSomar(@RequestParam List<Double> num) {
        
        Double resultado = 0.0;
        
        for(Double n : num){
            resultado +=n;
        }
        return resultado;
    }
    
    @GetMapping("subtrair")
    public Double getSubitrair(@RequestParam List<Double> num) {
        
        Double resultado = 0.0;
        
        for(Double n : num){
            resultado -=n;     
        }
        return resultado;
    }

    @GetMapping("multiplicar")
    public Double getMultiplicar(@RequestParam List<Double> num) {
        
        Double resultado = 1.0;
        
        for(Double n: num){
            resultado *= n;
        }
        return resultado;
    }

    @GetMapping("dividir")
    public Double getDividir(@RequestParam Double numero1, @RequestParam Double numero2) {
        
        if(numero2 ==0){
            throw new IllegalArgumentException("Por zero?");
        }
        return numero1/numero2;
    }
}
