FROM maven:3.8.5-openjdk-17 AS build
COPY /src /src
COPY pom.xml /
RUN mvn -f /pom.xml clean package

FROM eclipse-temurin:17-jre
WORKDIR /
COPY --from=build /target/*.jar vacancy-notifier-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "vacancy-notifier-service.jar"]