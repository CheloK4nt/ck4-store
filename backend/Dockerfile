FROM openjdk:17
WORKDIR /app

# Copiar todo el proyecto
COPY . .

# Dar permisos al wrapper de Maven
RUN chmod +x ./mvnw

# Exponer el puerto
EXPOSE 8080

# Comando para levantar Spring Boot en modo desarrollo
CMD ["./mvnw", "spring-boot:run"]
