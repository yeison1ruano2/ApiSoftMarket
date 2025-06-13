# Dockerfile
FROM openjdk:17-jdk-slim

# Crea un directorio de trabajo
WORKDIR /app

# Copia el JAR compilado al contenedor
COPY target/ApiSoftMarket-0.0.1-SNAPSHOT.jar app.jar

# Expón el puerto de Spring Boot
EXPOSE 8080

# Ejecuta la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
