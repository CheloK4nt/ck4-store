-- Tabla usuarios
CREATE TABLE IF NOT EXISTS usuarios (
    id SERIAL PRIMARY KEY,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    rol VARCHAR(20) NOT NULL DEFAULT 'USER', -- 'ADMIN', 'USER', etc.
    activo BOOLEAN DEFAULT TRUE,
    ultimo_login TIMESTAMP,
    creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Usuarios de prueba
INSERT INTO usuarios (email, password, nombre, rol)
VALUES 
  ('admin@ck4.dev', '1234', 'Administrador', 'ADMIN'),
  ('usuario@ck4.dev', '1234', 'Usuario Normal', 'USER')
ON CONFLICT DO NOTHING;
