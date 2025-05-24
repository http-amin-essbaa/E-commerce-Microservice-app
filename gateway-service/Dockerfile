FROM openjdk:21-jdk

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8761

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]