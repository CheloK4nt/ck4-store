FROM openjdk:17-slim
WORKDIR /app

# Instalar utilidades necesarias
RUN apt-get update && apt-get install -y unzip curl

# Mostrar versiones útiles en tiempo de build (opcional)
RUN java -version && curl --version

# Copiar todo el código del proyecto
#COPY . .

# Asegurar permisos para usar el Maven Wrapper
#RUN chmod +x ./mvnw

# Exponer el puerto donde correrá Spring Boot
EXPOSE 8080

# Ejecutar Spring Boot en modo dev (con devtools activo)
#CMD ["./mvnw", "spring-boot:run"]
