FROM openjdk:17-jdk-slim
COPY build/libs/DevOpsPVM.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]