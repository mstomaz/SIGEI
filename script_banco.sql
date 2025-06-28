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
    lotacao int NOT NULL,
    dataEvento datetime NOT NULL,
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

CREATE TABLE IF NOT EXISTS Festa_atracoes (
    idAtracao int auto_increment,
	idEvento int,
    atracao varchar(100) NOT NULL,
    ativo bit DEFAULT 1,
    CONSTRAINT PK_atracoes_da_festa primary key (idAtracao),
    CONSTRAINT FK_idEvento_festa FOREIGN KEY (idEvento) REFERENCES Evento(idEvento)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Oficina (
	idEvento int,
    tema varchar(200) NOT NULL,
    CONSTRAINT PK_oficina primary key (idEvento),
    CONSTRAINT FK_idEvento_oficina FOREIGN KEY (idEvento) REFERENCES Evento(idEvento)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Palestra (
	idEvento int,
    palestrante varchar(200) NOT NULL,
    CONSTRAINT PK_palestra primary key (idEvento),
    CONSTRAINT FK_idEvento_palestra FOREIGN KEY (idEvento) REFERENCES Evento(idEvento)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Show_artistas (
	idArtista int auto_increment,
	idEvento int,
    artista varchar(100) NOT NULL,
    ativo bit DEFAULT 1,
    CONSTRAINT PK_artista_show primary key (idArtista),
    CONSTRAINT FK_idEvento_show FOREIGN KEY (idEvento) REFERENCES Evento(idEvento)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Administrador (
	cpf varchar(11),
    nome varchar(40) NOT NULL,
    sobrenome varchar(60) NOT NULL,
    CONSTRAINT PK_administrador primary key (cpf)
);

CREATE TABLE IF NOT EXISTS Organizador (
	cpf varchar(11),
    nome varchar(40) NOT NULL,
    sobrenome varchar(60) NOT NULL,
    CONSTRAINT PK_administrador primary key (cpf)
);

CREATE TABLE IF NOT EXISTS Organizador_eventos (
	cpf varchar(11),
    idEvento int,
    CONSTRAINT PK_Organizador_eventos primary key (cpf, idEvento),
    CONSTRAINT FK_cpf_org_eventos FOREIGN KEY (cpf) REFERENCES Organizador(cpf)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT FK_idEvento_org_eventos FOREIGN KEY (idEvento) REFERENCES Evento(idEvento)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Participante (
	cpf varchar(11),
    nome varchar(40) NOT NULL,
    sobrenome varchar(60) NOT NULL,
    dataNasc date NOT NULL,
    CONSTRAINT PK_administrador primary key (cpf)
);

CREATE TABLE IF NOT EXISTS Participante_ingressos (
	cpf varchar(11),
    idIngresso int,
    CONSTRAINT PK_Participante_ingressos primary key (cpf, idIngresso),
    CONSTRAINT FK_cpf_part_ingressos FOREIGN KEY (cpf) REFERENCES Participante(cpf)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT FK_idEvento_part_ingressos FOREIGN KEY (idIngresso) REFERENCES Ingresso(idIngresso)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);