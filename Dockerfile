# Build Stage
FROM gradle:7.6-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle bootJar --no-daemon

# Runtime Stage
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/app.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
