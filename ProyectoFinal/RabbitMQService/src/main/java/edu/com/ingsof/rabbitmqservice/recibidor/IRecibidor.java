package edu.com.ingsof.rabbitmqservice.recibidor;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface IRecibidor {

    void publicar() throws IOException, TimeoutException;
}
