FROM openjdk:17
WORKDIR /app
COPY /target/spring_aws-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]