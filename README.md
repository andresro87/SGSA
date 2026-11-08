# SGSA – Sistema de Gestión de Solicitudes Académicas

## Trabajo Práctico Integrador – Metodología de los Sistemas II

**Universidad Tecnológica Nacional (UTN) - Tecnicatura Universitaria en Programación**

**Integrantes del Grupo:**
- **Andrés Rodríguez**
- **Francisco López**


El SGSA es una aplicación de consola desarrollada en Java que permite gestionar solicitudes académicas realizadas por estudiantes. El proyecto se utiliza como caso de estudio para analizar la calidad del software, identificar deuda técnica y aplicar técnicas de refactoring orientadas a mejorar la mantenibilidad del código.

## 1. Objetivo del proyecto

El objetivo es evolucionar un sistema existente mediante la identificación de problemas de diseño y calidad interna, la aplicación de buenas prácticas de programación y la verificación de que los cambios realizados no alteren el comportamiento esperado.

## 2. Funcionalidades principales

El sistema permite:

- Registrar solicitudes académicas.
- Cambiar el estado de las solicitudes respetando las transiciones permitidas.
- Asignar responsables a las solicitudes.
- Buscar y filtrar solicitudes por estado, tipo y prioridad.
- Consultar el listado de solicitudes registradas.
- Generar un reporte general.
- Guardar y recuperar la información mediante archivos JSON.

## 3. Tecnologías utilizadas

- **Lenguaje:** Java 21.
- **Entorno de desarrollo:** VSCodium / IntelliJ IDEA.
- **Gestor de dependencias y construcción:** Gradle.
- **Persistencia:** archivos JSON.
- **Biblioteca de serialización:** Gson.
- **Pruebas automatizadas:** JUnit 5.
- **Control de versiones:** Git y GitHub.

## 4. Modelo general del sistema

Las principales entidades y componentes son:

- `Solicitud`: representa una solicitud académica.
- `Estudiante`: contiene los datos del estudiante que realiza la solicitud.
- `Responsable`: representa a la persona o área responsable de gestionar una solicitud.
- `SistemaGestion`: administra las operaciones y reglas de negocio.
- `JsonSolicitudRepository`: centraliza la persistencia de solicitudes.
- `DatosSolicitud`: agrupa los datos necesarios para registrar una solicitud.

## 5. Estructura del proyecto

```text
SGSA/
├── docs/
│   ├── analisis-solid-clean-code.md
│   ├── codereview.md
│   ├── diagnostico-code-smells.md
│   ├── pruebas.md
│   └── refactorings.md
├── evidencias/
│   └── version-inicial/
├── src/
│   ├── main/
│   │   └── java/ar/edu/utn/sgsa/
│   └── test/
│       └── java/ar/edu/utn/sgsa/
├── build.gradle.kts
├── gradlew
├── gradlew.bat
└── README.md