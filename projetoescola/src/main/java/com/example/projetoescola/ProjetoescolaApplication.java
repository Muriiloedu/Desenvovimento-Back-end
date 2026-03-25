package com.example.projetoescola;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.projetoescola.models.CategoriaCurso;
import com.example.projetoescola.models.Curso;
import com.example.projetoescola.repositories.CategoriaCursoRepository;
import com.example.projetoescola.repositories.CursoRepository;

@SpringBootApplication
public class ProjetoescolaApplication {

	@Bean
	public CommandLineRunner init(
		@Autowired CategoriaCursoRepository categoriaCursoRepository,
		@Autowired CursoRepository cursoRepository){
		return args -> {
			System.out.println("*** INSERINDO CATEGORIA ***");
			CategoriaCurso cc1 = new CategoriaCurso(null, "Tecnologia", null);
			cc1 = categoriaCursoRepository.save(cc1);

			System.out.println("*** INSERINDO CURSO ***");
			Curso c1 = new Curso(null, "ADS", 2000,cc1);
			Curso c2 = new Curso(null, "Gestão de TI", 2100, cc1);
			c1 = cursoRepository.save(c1);
			c2 = cursoRepository.save(c2);

			List<Curso> cursos = cursoRepository.findByNomeLike("A%");
			System.out.println("*** CURSOS ENCONTRADOS ***");
			cursos.stream().map(Curso::getNome).forEach(System.out::print);
		};
	};
	


	public static void main(String[] args) {
		SpringApplication.run(ProjetoescolaApplication.class, args);
	}

}
