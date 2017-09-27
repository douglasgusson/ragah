CREATE TABLE uf ( 
  sigla_uf   CHAR(2)      NOT NULL,
  nome_uf    VARCHAR(40)  NOT NULL,
  CONSTRAINT pk_uf
    PRIMARY KEY (sigla_uf)
);


CREATE TABLE cidade (
  cod_cidade    SERIAL      NOT NULL, 
  nome_cidade   VARCHAR(60) NOT NULL, 
  sigla_uf      CHAR(2)     NOT NULL,
  CONSTRAINT pk_cidade
    PRIMARY KEY (cod_cidade),
  CONSTRAINT fk_cidade_uf
    FOREIGN KEY (sigla_uf)
    REFERENCES uf(sigla_uf)  
);


CREATE TABLE usuario (
  id_usuario    SERIAL      NOT NULL,
  nome_usuario  VARCHAR(60) NOT NULL,
  senha         CHAR(64)    NOT NULL,
  nome_completo VARCHAR(60) NOT NULL,
  email         VARCHAR(80) NOT NULL,
  ultimo_acesso TIMESTAMP   NOT NULL,
  nova_senha    BOOLEAN     NOT NULL,
  ativo         BOOLEAN     NOT NULL,
  administrador BOOLEAN     NOT NULL,
  CONSTRAINT pk_usuario
    PRIMARY KEY (id_usuario),
  CONSTRAINT un_nome_usuario
    UNIQUE (nome_usuario)
);


CREATE TABLE funcao (
  cod_funcao       SERIAL      NOT NULL, 
  nome_funcao      VARCHAR(60) NOT NULL,
  data_criacao     TIMESTAMP   NOT NULL,
  data_atualizacao TIMESTAMP   NOT NULL, 
  CONSTRAINT pk_funcao
    PRIMARY KEY (cod_funcao)
);


CREATE TABLE estado_civil (
  cod_estado_civil  SERIAL      NOT NULL,
  nome_estado_civil VARCHAR(30) NOT NULL,
  data_criacao      TIMESTAMP   NOT NULL,
  data_atualizacao  TIMESTAMP   NOT NULL, 
  CONSTRAINT pk_estado_civil
    PRIMARY KEY (cod_estado_civil)
);


CREATE TABLE funcionario (
  matricula        INTEGER      NOT NULL,
  nome             VARCHAR(50)  NOT NULL, 
  cpf              CHAR(11)     NOT NULL,
  rg               VARCHAR(20)  NOT NULL,
  data_nascimento  DATE         NOT NULL,
  estado_civil     INTEGER      NOT NULL,
  salario          NUMERIC(7,2) NOT NULL,
  data_admissao    DATE         NULL,
  data_demissao    DATE         NOT NULL,
  funcao           INTEGER      NOT NULL,
  endereco         VARCHAR(60)  NOT NULL,
  cep              CHAR(8)      NOT NULL,  
  bairro           VARCHAR(30)  NOT NULL,
  cidade           INTEGER      NOT NULL,
  data_criacao     TIMESTAMP    NOT NULL,
  data_atualizacao TIMESTAMP    NOT NULL,
  CONSTRAINT pk_funcionario
    PRIMARY KEY (matricula),
  CONSTRAINT fk_funcionario_estado_civil
    FOREIGN KEY (estado_civil)
    REFERENCES estado_civil (cod_estado_civil),
  CONSTRAINT fk_funcionario_funcao
    FOREIGN key (funcao)
    REFERENCES funcao (cod_funcao),
  CONSTRAINT fk_funcionario_cidade
    FOREIGN KEY (cidade)
    REFERENCES cidade (cod_cidade),
  CONSTRAINT un_cpf_funcionario
    UNIQUE (cpf)
);


CREATE TABLE telefone_funcionario (
  cod_telefone     SERIAL      NOT NULL,
  matr_funcionario INTEGER     NOT NULL,
  num_telefone     VARCHAR(14) NOT NULL, 
  descricao        VARCHAR(30) NOT NULL,
  data_criacao     TIMESTAMP   NOT NULL,
  data_atualizacao TIMESTAMP   NOT NULL,
  CONSTRAINT pk_telefone_funcionario
    PRIMARY KEY (cod_telefone, matr_funcionario),
  CONSTRAINT fk_telefone_funcionario_funcionario
    FOREIGN KEY (matr_funcionario)
    REFERENCES funcionario (matricula)
);