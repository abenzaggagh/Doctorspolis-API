package com.doctorspolis.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class DoctorspolisApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoctorspolisApplication.class, args);
	}

}
