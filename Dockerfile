FROM openjdk:26-ea-21-oraclelinux8
WORKDIR /app
COPY target/*.jar service.jar
CMD ["java", "-jar", "/app/service.jar"]