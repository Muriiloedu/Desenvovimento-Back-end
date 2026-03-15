package com.example.exerciojpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.exerciojpa.models.Categoria;
import com.example.exerciojpa.models.Produto;
import com.example.exerciojpa.repositories.CategoriaRepository;
import com.example.exerciojpa.repositories.ProdutoRepository;

@SpringBootApplication
public class ExerciojpaApplication {
	@Bean
	public CommandLineRunner init(
		@Autowired CategoriaRepository categoriaRepository,
		@Autowired ProdutoRepository produtoRepository
	){
		return args -> {
			Categoria c1 = categoriaRepository.salvar(new Categoria(null, "Eletrodomestico", ""));
			Categoria c2 = categoriaRepository.salvar(new Categoria(null, "Eletronico", ""));

			produtoRepository.salvar(new Produto(null, "liquidificador", 200.00 , 50,c1));
			produtoRepository.salvar(new Produto(null, "Tv", 1500.00, 20, c2));

			
			List<Produto> listaProdutos = produtoRepository.listar();
			listaProdutos.forEach(System.out::println);
		};
		

	}
		
	

	public static void main(String[] args) {
		SpringApplication.run(ExerciojpaApplication.class, args);
	}

}
