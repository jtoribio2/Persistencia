/* Eliminem la base de dades si existeix */

DROP DATABASE IF EXISTS escalada;

/* Crear la base de dades */
CREATE DATABASE escalada;
USE escalada;
CREATE TABLE escoles
(
	id 			INT NOT NULL,
	nom 		VARCHAR(25),
    lloc		VARCHAR(40),
    aproximacio VARCHAR(50),
    popularitat TINYINT COMMENT'1=baixa 2=mitjana 3=alta', 
    -- RESTRICCIONS / CONSTRAINTS 
	CONSTRAINT pk_escoles PRIMARY KEY (id),
    CONSTRAINT uk_escoles_id UNIQUE (id),
    CONSTRAINT ck_escoles_popularitat CHECK (popularitat BETWEEN 1 AND 3) 
) ;

INSERT INTO escoles(id,popularitat) VALUES(1,1);

CREATE TABLE sectors
(
	id 					INT NOT NULL,
	id_escoles			INT ,
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
-- Resulta que cada tipus de vies tindra unws restricios diferents 
INSERT INTO sectors (id,id_escoles,popularitat) VALUES(1,1,2);

CREATE TABLE vies
(
	id 					INT,
	id_sector			INT,
    nom					VARCHAR (20),
    dificultat			CHAR(2),
    orientacio			CHAR (3),
-- HE ELMINAT ALGUNES COLUMNES PERQUE  DISPOSEN DE DIFERENTS RESTRICCIONS 
   
    CONSTRAINT pk_vies PRIMARY KEY (id),
    CONSTRAINT uk_vies UNIQUE (id) COMMENT '',
    CONSTRAINT ck_vies_orientacio CHECK (REGEXP_LIKE(orientacio, '^([NSO]|N[EO]|S[EO])$'))  ,  -- REGEXP_LIKE PER POSAR UN REGEX  NOMES FUNCIONA AMB VERIOSIONS MODERES
     CONSTRAINT ck_vies_dificultat CHECK (REGEXP_LIKE(dificultat, '^(4,4|5,5|[6-9][abc])$'))  ,
    CONSTRAINT fk_vies_sectors FOREIGN KEY (id_sector)
				REFERENCES sectors (id)
) ;

INSERT INTO vies  (id,id_sector,orientacio,dificultat) VALUES(1,1,'N','9b');
CREATE TABLE viaEsportiva (
id 				INT, 
ancoratge 		CHAR (7),
troca			TINYINT COMMENT'1=conglomerat 2=granit 3=calcaria,arenisca 4=Altres',
llargada		TINYINT COMMENT 'de 5 a 30m',
estat			TINYINT  COMMENT 'Estat: 1.Apte, 2.construcció, 3.tancada',
	CONSTRAINT pk_viaEspotiva PRIMARY KEY(id),
    -- RESTRICCIONS 
	CONSTRAINT ck_viesEsportiva_ancoratge CHECK (ancoratge BETWEEN 1 AND 3) , 
	CONSTRAINT ck_viesEsportiva_troca CHECK (troca BETWEEN 1 AND 4),
    CONSTRAINT ck_viesEsportiva_llargada CHECK (llargada BETWEEN 5 AND 30),
    -- ES UNA GENERALITZACIO DEPEN DE LA TAULA VIES 
    CONSTRAINT fk_viaEsportiva_vies FOREIGN KEY(id)
    REFERENCES vies(id)
    
);


-- INSERT INTO viaEsportiva  (id,id_set,dificultat) VALUES(1,1,'N','6a');

-- TAULA ON TINDREM DATES ON ESTA RESTRINGIT ANAR A LA ESCOLA
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

CREATE TABLE escaladors(
nom		VARCHAR(20),
alies	VARCHAR(20),
edat 	INT,
nivell 	CHAR(2),
nom_via	VARCHAR(20),
estil INT COMMENT '1. Esportiva,2.Classica,3.gel'
);



