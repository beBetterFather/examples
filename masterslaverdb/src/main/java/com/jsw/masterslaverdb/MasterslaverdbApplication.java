package com.jsw.masterslaverdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MasterslaverdbApplication {

	public static void main(String[] args) {
		SpringApplication.run(MasterslaverdbApplication.class, args);
	}

}
