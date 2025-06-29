package com.deeroot.quickstart;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log
public class BooksApiApplication {


	public static void main(String[] args) {
		SpringApplication.run(BooksApiApplication.class, args);
	}
	/*
	@Override
	public void run(final String... args){
		log.info("Datasource: " + dataSource.toString());
		final JdbcTemplate restTemplate = new JdbcTemplate(dataSource);
		restTemplate.execute("select 1");
	}
*/
}
