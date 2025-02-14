FROM openjdk:17
ADD target/validation-app.jar validation-app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "validation-app.jar"]