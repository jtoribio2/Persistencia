# Projecte Escalada — Joel & Darwin

Aplicació de gestió d'escalada desenvolupada en Java. Permet administrar escoles, sectors i vies, incloent la gestió de disponibilitats i escaladors.

---

##  Estructura del Projecte

```
/
├── DIA/                  # Diagrama Entitat-Relació de la base de dades
├── ProjEscJoelDarwin/    # Aplicació Java principal
└── SQL_BD/             # Scripts SQL (estructura i dades de la BD)
```

---

##  Base de Dades

Els scripts de la carpeta `SQL_BD/` contenen:

- **Estructura**: Creació de taules, claus primàries i forànies.
- **Dades**: Inserció de dades de prova.

### Taules principals

| Taula | Descripció |
|---|---|
| `escoles` | Escoles d'escalada |
| `sectors` | Sectors pertanyents a una escola |
| `vies` | Vies pertanyents a un sector |
| `escaladors` | Escaladors registrats |
| `disponibilitats` | Disponibilitat dels escaladors a les vies |

### Relacions clau

- Una **escola** té múltiples **sectors**
- Un **sector** té múltiples **vies**
- Una **via** pot tenir **restriccions**

> El diagrama entitat-relació complet es troba a la carpeta `DIA/`.

---

##  Aplicació Java

Ubicada a `ProjEscJoelDarwin/`, és una aplicació de prova que implementa el patró **DAO** per a la gestió de la base de dades.

### Tecnologies

- Java
- MySQL
- JDBC (connexió a la BD mitjançant `PreparedStatement`)

### Funcionalitats principals

- Cercar sectors per escola
- Cercar vies per sector
- Eliminar vies i les seves dependències (disponibilitats, escaladors) per sector
- Eliminar sectors amb totes les seves vies associades
... 
---

## Configuració

1. Executa els scripts de `SQL_BD/` al teu servidor MySQL per crear la base de dades i carregar les dades.
2. Configura la connexió al projecte Java (host, usuari, contrasenya, nom de la BD).
3. Executa l'aplicació des de `ProjEscJoelDarwin/`.

---

## Autors

- Joel
- Darwin