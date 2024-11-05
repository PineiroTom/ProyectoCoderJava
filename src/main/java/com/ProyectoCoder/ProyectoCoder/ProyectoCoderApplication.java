package com.ProyectoCoder.ProyectoCoder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class ProyectoCoderApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoCoderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Levanto el servidor");
	}

}
