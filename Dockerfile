# -------------------------
# Build stage
# -------------------------
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Копиране на pom.xml и кеширане на зависимостите
COPY pom.xml .
RUN apt-get update && apt-get install -y maven \
    && mvn dependency:go-offline

# Копиране на изходния код и билд
COPY src ./src
RUN mvn clean package -DskipTests

# -------------------------
# Runtime stage
# -------------------------
FROM eclipse-temurin:21-jdk-slim

WORKDIR /app

# Копиране на готовия jar от build stage
COPY --from=build /app/target/*.jar app.jar

# Отваряне на порт 8080
EXPOSE 8080

# Стартиране на приложението
ENTRYPOINT ["java", "-jar", "app.jar"]
