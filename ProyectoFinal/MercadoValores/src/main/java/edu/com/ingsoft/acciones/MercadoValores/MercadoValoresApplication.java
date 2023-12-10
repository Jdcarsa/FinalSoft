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
		String basePath = getBaseFilePath();
		try{
			Manager.init(basePath);
		} catch (Exception ex) {
			Logger.getLogger("Application").log(Level.SEVERE, "Error al ejecutar la aplicación", ex);
		}

	}

	private static String getBaseFilePath() {
		String path = MercadoValoresApplication.class.getProtectionDomain().getCodeSource().getLocation().getPath();

		File pathFile = new File(path);
		if (pathFile.isFile()) {
			path = pathFile.getParent();

			if (!path.endsWith(File.separator)) {
				path += File.separator;
			}

		}

		return path;
	}
}
