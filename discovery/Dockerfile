FROM openjdk:21-jdk

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8761

# Command  run  application
ENTRYPOINT ["java", "-jar", "app.jar"]