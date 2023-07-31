FROM openjdk:19-alpine

WORKDIR /app

COPY target/back-test-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

CMD ["java", "-jar", "back-test-0.0.1-SNAPSHOT.jar"]
