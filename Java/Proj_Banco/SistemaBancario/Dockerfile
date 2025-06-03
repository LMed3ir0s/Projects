FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY out/artifacts/SistemaBancario_jar/SistemaBancario.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]