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
    nvies		TINYINT,
    popularitat TINYINT COMMENT'1=baixa 2=mitjana 3=alta', 
	CONSTRAINT pk_escoles PRIMARY KEY (id),
    CONSTRAINT uk_escoles_id UNIQUE (id),
    CONSTRAINT ck_escoles_popularitat CHECK (popularitat BETWEEN 1 AND 3)
) ;

CREATE TABLE disponibilitat
(
	id 					INT,
	id_escoles			INT,
    inici				DATE,
    final				DATE,
    CONSTRAINT pk_disponibilitat PRIMARY KEY (id),
    CONSTRAINT uk_disponibilitat UNIQUE (id),
    CONSTRAINT fk_disponibilitat_escoles FOREIGN KEY (id_escoles)
				REFERENCES escoles (id)
) ;


