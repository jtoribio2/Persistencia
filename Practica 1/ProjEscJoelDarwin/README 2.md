# App Escalada

Aplicación backend desarrollada en Java + JDBC para la gestión de escuelas, sectores, vías y escaladores de escalada.

Proyecto realizado como práctica de persistencia de datos en DAW.

---

# Tecnologías utilizadas

* Java
* JDBC
* MySQL
* SQL
* Arquitectura por capas
* DAO Pattern
* DTO Pattern

---

# Arquitectura del proyecto

El proyecto está organizado siguiendo una arquitectura separada por capas:

```text
controller
service
dao
model
dto
view
config
db
```

## Estructura de capas

### View

Encargada de mostrar menús e interacción con el usuario.

### Controller

Recoge la información introducida por el usuario y coordina la llamada a los Services.

### Service

Contiene la lógica de negocio:

* Validaciones
* Reglas de aplicación
* Restricciones
* Gestión de dependencias
* Coordinación entre DAOs

### DAO

Acceso directo a la base de datos mediante JDBC y SQL.

### Model

Contiene las entidades principales del proyecto.

### DTO

Utilizados para consultas complejas y resultados personalizados.

---

# Funcionalidades implementadas

## CRUD completo de:

* Escoles
* Sectors
* Vies
* Escaladors

---

# Consultas implementadas

* Mostrar vías disponibles de una Escola
* Buscar vías por dificultad
* Buscar vías por estado
* Consultar escuelas con restricciones activas
* Mostrar sectores con más de X vías disponibles
* Mostrar escaladores con el mismo nivel máximo
* Mostrar vías que han pasado recientemente a “Apte”
* Mostrar las vías más largas de una Escola

---

# Base de datos

La aplicación utiliza MySQL y JDBC puro.

Incluye:

* Foreign Keys
* Constraints
* Relaciones N:M
* Vistas SQL
* DTOs personalizados
* Relaciones jerárquicas entre entidades

---

# Relaciones principales

```text
Escola → Sectors
Sector → Vies
Vies → Disponibilitats
Vies → Llars
Escaladors ↔ Vies
```

---

# Flujo de información

La información sigue el siguiente recorrido dentro de la aplicación:

```text
View → Controller → Service → DAO → Base de datos
```

Cada capa tiene responsabilidades separadas para mantener una arquitectura limpia y desacoplada.

---

# Gestión de dependencias

Uno de los principales retos del proyecto fue gestionar correctamente las dependencias entre entidades relacionadas.

La existencia de múltiples Foreign Keys obligó a implementar un flujo de eliminación manual para evitar errores de integridad.

El orden de eliminación quedó planteado de la siguiente manera:

```text
Disponibilitats / Llars / Escaladors_Vies
                ↓
              Vies
                ↓
             Sectors
                ↓
             Escoles
```

Esto permite eliminar correctamente entidades complejas manteniendo la integridad de los datos.

---

# Creación de entidades relacionadas

Otro de los problemas importantes fue la creación de entidades dependientes entre sí.

Por ejemplo:

```text
Crear Escola + Sector + Via
```

Esto obligó a trabajar con IDs autogenerados y reorganizar el flujo de creación.

El flujo final quedó así:

```text
1. Crear Escola
2. Obtener ID generado
3. Crear Sector usando el ID de Escola
4. Obtener ID del Sector
5. Crear Via usando el ID del Sector
```

---

# Rollback manual

Se implementó un rollback manual para evitar inconsistencias cuando una operación compleja fallaba a mitad del proceso.

Ejemplo:

```text
Escola creada correctamente
Sector creado correctamente
Error creando Via
```

En este caso se eliminan automáticamente las entidades creadas previamente para mantener consistencia en la base de datos.

---

# Validaciones implementadas

El proyecto implementa validaciones tanto en Java como en SQL.

Algunas de ellas son:

* Popularidad válida
* IDs positivos
* DNI único
* Campos obligatorios
* Restricciones de tipos de vía
* Relaciones válidas entre entidades

---

# Restricción entre tipos de vías

Se decidió implementar la siguiente regla de negocio:

```text
Una Escola solo puede contener:
- Vías GEL
o
- Vías Clásicas / Esportivas
```

Esto obligó a implementar validaciones adicionales antes de insertar nuevas vías.

---

# Organización de menús

La navegación se separó en distintos menús independientes:

```text
MainMenu
CrearMenu
BuscarMenu
ModificarMenu
EliminarMenu
ConsultarMenu
LlistarMenu
```

Esto permitió separar completamente la navegación de la lógica de negocio.

---

# Uso de DTOs

A medida que las consultas crecieron en complejidad se implementaron DTOs específicos para desacoplar las entidades principales de las consultas SQL.

Algunos ejemplos:

* ViaPerDifDTO
* SectorViaDispDTO
* ViesLlarguesDTO
* EscaladorNivellDTO
* EscolesRestricDTO

---

# Problemas y decisiones técnicas durante el desarrollo

## Gestión de dependencias entre entidades

Uno de los mayores retos del proyecto fue gestionar correctamente las relaciones entre entidades y el orden de eliminación de datos.

La aplicación trabaja con múltiples relaciones:

```text
Escola → Sectors
Sector → Vies
Vies → Disponibilitats
Vies → Llars
Escaladors ↔ Vies
```

Esto provocaba problemas al eliminar registros, ya que las Foreign Keys impedían borrar entidades que todavía tenían dependencias activas.

Por ejemplo:

* No se podía eliminar una Escola si todavía existían Sectors asociados.
* No se podía eliminar un Sector si contenía Vies.
* No se podía eliminar una Via si seguían existiendo relaciones en tablas auxiliares.

Para solucionar esto fue necesario implementar un flujo de eliminación manual siguiendo el orden correcto de dependencias.

---

# Aprendizaje adquirido

El proyecto permitió profundizar en:

* Arquitectura backend
* JDBC
* SQL relacional
* Foreign Keys
* DAO Pattern
* DTO Pattern
* Gestión de dependencias
* Flujo entre capas
* Validaciones
* Integridad de datos
* Manejo de errores
* Organización profesional de proyectos Java

Además, permitió comprender problemas reales relacionados con aplicaciones backend y bases de datos relacionales.

---

# Ejecución del proyecto

## 1. Crear la base de datos

Ejecutar el script SQL incluido en el proyecto.

## 2. Configurar conexión

Modificar:

```text
resources/properties/db.properties
```

con los datos de conexión correspondientes.

## 3. Ejecutar aplicación

Ejecutar:

```text
Main.java
```

---

# Autor

Joel

Darwin

Proyecto DAW - Persistencia de dades
