FROM openjdk:18
VOLUME /tmp
EXPOSE 8081
ADD ./target/MiAgenteFinanciero-0.0.1-SNAPSHOT.jar agentefinanciero.jar
ENTRYPOINT ["java", "-jar" , "/agentefinanciero.jar"]