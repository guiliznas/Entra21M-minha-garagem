#apaga
DROP DATABASE IF EXISTS minha_garagem;

#cria
CREATE DATABASE IF NOT EXISTS minha_garagem;

#seleciona
USE minha_garagem;

#apaga tabela
DROP TABLE IF EXISTS carros;

#cria tabela
CREATE TABLE IF NOT EXISTS carros (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(150),
    cor VARCHAR(100),
    fabricante VARCHAR(255),
    placa VARCHAR(8),
    chassi VARCHAR(50),
    quilometragem FLOAT,
    potencia FLOAT,
    data_compra DATE,
    esta_funcionando BOOLEAN,
    permitida_circulacao BOOLEAN,
    qtd_portas TINYINT,
    qtd_batidas TINYINT,
    ano_fabricacao SMALLINT,
    ano_lancamento SMALLINT,
    tipo_pneu SMALLINT,
    renavam INT,
	descricao TEXT
);

#Excluir tabela de categorias caso exista
DROP TABLE IF EXISTS categorias;

#Criar tabela de categorias caso não exista
CREATE TABLE IF NOT EXISTS categorias(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(150) UNIQUE NOT NULL,
    ativa BOOLEAN DEFAULT FALSE,
    descricao TEXT
    #Unique (id, nome)
);