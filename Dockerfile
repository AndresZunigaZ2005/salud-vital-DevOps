# Etapa 1: Compilar con Gradle
FROM gradle:8.5-jdk17-alpine AS builder
COPY --chown=gradle:gradle . /home/gradle/project
WORKDIR /home/gradle/project
RUN gradle build --no-daemon

FROM openjdk:17-jdk-slim
COPY --from=builder /home/gradle/project/build/libs/DevOpsPVM.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
