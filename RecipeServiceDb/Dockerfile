# Используйте официальный образ PostgreSQL
FROM postgres:latest

# Переменные окружения для настройки базы данных
ENV POSTGRES_DB=recipe_service
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres

# Копируем скрипт инициализации в директорию Docker-entrypoint-initdb.d
COPY init.sql /docker-entrypoint-initdb.d/