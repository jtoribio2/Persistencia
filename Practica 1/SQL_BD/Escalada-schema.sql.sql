/* Eliminem la base de dades si existeix */

DROP DATABASE IF EXISTS escalada;

/* Crear la base de dades */
CREATE DATABASE escalada;
USE escalada;
CREATE TABLE escoles
(
	id_escola 	INT AUTO_INCREMENT,
	nom 		VARCHAR(25),
    lloc		VARCHAR(40),
    aproximacio VARCHAR(50),
    popularitat TINYINT COMMENT'1=baixa 2=mitjana 3=alta', 
	CONSTRAINT pk_escoles PRIMARY KEY (id_escola),
    CONSTRAINT uk_escoles_id UNIQUE (id_escola),
    CONSTRAINT ck_escoles_popularitat CHECK (popularitat BETWEEN 1 AND 3)
) ;

CREATE TABLE sectors
(
	id_sector			INT AUTO_INCREMENT,
	id_escola			INT,
    nom					VARCHAR (20),
    latitut				DECIMAL (9,6),
    longitut			DECIMAL (5,2),
    aproximacio			VARCHAR (50),
    popularitat 		TINYINT COMMENT'1=baixa 2=mitjana 3=alta', 
    CONSTRAINT pk_sectors PRIMARY KEY (id_sector),
    CONSTRAINT uk_sectors UNIQUE (id_sector),
    CONSTRAINT fk_sectors_escoles FOREIGN KEY (id_escola)
				REFERENCES escoles (id_escola)
) ;

CREATE TABLE tipus_via
(
	id_tipus_via		INT AUTO_INCREMENT,
    tipus				VARCHAR(10),
    CONSTRAINT pk_tipus_via PRIMARY KEY (id_tipus_via),
    CONSTRAINT uk_tipus_via UNIQUE (id_tipus_via)
);

CREATE TABLE vies
(
	id_via 				INT AUTO_INCREMENT,
	id_sector			INT,
    id_tipus_via		INT,
    nom					VARCHAR (20),
    llargada			TINYINT,
    dificultat			CHAR (3),
    orientacio			CHAR (2),
    ancoratge 			CHAR (7),
    troca				VARCHAR (11),
    CONSTRAINT pk_vies PRIMARY KEY (id_via),
    CONSTRAINT uk_vies UNIQUE (id_via),
    CONSTRAINT fk_vies_sectors FOREIGN KEY (id_sector)
				REFERENCES sectors (id_sector),
	CONSTRAINT fk_vies_tipus_via FOREIGN KEY (id_tipus_via)
				REFERENCES tipus_via (id_tipus_via)
) ;

CREATE TABLE disponibilitats
(
	id_disponibilitat 	INT AUTO_INCREMENT,
	id_via				INT,
    inici				DATE,
    final				DATE,
    rao					VARCHAR(40),
    CONSTRAINT pk_disponibilitat PRIMARY KEY (id_disponibilitat),
    CONSTRAINT uk_disponibilitat UNIQUE (id_disponibilitat),
    CONSTRAINT fk_disponibilitat_vies FOREIGN KEY (id_via)
				REFERENCES vies (id_via)
) ;

CREATE TABLE llars
(
	id_llar				INT AUTO_INCREMENT,
    id_via				INT,
    metres				TINYINT,
    CONSTRAINT pk_llars PRIMARY KEY (id_llar),
    CONSTRAINT uk_llars UNIQUE (id_llar),
    CONSTRAINT fk_llars_vies FOREIGN KEY (id_via)
				REFERENCES vies (id_via)
);

CREATE TABLE escaladors (
id_escalador INT AUTO_INCREMENT,
dni			CHAR(9),
nom 		VARCHAR(20),
edat 		TINYINT,
estil 		TINYINT COMMENT'1.esportiva, 2.clàssica, 3.gel' ,

CONSTRAINT pk_escaladors PRIMARY KEY(id_escalador),
CONSTRAINT uk_escaladors UNIQUE (id_escalador,dni),
CONSTRAINT ck_escaladors_estil CHECK(estil BETWEEN 1 AND 3)
);

CREATE TABLE escaladors_vies (
    id_escalador    INT,
    id_via          INT,
   
    -- PK 
    CONSTRAINT pk_escalados_vies PRIMARY KEY (id_escalador, id_via),

    -- ID VIA: Ahora solo apunta al ID, que es la Primary Key de 'vies'
    CONSTRAINT fk_escaladors_vies_vies FOREIGN KEY(id_via)
        REFERENCES vies(id_via),
        
    -- ID ESCALADOR
    CONSTRAINT fk_escaladors_vies_escaladors FOREIGN KEY(id_escalador)
        REFERENCES escaladors(id_escalador)
);

CREATE VIEW vista_vias_per_escola AS
SELECT 
    e.id_escola,
    e.nom,
    COUNT(v.id_via) AS numero_vias
FROM escoles e
JOIN sectors s ON e.id_escola = s.id_escola
LEFT JOIN vies v ON s.id_sector = v.id_sector
GROUP BY e.id_escola, e.nom;

CREATE VIEW vista_vias_per_sector AS
SELECT 
    s.id_sector,
    s.nom,
    COUNT(v.id_via) AS numero_vias
FROM sectors s
LEFT JOIN vies v ON s.id_sector = v.id_sector
GROUP BY s.id_sector, s.nom;







