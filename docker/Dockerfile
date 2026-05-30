FROM maven:3.9.8-eclipse-temurin-11 AS build
WORKDIR /app
COPY . .
RUN mvn -q -DskipTests package

FROM eclipse-temurin:11-jre
WORKDIR /app
COPY --from=build /app /app
CMD ["bash", "-lc", "echo Ambiente AGT preparado"]
