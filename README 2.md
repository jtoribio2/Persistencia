# App Escalada

Aplicació backend desenvolupada en Java + JDBC per a la gestió d'escoles, sectors, vies i escaladors d'escalada.

Projecte realitzat com a pràctica de persistència de dades en DAW.

---

# Tecnologies utilitzades

* Java
* JDBC
* MySQL
* SQL
* Arquitectura per capes
* Patró DAO
* Patró DTO

---

# Arquitectura del projecte

El projecte està organitzat seguint una arquitectura separada per capes:

```text
src/
├── controller/
├── service/
├── dao/
├── model/
├── dto/
├── view/
├── config/
└── db/
```

## Estructura de capes

### View

Encarregada de mostrar menús i interacció amb l'usuari.

### Controller

Recull la informació introduïda per l'usuari i coordina la crida als Services.

### Service

Conté la lògica de negoci:

* Validacions
* Regles d'aplicació
* Restriccions
* Gestió de dependències
* Coordinació entre DAOs

### DAO

Accés directe a la base de dades mitjançant JDBC i SQL.

### Model

Conté les entitats principals del projecte.

### DTO

Utilitzats per a consultes complexes i resultats personalitzats.

---

# Funcionalitats implementades

## CRUD complet de:

* Escoles
* Sectors
* Vies
* Escaladors

---

# Consultes implementades

* Mostrar vies disponibles d'una Escola
* Cercar vies per dificultat
* Cercar vies per estat
* Consultar escoles amb restriccions actives
* Mostrar sectors amb més de X vies disponibles
* Mostrar escaladors amb el mateix nivell màxim
* Mostrar vies que han passat recentment a "Apte"
* Mostrar les vies més llargues d'una Escola

---

# Base de dades

L'aplicació utilitza MySQL i JDBC pur.

Inclou:

* Foreign Keys
* Constraints
* Relacions N:M
* Vistes SQL
* DTOs personalitzats
* Relacions jeràrquiques entre entitats

---

# Relacions principals

```text
Escola → Sectors
Sector → Vies
Vies → Disponibilitats
Vies → Llars
Escaladors ↔ Vies
```

---

# Flux d'informació

La informació segueix el recorregut següent dins de l'aplicació:

```text
View → Controller → Service → DAO → Base de dades
```

Cada capa té responsabilitats separades per mantenir una arquitectura neta i desacoblada.

---

# Gestió de dependències

Un dels principals reptes del projecte va ser gestionar correctament les dependències entre entitats relacionades.

L'existència de múltiples Foreign Keys va obligar a implementar un flux d'eliminació manual per evitar errors d'integritat.

L'ordre d'eliminació va quedar plantejat de la manera següent:

```text
Disponibilitats / Llars / Escaladors_Vies
                ↓
              Vies
                ↓
             Sectors
                ↓
             Escoles
```

Això permet eliminar correctament entitats complexes mantenint la integritat de les dades.

---

# Creació d'entitats relacionades

Un altre dels problemes importants va ser la creació d'entitats dependents entre si.

Per exemple:

```text
Crear Escola + Sector + Via
```

Això va obligar a treballar amb IDs autogenerats i reorganitzar el flux de creació.

El flux final va quedar així:

```text
1. Crear Escola
2. Obtenir ID generat
3. Crear Sector utilitzant l'ID de l'Escola
4. Obtenir ID del Sector
5. Crear Via utilitzant l'ID del Sector
```

---

# Rollback manual

Es va implementar un rollback manual per evitar inconsistències quan una operació complexa fallava a mig procés.

Exemple:

```text
Escola creada correctament
Sector creat correctament
Error creant Via
```

En aquest cas s'eliminen automàticament les entitats creades prèviament per mantenir la consistència a la base de dades.

---

# Validacions implementades

El projecte implementa validacions tant en Java com en SQL.

Algunes d'elles són:

* Popularitat vàlida
* IDs positius
* DNI únic
* Camps obligatoris
* Restriccions de tipus de via
* Relacions vàlides entre entitats

---

# Restricció entre tipus de vies

Es va decidir implementar la regla de negoci següent:

```text
Una Escola només pot contenir:
- Vies GEL
o
- Vies Clàssiques / Esportives
```

Això va obligar a implementar validacions addicionals abans d'inserir noves vies.

---

# Organització de menús

La navegació es va separar en diferents menús independents:

```text
MainMenu
CrearMenu
BuscarMenu
ModificarMenu
EliminarMenu
ConsultarMenu
LlistarMenu
```

Això va permetre separar completament la navegació de la lògica de negoci.

---

# Ús de DTOs

A mesura que les consultes van créixer en complexitat es van implementar DTOs específics per desacoblar les entitats principals de les consultes SQL.

Alguns exemples:

* ViaPerDifDTO
* SectorViaDispDTO
* ViesLlarguesDTO
* EscaladorNivellDTO
* EscolesRestricDTO

---

# Problemes i decisions tècniques durant el desenvolupament

## Gestió de dependències entre entitats

Un dels majors reptes del projecte va ser gestionar correctament les relacions entre entitats i l'ordre d'eliminació de dades.

L'aplicació treballa amb múltiples relacions:

```text
Escola → Sectors
Sector → Vies
Vies → Disponibilitats
Vies → Llars
Escaladors ↔ Vies
```

Això provocava problemes en eliminar registres, ja que les Foreign Keys impedien esborrar entitats que encara tenien dependències actives.

Per exemple:

* No es podia eliminar una Escola si encara existien Sectors associats.
* No es podia eliminar un Sector si contenia Vies.
* No es podia eliminar una Via si seguien existint relacions a taules auxiliars.

Per solucionar-ho va ser necessari implementar un flux d'eliminació manual seguint l'ordre correcte de dependències.

---

# Aprenentatge adquirit

El projecte va permetre aprofundir en:

* Arquitectura backend
* JDBC
* SQL relacional
* Foreign Keys
* Patró DAO
* Patró DTO
* Gestió de dependències
* Flux entre capes
* Validacions
* Integritat de dades
* Gestió d'errors
* Organització professional de projectes Java

A més, va permetre comprendre problemes reals relacionats amb aplicacions backend i bases de dades relacionals.

---

# Execució del projecte

## 1. Crear la base de dades

Executar l'script SQL inclòs al projecte.

## 2. Configurar la connexió

Modificar:

```text
resources/properties/db.properties
```

amb les dades de connexió corresponents.

## 3. Executar l'aplicació

Executar:

```text
Main.java
```

---

# Autor

Joel

Darwin

Projecte DAW - Persistència de dades