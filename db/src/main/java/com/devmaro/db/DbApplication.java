package com.devmaro.db;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import javax.sql.DataSource;

@SpringBootApplication
@Slf4j
public class DbApplication  {

	public static void main(String[] args) {
		SpringApplication.run(DbApplication.class, args);
	}

}
