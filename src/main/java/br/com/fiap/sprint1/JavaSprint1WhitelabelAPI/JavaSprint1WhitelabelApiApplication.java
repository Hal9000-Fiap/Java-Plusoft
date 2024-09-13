package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JavaSprint1WhitelabelApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaSprint1WhitelabelApiApplication.class, args);
	}

}
