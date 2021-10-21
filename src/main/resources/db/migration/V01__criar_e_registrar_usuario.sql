CREATE TABLE usuario (
id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(50) NOT NULL,
cpf VARCHAR(14) NOT NULL,
email VARCHAR(50) NOT NULL,
senha VARCHAR(50) NOT NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO usuario (nome, cpf, email, senha) values ("Emanuel Cavalcante Barros", "077.813.433-44", "emanuelcavalcanteb@gmail.com", "210201ee");