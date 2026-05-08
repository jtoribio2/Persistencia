# PROJECTE DE PERSISTENCIA 

Aplicació de gestió de vies d'escalada , desenvolupada en Java amb  la API JDBC sobre base de dades relacional. Permet administrar vies esportives, clàssiques i de gel, així com les seves escoles, sectors i escaladors.

> Aplicació JDBC per a la gestió de vies, escoles, sectors i escaladors

![Java](https://img.shields.io/badge/Java-21LTS-red) ![JDBC](https://img.shields.io/badge/JDBC-MySQL-blue) 



## Requisits

- Java 21 LTS
- Base de dades relacional : MySQL
- Driver JDBC MYSQL
- Tindre els arxius per crear i inserir dades de a bd de prova 

## Estructura
El projecte segueix una arquitectura per capes. La capa `model.entity` defineix les entitats on seran objectes per tractar en el programa.  

La capa `dao(Implmentacio)` gestiona l'accés a la base de dades i la capa `service` es on farem comprovacions abans de comunicarse amb el dao .  
  
Els controladors de `controller` actuen com a punt d'entrada de les operacions, mentre que `config` gestiona la connexió a la base de dades i `lib` conté controladors per el `JDBC`.

### Estructura de carpetes

```
src/
├── config/         # Configuració de la connexió a la BD
├── controller/     # Controladors de la lògica de negoci
├── dao/
│   ├── impl.mysql/ # Implementació dels DAOs amb MySQL
│   └── interfaces/ # Interfícies dels DAOs
├── db/             # Plantilles de credenacials de la bd...
├── lib/            # Controladors JDBC dels BDs
├── model.entity/   # Entitats del model de dades
├── service/        # Capa de serveis
└── Main.java       # Punt d'entrada de l'aplicació
```

# Sobre la BD 

Hem seleccionat **MySQL** com a sistema de gestió de base de dades relacional perquè és l'opció més fiable i segura per gestionar la informació d'aquest projecte. Aquesta elecció ens garanteix **integritat, consistència i una estructura sòlida**, assegurant que les dades siguin vàlides i estiguin protegides en tot moment.

### Disseny de l'Esquema
Pel que fa a l'arquitectura de les dades, hem aplicat els següents criteris:

*   **Claus Primàries per ID:** Totes les taules utilitzen identificadors numèrics com a claus primàries (*Primary Keys*). Això simplifica la creació de relacions mitjançant claus Foraneas (*Foreign Keys*) i manté l'esquema net.
*   **Optimització amb Java:** Aquesta estructura facilita la interacció des del programa en **Java**, fent que les operacions per inserir, modificar o eliminar informació siguin més directes i el codi sigui més fàcil de mantenir.
*   **Versatilitat:** El projecte s'ha dissenyat de manera flexible; tot i que actualment treballem amb MySQL, el sistema està preparat per suportar el canvi a altres bases de dades en el futur sense haver de refer tot el codi.

## Diagrama  Entitat-Relació 
![alt text](./Practica%201/DIA/Diagrama1.png)  
  

