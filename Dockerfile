FROM eclipse-temurin:17-jdk-alpine as builder
WORKDIR /vacancy-notifier
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install

FROM eclipse-temurin:17-jre-alpine
WORKDIR /vacancy-notifier
COPY --from=builder /vacancy-notifier/target/*.jar /vacancy-notifier/vacancy-notifier-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/vacancy-notifier/vacancy-notifier-service.jar"]