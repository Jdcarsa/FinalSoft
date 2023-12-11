package edu.com.ingsoft.MiAgenteFinanciero;

import com.rabbitmq.client.DeliverCallback;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
public class MiAgenteFinancieroApplication {

	private static String message;
	private static final String EXCHANGE_NAME = "notificacion";

	public static void main(String[] args) throws IOException, TimeoutException {

		SpringApplication.run(MiAgenteFinancieroApplication.class, args);
		publicar();
	}

	public static void publicar() throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("172.17.0.3");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		String queueName = channel.queueDeclare().getQueue();
		channel.queueBind(queueName, EXCHANGE_NAME, "");

		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
			message = new String(delivery.getBody(), "UTF-8");

			 System.out.println(" [x] Received '" + message + "'");
		};
		channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
		});
	}

}
