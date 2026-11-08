# SGSA – Sistema de Gestión de Solicitudes Académicas

## Trabajo Práctico Integrador – Metodología de los Sistemas II

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
- **Entorno de desarrollo:** IntelliJ IDEA.
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
```

## 6. Requisitos para ejecutar el proyecto

- Tener instalado JDK 21.
- Contar con Git para clonar el repositorio.
- No es necesario instalar Gradle globalmente, ya que el proyecto incluye Gradle Wrapper.

## 7. Instalación y ejecución

Clonar el repositorio:

```bash
git clone https://github.com/andresro87/SGSA.git
```

Ingresar al directorio:

```bash
cd SGSA
```

En Windows, ejecutar la aplicación:

```powershell
.\gradlew.bat run
```

## 8. Ejecución de pruebas

Para ejecutar todas las pruebas automatizadas:

```powershell
.\gradlew.bat clean test
```

La suite cuenta con 12 pruebas automatizadas que verifican el registro de solicitudes, las validaciones, los cambios de estado, la asignación de responsables, las búsquedas, la persistencia y la protección de la colección interna.

## 9. Documentación del trabajo práctico

En la carpeta `docs/` se encuentra la documentación correspondiente al proceso de evolución del sistema:

- **diagnostico-code-smells.md:** identificación y análisis de problemas de calidad del código.
- **analisis-solid-clean-code.md:** evaluación de los principios SOLID y criterios de Clean Code.
- **refactorings.md:** descripción de las técnicas de refactoring aplicadas.
- **pruebas.md:** casos de prueba y resultados de verificación.
- **codereview.md:** checklist, observaciones, correcciones y resultado de la revisión técnica.

## 10. Versionado y etapas

El repositorio utiliza tags de Git para identificar versiones y etapas del trabajo práctico:

- `estado-inicial`: versión original del sistema antes de los refactorings.
- `etapa-refactoring`: cambios de refactoring y mejoras de calidad.
- `etapa-pruebas`: ampliación y verificación de las pruebas automatizadas.
- `etapa-docs`: documentación del proyecto.

La versión final refactorizada se identificará mediante el tag `estadorefactorizado`, una vez completada la revisión y documentación final.

## 11. Uso de inteligencia artificial

Se utilizó ChatGPT como herramienta de asistencia durante el desarrollo del trabajo práctico. Su utilización incluyó el análisis del código, la identificación de posibles problemas de diseño, la propuesta de refactorings, el apoyo en la elaboración de pruebas y la redacción de documentación.

Las modificaciones fueron aplicadas y verificadas en el entorno local mediante la ejecución de las pruebas automatizadas.

## 12. Repositorio

[GitHub – SGSA](https://github.com/andresro87/SGSA)