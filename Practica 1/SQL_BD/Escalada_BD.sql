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
    latitut				DECIMAL (9,6),
    longitut			DECIMAL (5,2),
    aproximacio			VARCHAR (50),
    popularitat 		TINYINT COMMENT'1=baixa 2=mitjana 3=alta', 
    CONSTRAINT pk_sectors PRIMARY KEY (id),
    CONSTRAINT uk_sectors UNIQUE (id),
    CONSTRAINT fk_sectors_escoles FOREIGN KEY (id_escoles)
				REFERENCES escoles (id)
) ;

CREATE TABLE tipus_via
(
	id					INT,
    tipus				VARCHAR(10),
    CONSTRAINT pk_tipus_via PRIMARY KEY (id),
    CONSTRAINT uk_tipus_via UNIQUE (id)
);

CREATE TABLE vies
(
	id 					INT,
	id_sector			INT,
    id_tipus_via		INT,
    nom					VARCHAR (20),
    llargada			TINYINT,
    dificultat			CHAR (3),
    orientacio			CHAR (2),
    ancoratge 			CHAR (7),
    troca				VARCHAR (11),
    CONSTRAINT pk_vies PRIMARY KEY (id),
    CONSTRAINT uk_vies UNIQUE (id),
    CONSTRAINT fk_vies_sectors FOREIGN KEY (id_sector)
				REFERENCES sectors (id),
	CONSTRAINT fk_vies_tipus_via FOREIGN KEY (id_tipus_via)
				REFERENCES tipus_via (id)
) ;

CREATE TABLE disponibilitats
(
	id 					INT,
	id_via				INT,
    inici				DATE,
    final				DATE,
    rao					VARCHAR(40),
    CONSTRAINT pk_disponibilitat PRIMARY KEY (id),
    CONSTRAINT uk_disponibilitat UNIQUE (id),
    CONSTRAINT fk_disponibilitat_vies FOREIGN KEY (id_via)
				REFERENCES vies (id)
) ;

CREATE TABLE llars
(
	id					INT,
    id_via				INT,
    metres				TINYINT,
    CONSTRAINT pk_llars PRIMARY KEY (id),
    CONSTRAINT uk_llars UNIQUE (id),
    CONSTRAINT fk_llars_vies FOREIGN KEY (id_via)
				REFERENCES vies (id)
);

CREATE TABLE escaladors (
id 			INT,
nom 		VARCHAR(20),
edat 		TINYINT,
estil 		TINYINT COMMENT'1.esportiva, 2.clàssica, 3.gel' ,

CONSTRAINT pk_escaladors PRIMARY KEY(id),
CONSTRAINT ck_escaladors_estil CHECK(estil BETWEEN 1 AND 3)
);

CREATE TABLE escaladors_vies (
    id_escalador    INT,
    id_via          INT,
   
    -- PK 
    CONSTRAINT pk_escalados_vies PRIMARY KEY (id_escalador, id_via),

    -- ID VIA: Ahora solo apunta al ID, que es la Primary Key de 'vies'
    CONSTRAINT fk_escaladors_vies_vies FOREIGN KEY(id_via)
        REFERENCES vies(id),
        
    -- ID ESCALADOR
    CONSTRAINT fk_escaladors_vies_escaladors FOREIGN KEY(id_escalador)
        REFERENCES escaladors(id)
);

CREATE VIEW vista_vias_per_escola AS
SELECT 
    e.id,
    e.nom,
    COUNT(v.id) AS numero_vias
FROM escoles e
JOIN sectors s ON e.id = s.id_escoles
LEFT JOIN vies v ON s.id = v.id_sector
GROUP BY e.id, e.nom;

CREATE VIEW vista_vias_per_sector AS
SELECT 
    s.id,
    s.nom,
    COUNT(v.id) AS numero_vias
FROM sectors s
LEFT JOIN vies v ON s.id = v.id_sector
GROUP BY s.id, s.nom;







