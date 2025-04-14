FROM node:18
WORKDIR /app

# Instalar Angular CLI globalmente (solo se hace 1 vez al construir)
RUN npm install -g @angular/cli

# Copiar e instalar dependencias
COPY package*.json ./
RUN npm install

# Copiar el resto del proyecto
COPY . .

# Exponer el puerto
EXPOSE 4200

# Comando para desarrollo con live reload
CMD ["npm", "run", "start"]