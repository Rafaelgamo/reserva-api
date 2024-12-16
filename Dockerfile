#
# Building
#
FROM openjdk:17-jdk-slim AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn

RUN chmod +x ./mvnw

RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package -DskipTests

#
# Run app
#
FROM openjdk:17-jdk-slim AS run

WORKDIR /app

COPY --from=build /target .

EXPOSE 8080:8080

ENTRYPOINT ["java", "-jar", "reserva-api-0.0.1.jar"]