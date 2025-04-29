# Build stage
FROM maven:3.9.9-eclipse-temurin-8 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:8-jdk
WORKDIR /app
VOLUME /data/db
COPY --from=build /app/target/*.jar app.jar
EXPOSE 6011
ENTRYPOINT ["java", "-jar", "app.jar"]
