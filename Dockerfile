FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copiar el archivo jar de la aplicación al contenedor
COPY target/aws-prueba-0.0.1-SNAPSHOT.jar /app/aws-prueba-0.0.1-SNAPSHOT.jar

EXPOSE 8080

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/aws-prueba-0.0.1-SNAPSHOT.jar"]
