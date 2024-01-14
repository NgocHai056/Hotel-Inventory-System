FROM openjdk:17

WORKDIR /app

COPY target/inventory-0.0.1-SNAPSHOT.jar .

CMD java -jar inventory-0.0.1-SNAPSHOT.jar


