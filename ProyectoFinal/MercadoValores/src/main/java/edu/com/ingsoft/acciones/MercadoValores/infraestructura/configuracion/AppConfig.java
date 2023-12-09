package edu.com.ingsoft.acciones.MercadoValores.infraestructura.configuracion;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {

/**
 * This function creates and returns an instance of the ModelMapper class.
 * 
 * @return The method is returning an instance of the ModelMapper class.
 */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }
}
