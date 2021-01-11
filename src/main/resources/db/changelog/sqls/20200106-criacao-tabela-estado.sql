CREATE TABLE estado(
	codigo BIGINT NOT NULL,
	nome VARCHAR(255) NOT NULL,
	sigla_uf VARCHAR(255) NOT NULL,
    CONSTRAINT unique_estado_codigo UNIQUE( codigo )) ;