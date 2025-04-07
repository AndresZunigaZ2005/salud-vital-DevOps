FROM openjdk:17-jdk-slim
COPY build/libs/DevOpsPVM.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
