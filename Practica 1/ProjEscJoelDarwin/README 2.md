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

# Gestión de conexiones JDBC

La aplicación trabaja utilizando JDBC puro para conectarse directamente a MySQL.

Cada operación realizada contra la base de datos abre su propia conexión independiente.

El flujo general queda planteado de la siguiente manera:

```text
Controller
   ↓
Service
   ↓
DAO
   ↓
Connection
   ↓
MySQL
```

---

# Apertura de conexiones

Cada método DAO obtiene una nueva conexión utilizando:

```java
Connection conn = provider.getConnection();
```

Esto significa que:

* Cada operación trabaja con su propia conexión.
* Las conexiones no se reutilizan manualmente.
* Cada consulta es independiente de las demás.

Ejemplo simplificado:

```java
try (
    Connection conn = provider.getConnection();
    PreparedStatement ps = conn.prepareStatement(sql)
) {

    ps.executeUpdate();

}
```

---

# Uso de try-with-resources

El proyecto utiliza `try-with-resources`, una funcionalidad de Java que permite cerrar automáticamente los recursos utilizados.

Esto evita problemas típicos como:

* Conexiones abiertas
* Memory leaks
* PreparedStatements sin cerrar
* ResultSets abiertos

Cuando el bloque `try` termina:

```java
try (
    Connection conn = provider.getConnection();
)
```

Java ejecuta automáticamente:

```java
conn.close();
```

aunque ocurra una excepción.

Esto hace que la gestión de recursos sea mucho más segura y limpia.

---

# PreparedStatement

Todas las consultas SQL se realizan mediante `PreparedStatement`.

Ejemplo:

```java
String sql = "SELECT * FROM vies WHERE id_sector = ?";

PreparedStatement ps = conn.prepareStatement(sql);

ps.setInt(1, idSector);
```

Esto permite:

* Evitar SQL Injection
* Reutilizar consultas
* Insertar parámetros dinámicamente
* Mejorar legibilidad

---

# Gestión de errores SQL

Los errores SQL se capturan mediante bloques `catch`.

Ejemplo:

```java
catch (SQLException e) {
    throw new RuntimeException(
        "Error insertando via"
    );
}
```

Esto evita propagar excepciones SQL directamente hacia capas superiores.

---

# Soporte para múltiples tecnologías de base de datos

El proyecto fue preparado para poder cambiar de tecnología de base de datos sin modificar la lógica de negocio de la aplicación.

Actualmente utiliza MySQL, pero la arquitectura permite adaptar DAOs y conexiones para trabajar con otros motores como:

* PostgreSQL
* MariaDB
* SQLite

La separación entre:

```text
DAO
ConnectionProvider
AppConfig
```

permite desacoplar el acceso a datos del resto de la aplicación.

---

# Cambio de tecnología de base de datos

Para cambiar el proyecto a otra tecnología sería necesario:

## 1. Añadir el driver JDBC correspondiente

Ejemplo PostgreSQL:

```text
postgresql-xx.x.x.jar
```

---

## 2. Crear una nueva implementación de conexión

Ejemplo:

```text
PostgresConnection.java
```

Implementando la interfaz:

```text
ConnectionProvider
```

---

## 3. Añadir nuevas credenciales en db.properties

Ejemplo:

```properties
db.url=
db.user=
db.password=
db.driver=
```

---

## 4. Modificar AppConfig

Cambiar el proveedor de conexión utilizado por los DAOs.

Ejemplo:

```java
ConnectionProvider provider =
        new PostgresConnection();
```

---

# Preparado para múltiples bases de datos

La arquitectura actual también permite que diferentes tablas trabajen sobre distintas tecnologías.

Por ejemplo:

```text
Escaladors → PostgreSQL
Vies → MySQL
Disponibilitats → SQLite
```

Esto sería posible creando distintos:

* DAOs
* Providers
* Configuraciones de conexión

La lógica de negocio permanecería prácticamente igual gracias a la separación por capas.

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
