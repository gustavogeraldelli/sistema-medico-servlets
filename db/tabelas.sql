CREATE TABLE usuarios(
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    tipo INT NOT NULL
);

CREATE TABLE medicos(
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    crm VARCHAR(50) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    especialidade VARCHAR(50) NOT NULL
);

CREATE TABLE pacientes(
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    cpf VARCHAR(20) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    sexo CHAR(1) NOT NULL,
    data_nasc DATE NOT NULL
);

CREATE TABLE consultas(
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_paciente INT NOT NULL,
    id_medico INT NOT NULL,
    data_hora DATETIME NOT NULL
);