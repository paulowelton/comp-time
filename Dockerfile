# Multi-stage build for Spring Boot application

# Stage 1: Build the Maven package
FROM maven:3.9.9-eclipse-temurin-21-alpine AS builder
WORKDIR /app

# Copy dependency definition first for caching Maven dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy application source and build application jar
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Minimal runtime image
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Create a non-root system user and group for security
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

# Copy built JAR artifact from builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose application port
EXPOSE 8000

# Execute Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
