package edu.com.ingsoft.acciones.MercadoValores;

import edu.com.ingsoft.acciones.MercadoValores.infraestructura.rabbitService.Manager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class MercadoValoresApplication {

	public static void main(String[] args) {

		SpringApplication.run(MercadoValoresApplication.class, args);

	}


}
