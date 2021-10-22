CREATE TABLE requisicao (
id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
funcionario VARCHAR(50) NOT NULL,
lanche VARCHAR(50) NOT NULL UNIQUE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO requisicao (funcionario, lanche) values ("Emanuel Cavalcante Barros", "Pão");