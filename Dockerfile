FROM openjdk:17-jdk-alpine
WORKDIR /opt/app
COPY target/demo-0.0.1-SNAPSHOT.jar target/demo-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT exec java -jar target/demo-0.0.1-SNAPSHOT.jar