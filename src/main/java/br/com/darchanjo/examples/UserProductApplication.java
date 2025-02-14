package br.com.darchanjo.examples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "br.com.darchanjo.examples.repo")
@EntityScan(basePackages = "br.com.darchanjo.examples")
public class UserProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserProductApplication.class, args);
    }
}
