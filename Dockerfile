# ----------------------
# Build stage
# ----------------------
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Копираме pom.xml и кешираме зависимостите
COPY pom.xml .
RUN mvn dependency:go-offline

# Копираме изходния код и билдваме проекта
COPY src ./src
RUN mvn clean package -DskipTests

# ----------------------
# Runtime stage
# ----------------------
FROM openjdk:21-jdk-slim

WORKDIR /app

# Копираме jar файла от build stage
COPY --from=build /app/target/*.jar app.jar

# Expose порт
EXPOSE 8080

# Стартираме приложението
ENTRYPOINT ["java", "-jar", "app.jar"]