FROM openjdk:8-jre-alpine

WORKDIR /app

COPY target/calculator-1.0-SNAPSHOT.jar /app/application.jar

CMD ["java", "-jar", "/app/application.jar"]

