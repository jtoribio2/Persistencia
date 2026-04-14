/* Eliminem la base de dades si existeix */

DROP DATABASE IF EXISTS escalada;

/* Crear la base de dades */
CREATE DATABASE escalada;
USE escalada;
CREATE TABLE escoles
(
	id 					INT,
	nom 		VARCHAR(25),
    lloc		VARCHAR(40),
    aproximacio VARCHAR(50),
    popularitat TINYINT COMMENT'1=baixa 2=mitjana 3=alta', 
	CONSTRAINT pk_escoles PRIMARY KEY (id),
    CONSTRAINT uk_escoles_id UNIQUE (id),
    CONSTRAINT ck_escoles_popularitat CHECK (popularitat BETWEEN 1 AND 3)
) ;

CREATE TABLE sectors
(
	id 					INT,
	id_escoles			INT,
    nom					VARCHAR (20),
    latitud				DECIMAL (9,6),
    longitud			DECIMAL (5,2),
    aproximacio			VARCHAR (50),
    popularitat 		TINYINT COMMENT'1=baixa 2=mitjana 3=alta', 
    CONSTRAINT pk_sectors PRIMARY KEY (id),
    CONSTRAINT uk_sectors UNIQUE (id),
    CONSTRAINT fk_sectors_escoles FOREIGN KEY (id_escoles)
				REFERENCES escoles (id)
) ;

CREATE TABLE vies
(
	id 					INT,
	id_sector			INT,
    nom					VARCHAR (20),
    llargada			TINYINT,
    dificultad			CHAR (2),
    orientacio			CHAR (2),
    ancoratge 			CHAR (7),
    troca				VARCHAR (11),
    CONSTRAINT pk_vies PRIMARY KEY (id),
    CONSTRAINT uk_vies UNIQUE (id),
    CONSTRAINT fk_vies_sectors FOREIGN KEY (id_sector)
				REFERENCES sectors (id)
) ;

CREATE TABLE disponibilitats
(
	id 					INT,
	id_sectors			INT,
    inici				DATE,
    final				DATE,
    rao					VARCHAR(40),
    CONSTRAINT pk_disponibilitat PRIMARY KEY (id),
    CONSTRAINT uk_disponibilitat UNIQUE (id),
    CONSTRAINT fk_disponibilitat_sectors FOREIGN KEY (id_sectors)
				REFERENCES sectors (id)
) ;


