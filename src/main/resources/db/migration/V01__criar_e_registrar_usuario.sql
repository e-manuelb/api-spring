CREATE TABLE usuario (
id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(50) NOT NULL,
cpf VARCHAR(14) NOT NULL UNIQUE,
email VARCHAR(50) NOT NULL,
senha VARCHAR(50) NOT NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO usuario (nome, cpf, email, senha) values ("João da Silva", "001.430.485-33", "joaodasilva@gmail.com", "12345678");
INSERT INTO usuario (nome, cpf, email, senha) values ("Maria da Silva", "002.850.485-34", "mariadasilva@gmail.com", "12345678");
INSERT INTO usuario (nome, cpf, email, senha) values ("Antonio da Costa", "080.740.496-48", "antoniodacosta@gmail.com", "12345678");
INSERT INTO usuario (nome, cpf, email, senha) values ("Rodrigo Sousa", "626.741.362-00", "joaodasilva@gmail.com", "12345678");
