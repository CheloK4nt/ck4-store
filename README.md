- Levantar el contenedor:
    docker-compose up --build

- Resetear contenedor:
    docker-compose down --volumes --rmi all --remove-orphans

- Ingresar a contenedor backend:
    docker exec -it ck4-backend /bin/bash

- Ingresar a contenedor base de datos:
    docker exec -it ck4-db psql -U chelok4nt -d ck4store

- Ingresar a contenedor frontend:
    docker exec -it ck4-frontend /bin/bash

- PgSQL
    chelok4nt
    devops2025

- PgAdmin
    http://localhost:5050
    user: admin@ck4.dev
    password: admin123