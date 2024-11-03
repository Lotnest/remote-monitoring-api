FROM openjdk:21-jdk-slim

WORKDIR /remote-monitoring-api

COPY build/libs/remote-monitoring-api-*-SNAPSHOT.jar remote-monitoring-api.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "remote-monitoring-api.jar"]
