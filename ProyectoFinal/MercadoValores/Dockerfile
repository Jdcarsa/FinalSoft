FROM openjdk:18
VOLUME /tmp
ENV IMG_PATH=/img
EXPOSE 8080
RUN mkdir -p /img
ADD ./target/MercadoValores-0.0.1-SNAPSHOT.jar mercadoValores.jar
ENTRYPOINT ["java", "-jar" , "/mercadoValores.jar"]