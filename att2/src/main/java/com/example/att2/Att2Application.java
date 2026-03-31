package com.example.att2;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.att2.models.Diretor;
import com.example.att2.models.Filme;
import com.example.att2.repositories.DiretorRepository;
import com.example.att2.repositories.Filmerepository;

@SpringBootApplication
public class Att2Application {

    @Bean
    public CommandLineRunner init(
            DiretorRepository diretorRepository,
            Filmerepository filmeRepository
    ) {

        return args -> {
            System.out.println("*** INSERINDO FILME E DIRETOR ***");

            Diretor d1 = new Diretor(null, "hermano", null);
            d1 = diretorRepository.save(d1);

            Filme f1 = new Filme(null, "Viva", 2, d1);
            f1 = filmeRepository.save(f1);

			Diretor d2 = new Diretor(null, "Tarantino", null);
            d2 = diretorRepository.save(d2);

            Filme f2 = new Filme(null, "Djonga livre", 3, d2);
            f2 = filmeRepository.save(f2);

			Diretor d3 = new Diretor(null, "Dogman", null);
            d3 = diretorRepository.save(d3);

            Filme f3 = new Filme(null, "La Fiesta de la salsicha maluka hermano", 1, d3);
            f3 = filmeRepository.save(f3);


            System.out.println("*** FILME SALVO: " + f1.getTitulo() + " ID=" + f1.getId());
            System.out.println("*** DIRETOR SALVO: " + d1.getNome() + " ID=" + d1.getId());

			System.out.println("*** FILME SALVO: " + f2.getTitulo() + " ID=" + f2.getId());
            System.out.println("*** DIRETOR SALVO: " + d2.getNome() + " ID=" + d2.getId());

			System.out.println("*** FILME SALVO: " + f3.getTitulo() + " ID=" + f3.getId());
            System.out.println("*** DIRETOR SALVO: " + d3.getNome() + " ID=" + d3.getId());

            List<Filme> filmes = filmeRepository.findByDuracaoGreaterThanEqual(2);
            System.out.println("*** FILMES COM DURAÇÃO >= 2 ***");
            filmes.stream().map(Filme::getTitulo).forEach(System.out::println);

            List<Filme> filmesDoDiretor = filmeRepository.findByDiretor(d1);
            System.out.println("*** FILMES DO DIRETOR ***");
            filmesDoDiretor.stream().map(Filme::getTitulo).forEach(System.out::println);

            List<Filme> filmesPorTitulo = filmeRepository.findByTituloLike("V%");
            System.out.println("*** FILMES COM TITULO LIKE V% ***");
            filmesPorTitulo.stream().map(Filme::getTitulo).forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Att2Application.class, args);
    }
}
