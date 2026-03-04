package com.example.exemplojpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.exemplojpa.models.Professor;
import com.example.exemplojpa.repositories.ProfessorRepository;

@SpringBootApplication
public class ExemplojpaApplication {

	@Bean
	public CommandLineRunner init(@Autowired ProfessorRepository professorRepository) {
	return args -> {
		professorRepository.salvar(
	new Professor(null, "Rafael","doutor"));
		
		List<Professor> listaProfessores = professorRepository.listar();
	listaProfessores.forEach(System.out::println);
	};
}
	public static void main(String[] args) {
		SpringApplication.run(ExemplojpaApplication.class, args);
	}

}
