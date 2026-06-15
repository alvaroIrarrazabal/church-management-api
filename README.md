#  Sistema de Gestión para Iglesias

API REST desarrollada con Java y Spring Boot para la administración integral de iglesias.

El sistema permite gestionar miembros, ministerios, asistencia, diezmos, ofrendas y usuarios, implementando autenticación y autorización segura mediante JWT.

##  Tecnologías utilizadas

* Java 21
* Spring Boot
* Spring Security
* JWT
* JPA / Hibernate
* MySQL
* Maven
* Git

##  Funcionalidades principales

* Gestión de miembros
* Gestión de ministerios
* Control de asistencia
* Registro de diezmos
* Registro de ofrendas
* Administración de usuarios y roles
* Autenticación y autorización con JWT
* Validaciones mediante DTOs
* Arquitectura en capas

##  Arquitectura

El proyecto sigue una arquitectura en capas:

```text
Controller → Service → Repository → Database
```

Estructura principal:

```text
src
└── main
    └── java
        ├── controller
        ├── service
        ├── repository
        ├── entity
        ├── dto
        ├── mapper
        ├── security
        └── config
```

##  Base de datos

Motor de base de datos:

* Postgresql

Entidades principales:

* Usuarios
* Roles
* Miembros
* Ministerios
* Asistencias
* Diezmos
* Ofrendas

##  Endpoints principales

| Método | Endpoint        | Descripción          |
| ------ | --------------- | -------------------- |
| POST   | `/auth/login`   | Iniciar sesión       |
| POST   | `/members`      | Crear miembro        |
| GET    | `/members`      | Listar miembros      |
| PUT    | `/members/{id}` | Actualizar miembro   |
| DELETE | `/members/{id}` | Eliminar miembro     |
| GET    | `/ministries`   | Listar ministerios   |
| POST   | `/attendance`   | Registrar asistencia |
| POST   | `/tithes`       | Registrar diezmo     |
| POST   | `/offerings`    | Registrar ofrenda    |

##  Cómo ejecutar el proyecto

Clona el repositorio:

```bash
git clone https://github.com/alvaroIrarrazabal/church-management-api.git
```

Ingresa al proyecto:

```bash
cd church-management-api
```

Configura las credenciales de la base de datos en:

```text
src/main/resources/application.properties
```

Ejecuta la aplicación:

```bash
./mvnw spring-boot:run
```

La API estará disponible en:

```text
http://localhost:8081
```


##  Autor

Álvaro Irarrázabal

* LinkedIn: https://linkedin.com/in/alvaro-irarrazabal
* GitHub: https://github.com/alvaroIrarrazabal/church-management-api

* ##  Próximas funcionalidades

- [ ] Dockerización del proyecto
- [ ] Tests unitarios con JUnit y Mockito
- [ ] Documentación completa con Swagger/OpenAPI
- [ ] Implementación de microservicios
- [ ] Pipeline CI/CD con GitHub Actions
