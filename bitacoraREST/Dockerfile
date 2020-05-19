FROM openjdk:8-jdk-slim
COPY "target/original-bitacora-rest-service-1.0.0.jar" "SPSbitacoraService-1.0.0.jar"
EXPOSE 8090
ENTRYPOINT ["java","-jar","SPSbitacoraService-1.0.0.jar"]