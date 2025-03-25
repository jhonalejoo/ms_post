FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17
COPY --from=build /app/target/Auth-0.0.1-SNAPSHOT.jar ms-post.jar
ENTRYPOINT ["java", "-jar", "/ms-post.jar"]
