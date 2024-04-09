CREATE DATABASE filtro_java;

USE filtro_java;

CREATE TABLE empresa (
	id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    sector VARCHAR(255) NOT NULL,
    ubicacion VARCHAR(255) NOT NULL,
    contacto VARCHAR(255) NOT NULL
);

CREATE TABLE vacante (
	id INT AUTO_INCREMENT PRIMARY KEY,
    empresa_id INT,
    FOREIGN KEY (empresa_id) REFERENCES empresa(id) ON DELETE CASCADE,
    titulo VARCHAR(255) NOT NULL,
    descripcion TEXT,
    duracion VARCHAR(255) NOT NULL,
    estado VARCHAR(255) NOT NULL
);

CREATE TABLE coder (
	id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellidos VARCHAR(255) NOT NULL,
    documento VARCHAR(255) NOT NULL,
    cohorte INT,
    cv TEXT
);

CREATE TABLE contratacion (
	id INT AUTO_INCREMENT PRIMARY KEY,
	vacante_id INT,
    coder_id INT,
    FOREIGN KEY (vacante_id) REFERENCES vacante(id) ON DELETE CASCADE,
    FOREIGN KEY (coder_id) REFERENCES coder(id) ON DELETE CASCADE,
    fecha_aplicacion DATE NOT NULL,
    estado VARCHAR(255) NOT NULL,
    salario DECIMAL(10,2) NOT NULL
);

ALTER TABLE vacante ADD tecnologia VARCHAR(255) NOT NULL;

ALTER TABLE coder ADD clan VARCHAR(255) NOT NULL;

ALTER TABLE vacante 

SELECT * FROM empresa;