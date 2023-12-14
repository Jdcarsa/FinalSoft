package edu.com.ingsoft.acciones.MercadoValores.infraestructura.rabbitService;

public interface IRabbitNotificador {
    void notificar(String mensaje) throws Exception;
}
