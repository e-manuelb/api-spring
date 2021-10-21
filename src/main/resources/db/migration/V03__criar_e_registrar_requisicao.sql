CREATE TABLE requisicao (
id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
nomePessoa VARCHAR(50) NOT NULL,
cpfPessoa VARCHAR(14) NOT NULL,
lanche VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO requisicao (nomePessoa, cpfPessoa, lanche) values ("Emanuel Cavalcante Barros", "077.813.433-44", "Pão");