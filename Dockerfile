# Etapa 1: Construcción del proyecto con Gradle
FROM gradle:8.5-jdk17-alpine AS builder

# Copiar el proyecto
COPY --chown=gradle:gradle . /home/gradle/project
WORKDIR /home/gradle/project

# Ejecutar la tarea shadowJar para generar el JAR con todas las dependencias
RUN gradle shadowJar --no-daemon

# Etapa 2: Imagen de ejecución
FROM openjdk:17-jdk-slim

# Copiar el JAR generado desde la etapa anterior
COPY --from=builder /home/gradle/project/build/libs/DevOpsPVM.jar /app.jar

# Exponer el puerto 8080
EXPOSE 8080

# Ejecutar el JAR al iniciar el contenedor
ENTRYPOINT ["java", "-jar", "/app.jar"]
