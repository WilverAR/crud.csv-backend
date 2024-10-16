# Etapa 1: Construcción
FROM eclipse-temurin:21 AS build
LABEL authors="Wilver Arana Ramos"

# Definir directorio de trabajo
WORKDIR /root

# Copiar archivos necesarios para la construcción
COPY pom.xml ./
COPY .mvn ./.mvn
COPY mvnw ./
COPY src ./src

# Descargar las dependencias y construir la aplicación
RUN ./mvnw clean package -DskipTests # -DskipTests para saltar las pruebas \

# Etapa 2: Ejecución
FROM eclipse-temurin:21

# Definir directorio de trabajo
WORKDIR /root

# Copiar solo el archivo JAR desde la etapa de construcción
COPY --from=build /root/target/crud_csv-backend-0.0.1-SNAPSHOT.jar /root/crud_csv-backend.jar

# Exponer el puerto en el que la aplicación se ejecutará
EXPOSE 8080

# Definir el comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/root/crud_csv-backend.jar"]