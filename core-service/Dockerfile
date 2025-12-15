FROM eclipse-temurin:17-jdk
WORKDIR /project

COPY ./ ./
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

# Запускаем через Spring Boot Maven Plugin
CMD ["./mvnw", "spring-boot:run"]
