FROM openjdk:17

COPY ./target/dam_service-0.0.1-SNAPSHOT.jar /app/dam_service-0.0.1-SNAPSHOT.jar

WORKDIR /app/

EXPOSE 8082
EXPOSE 5006

CMD [ "java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "/app/dam_service-0.0.1-SNAPSHOT.jar" ]