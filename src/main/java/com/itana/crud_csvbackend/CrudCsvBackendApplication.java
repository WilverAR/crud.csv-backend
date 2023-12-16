package com.itana.crud_csvbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CrudCsvBackendApplication {

    public static void main(String[] args) {

        SpringApplication.run(CrudCsvBackendApplication.class, args);

        String url = "http://localhost:8080/swagger-ui.html";
        System.out.println("\n• Swagger UI is available at » " + url);
    }

}
