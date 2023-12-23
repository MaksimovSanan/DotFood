# Используйте официальный образ OpenJDK
FROM openjdk:17

# Копируем JAR файл приложения в контейнер
COPY target/recipeService-0.0.1-SNAPSHOT.jar /app.jar

# Экспонируем порт, на котором работает Spring Boot
EXPOSE 8080

# Запускаем приложение при старте контейнера
CMD ["java", "-jar", "/app.jar"]