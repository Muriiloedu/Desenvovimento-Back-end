package com.example.ac2;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.ac2.models.Funcionario;
import com.example.ac2.models.Projeto;
import com.example.ac2.models.Setor;
import com.example.ac2.repositories.FuncionarioRepository;
import com.example.ac2.repositories.ProjetoRepository;
import com.example.ac2.repositories.SetorRepository;

@SpringBootApplication
public class Ac2Application {
	@Bean
	public CommandLineRunner init(
		@Autowired FuncionarioRepository funcionarioRepository,
		@Autowired ProjetoRepository projetoRepository,
		@Autowired SetorRepository setorRepository) {
			return args -> {
				            // =============================================
            // 1. CRIAÇÃO DOS SETORES
            // =============================================
            Setor ti = Setor.builder()
                    .nome("TI")
                    .build();

            Setor rh = Setor.builder()
                    .nome("RH")
                    .build();

            Setor financeiro = Setor.builder()
                    .nome("Financeiro")
                    .build();

            setorRepository.save(ti);
            setorRepository.save(rh);
            setorRepository.save(financeiro);

            // =============================================
            // 2. CRIAÇÃO DOS FUNCIONÁRIOS
            // =============================================
            Funcionario ana = Funcionario.builder()
                    .nome("Ana Silva")
                    .email("ana.silva@empresa.com")
                    .cargo("Desenvolvedora")
                    .setor(ti)
                    .build();

            Funcionario carlos = Funcionario.builder()
                    .nome("Carlos Souza")
                    .email("carlos.souza@empresa.com")
                    .cargo("Analista de RH")
                    .setor(rh)
                    .build();

            Funcionario beatriz = Funcionario.builder()
                    .nome("Beatriz Lima")
                    .email("beatriz.lima@empresa.com")
                    .cargo("Gerente Financeiro")
                    .setor(financeiro)
                    .build();

            Funcionario diego = Funcionario.builder()
                    .nome("Diego Martins")
                    .email("diego.martins@empresa.com")
                    .cargo("DevOps")
                    .setor(ti)
                    .build();

            funcionarioRepository.save(ana);
            funcionarioRepository.save(carlos);
            funcionarioRepository.save(beatriz);
            funcionarioRepository.save(diego);

            // =============================================
            // 3. CRIAÇÃO DOS PROJETOS
            // =============================================
            Projeto sistemaWeb = Projeto.builder()
                    .descricao("Desenvolvimento do novo sistema web da empresa")
                    .dataInicio(LocalDate.of(2024, 1, 10))
                    .dataFim(LocalDate.of(2024, 6, 30))
                    .build();

            Projeto appMobile = Projeto.builder()
                    .descricao("Aplicativo mobile para clientes")
                    .dataInicio(LocalDate.of(2024, 3, 1))
                    .dataFim(LocalDate.of(2024, 12, 31))
                    .build();

            Projeto migracaoCloud = Projeto.builder()
                    .descricao("Migração da infraestrutura para a nuvem")
                    .dataInicio(LocalDate.of(2024, 2, 15))
                    .dataFim(null) // sem prazo definido
                    .build();

            projetoRepository.save(sistemaWeb);
            projetoRepository.save(appMobile);
            projetoRepository.save(migracaoCloud);

            // =============================================
            // 4. VINCULAÇÃO FUNCIONÁRIOS <-> PROJETOS
            // =============================================
            sistemaWeb.getFuncionarios().add(ana);
            sistemaWeb.getFuncionarios().add(diego);
            projetoRepository.save(sistemaWeb);

            appMobile.getFuncionarios().add(ana);
            appMobile.getFuncionarios().add(carlos);
            projetoRepository.save(appMobile);

            migracaoCloud.getFuncionarios().add(diego);
            migracaoCloud.getFuncionarios().add(beatriz);
            projetoRepository.save(migracaoCloud);

            // =============================================
            // 5. CONSULTAS / RELATÓRIOS
            // =============================================

            System.out.println("\n========== TODOS OS SETORES COM FUNCIONÁRIOS ==========");
            setorRepository.findAllComFuncionarios()
                    .forEach(s -> {
                        System.out.println("Setor: " + s.getNome());
                        s.getFuncionarios()
                                .forEach(f -> System.out.println("  -> " + f.getNome() + " (" + f.getCargo() + ")"));
                    });

            System.out.println("\n========== PROJETOS COM INÍCIO EM 2024 ==========");
            projetoRepository.findByDataInicioBetween(
                            LocalDate.of(2024, 1, 1),
                            LocalDate.of(2024, 12, 31))
                    .forEach(p -> System.out.println(p.getDescricao() + " | Início: " + p.getDataInicio()));

            System.out.println("\n========== PROJETOS DE ANA SILVA ==========");
            funcionarioRepository.findFuncionarioFetchProjetos(ana.getId())
                    .ifPresent(f -> f.getProjetos()
                            .forEach(p -> System.out.println(f.getNome() + " -> " + p.getDescricao())));

            System.out.println("\n========== PROJETO 'SISTEMA WEB' COM FUNCIONÁRIOS ==========");
            projetoRepository.findProjetoFetchFuncionarios(sistemaWeb.getId())
                    .ifPresent(p -> {
                        System.out.println("Projeto: " + p.getDescricao());
                        p.getFuncionarios()
                                .forEach(f -> System.out.println("  -> " + f.getNome()));
                    });

            System.out.println("\n========== BUSCA FUNCIONÁRIO POR E-MAIL ==========");
            funcionarioRepository.findByEmail("carlos.souza@empresa.com")
                    .ifPresent(f -> System.out.println("Encontrado: " + f.getNome()
                            + " | Cargo: " + f.getCargo()
                            + " | Setor: " + f.getSetor().getNome()));

            System.out.println("\n========== SETOR DE TI COM FUNCIONÁRIOS ==========");
            setorRepository.findSetorFetchFuncionarios(ti.getId())
                    .ifPresent(s -> {
                        System.out.println("Setor: " + s.getNome());
                        s.getFuncionarios()
                                .forEach(f -> System.out.println("  -> " + f.getNome()));
                    });
        };
	}
	public static void main(String[] args) {
		SpringApplication.run(Ac2Application.class, args);
	}
}

