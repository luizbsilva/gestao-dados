CREATE TABLE pessoa (
	codigo BIGINT,
	nome VARCHAR(255) NOT NULL,
	cpf VARCHAR(255),
	rg VARCHAR(255),
	data_nasc DATE,
	sexo VARCHAR(255),
	mae VARCHAR(255),
	pai VARCHAR(255),
	logradouro VARCHAR(255),
	numero VARCHAR(255),
	bairro VARCHAR(255),
	cep VARCHAR(255),
	cidade VARCHAR(255),
	estado VARCHAR(255),
	altura numeric(19,2),
	peso numeric(19,2),
	tipo_sanguineo BIGINT,
	ativo BOOLEAN NOT NULL,
	PRIMARY KEY ( codigo ),
    CONSTRAINT unique_pessoa_codigo UNIQUE( codigo ));

CREATE SEQUENCE pessoa_codigo_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
ALTER TABLE pessoa_codigo_seq OWNER TO postgres;  
ALTER TABLE pessoa ALTER COLUMN codigo SET DEFAULT nextval('pessoa_codigo_seq'::regclass);	