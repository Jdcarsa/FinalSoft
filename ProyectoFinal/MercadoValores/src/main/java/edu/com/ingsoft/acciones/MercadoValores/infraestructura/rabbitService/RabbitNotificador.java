package edu.com.ingsoft.acciones.MercadoValores.infraestructura.rabbitService;

import edu.com.ingsoft.rabbitmqservice.notificador.INotificador;

public class RabbitNotificador {


    public RabbitNotificador(){
    }

    public void notificar(String mensaje){
        Manager manager = Manager.getInstance();
        INotificador noti = manager.getNotificador("notificador");
        noti.publish(mensaje);
    }

}
