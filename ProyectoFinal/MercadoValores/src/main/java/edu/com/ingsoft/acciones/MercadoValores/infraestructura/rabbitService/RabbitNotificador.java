package edu.com.ingsoft.acciones.MercadoValores.infraestructura.rabbitService;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


public class RabbitNotificador {

    private static final String EXCHANGE_NAME = "notificacion";

    public RabbitNotificador(){
    }

    public void notificar(String mensaje) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("172.18.0.3");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

            channel.basicPublish(EXCHANGE_NAME, "", null, mensaje.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + mensaje + "'");
        } catch (Exception e){
            System.out.println("Error: " + e.toString());
        }
    }

}
