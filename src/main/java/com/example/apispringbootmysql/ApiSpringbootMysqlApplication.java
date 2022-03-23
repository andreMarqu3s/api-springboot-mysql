package com.example.apispringbootmysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.example.apispringbootmysql")
@EntityScan(basePackages = "com.example.apispringbootmysql.model")
public class ApiSpringbootMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiSpringbootMysqlApplication.class, args);
	}

}
