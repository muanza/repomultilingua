FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /workspace
COPY . .
RUN mvn -Pproduction -DskipTests clean package

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /workspace/modules/facturacao/target/facturacao-1.0.0-SNAPSHOT.jar /app/facturacao.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/facturacao.jar"]
