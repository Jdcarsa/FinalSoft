package edu.com.ingsoft.rabbitmqservice.notificador;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface INotificador {

    public void publish(String msg) throws IOException, TimeoutException;
}
