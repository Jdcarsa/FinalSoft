FROM openjdk:18
VOLUME /tmp
EXPOSE 8080
ADD ./target/MercadoValores-0.0.1-SNAPSHOT.jar mercadoValores.jar
ENTRYPOINT ["java", "-jar" , "/mercadoValores.jar"]