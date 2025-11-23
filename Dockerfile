# Use Maven to build the application
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy everything
COPY . .

# Package the Spring Boot application (skip tests)
RUN mvn -B -DskipTests clean package

# Use lightweight JDK image for running the app
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy the built JAR from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose port 8080 for Render
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
