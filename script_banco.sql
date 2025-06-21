CREATE DATABASE IF NOT EXISTS ZezinhoEventos;

USE ZezinhoEventos;

CREATE TABLE IF NOT EXISTS Evento (
	idEvento int auto_increment,
    nome varchar(200) NOT NULL,
    descricao text,
    rua varchar(70) NOT NULL,
    numero varchar(10) NOT NULL,
    bairro varchar(70) NOT NULL,
    cidade varchar(80) NOT NULL,
    uf char(2) NOT NULL,
    referencia varchar(200),
    lotacao int,
    data datetime NOT NULL,
    vagasDisp int NOT NULL,
    tipoEvento int NOT NULL,
    CONSTRAINT PK_eventos primary key (idEvento)
);

CREATE TABLE IF NOT EXISTS Ingresso (
	idIngresso int auto_increment,
    idEvento int NOT NULL,
    CONSTRAINT PK_ingressos primary key (idIngresso),
    CONSTRAINT FK_evento_ref_ingresso FOREIGN KEY (idEvento) REFERENCES Evento(idEvento)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Evento_Ingressos (
	idEvento int,
    idIngresso int,
    CONSTRAINT PK_evento_ingressos primary key (idEvento, idIngresso),
    CONSTRAINT FK_evento_ingressos_idEvento FOREIGN KEY (idEvento) REFERENCES Evento(idEvento)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT FK_evento_ingressos_idIngresso FOREIGN KEY (idIngresso) REFERENCES Ingresso(idIngresso)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Festa_atracoes (
    idAtracao int auto_increment,
	idFesta int,
    atracao varchar(100),
    ativo bit DEFAULT 1,
    CONSTRAINT PK_atracoes_da_festa primary key (idAtracao),
    CONSTRAINT FK_idEvento_festa FOREIGN KEY (idFesta) REFERENCES Evento(idEvento)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);