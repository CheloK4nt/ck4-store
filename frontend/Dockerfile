FROM node:18
WORKDIR /app

# Instalar Angular CLI globalmente (solo se hace 1 vez al construir)
RUN npm install -g @angular/cli@17.3.10

# Verificar versiones al construir la imagen
RUN node -v && npm -v && ng version

# Copiar e instalar dependencias
COPY package*.json ./
RUN npm install

# Copiar el resto del proyecto
COPY . .

# Exponer el puerto
EXPOSE 4200

# Comando para desarrollo con live reload
CMD ["npm", "run", "start"]