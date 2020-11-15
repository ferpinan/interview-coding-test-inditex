FROM maven:3.6.3-openjdk-8 as builder
WORKDIR /home/prices-rest-api/
COPY /src /home/prices-rest-api/src
COPY pom.xml /home/prices-rest-api/
RUN mvn clean package -DfinalName=prices-rest-api

FROM openjdk:11-jre-slim
VOLUME /home/logs
COPY /docker-resources/config/application.properties /home/application.properties
COPY --from=builder /home/prices-rest-api/target/prices-rest-api.jar /home/prices-rest-api.jar
EXPOSE 8080
CMD ["java", "-jar", "/home/prices-rest-api.jar", "--spring.config.location=file:///home/application.properties"]