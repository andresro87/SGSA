# SGSA – Sistema de Gestión de Solicitudes Académicas

## Trabajo Práctico Integrador – Metodología de Sistemas II

**Universidad Tecnológica Nacional (UTN)**  
**Tecnicatura Universitaria en Programación**

**Integrantes:**
- Andrés Rodríguez
- Francisco López

---

## 1. Descripción del proyecto

El Sistema de Gestión de Solicitudes Académicas (SGSA) es una aplicación de consola desarrollada en Java que permite administrar solicitudes realizadas por estudiantes.

El proyecto se utiliza como caso de estudio para analizar la calidad del software, identificar deuda técnica y aplicar técnicas de refactoring orientadas a mejorar la organización, legibilidad y mantenibilidad del código, preservando el comportamiento funcional existente.

## 2. Objetivo

Evolucionar un sistema existente mediante la identificación de problemas de diseño y calidad interna, la aplicación de buenas prácticas de programación y la verificación de que las modificaciones realizadas no alteren el comportamiento esperado.

## 3. Funcionalidades principales

El sistema permite:

- Registrar solicitudes académicas.
- Cambiar el estado de las solicitudes respetando las transiciones permitidas.
- Asignar responsables a las solicitudes.
- Buscar y filtrar solicitudes por estado, tipo y prioridad.
- Consultar el listado de solicitudes registradas.
- Generar un reporte general.
- Guardar y recuperar la información mediante archivos JSON.

## 4. Tecnologías utilizadas

| Tecnología | Uso |
|---|---|
| Java 21 | Lenguaje de programación |
| Gradle | Construcción y gestión de dependencias |
| Gson 2.13.1 | Serialización y persistencia JSON |
| JUnit 5.12.2 | Pruebas automatizadas |
| Git | Control de versiones |
| GitHub | Repositorio remoto |

## 5. Modelo general del sistema

Los principales componentes son:

- `Solicitud`: representa una solicitud académica y contiene sus reglas de transición de estado.
- `EstadoSolicitud`: representa los estados posibles de una solicitud.
- `Estudiante`: contiene los datos del estudiante.
- `Responsable`: representa a la persona o área responsable de gestionar una solicitud.
- `SistemaGestion`: administra las operaciones y reglas de negocio.
- `SolicitudRepository`: interfaz que declara las operaciones de persistencia, aunque actualmente no está integrada en SistemaGestion.
- `JsonSolicitudRepository`: implementa la persistencia mediante archivos JSON.
- `DatosSolicitud`: agrupa los datos necesarios para registrar una solicitud.
- `Main`: contiene el punto de entrada y la interacción por consola.

## 6. Estructura del proyecto

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
│   │   ├── java/ar/edu/utn/sgsa/
│   │   │   ├── DatosSolicitud.java
│   │   │   ├── EstadoSolicitud.java
│   │   │   ├── Estudiante.java
│   │   │   ├── JsonSolicitudRepository.java
│   │   │   ├── Main.java
│   │   │   ├── Responsable.java
│   │   │   ├── SistemaGestion.java
│   │   │   ├── Solicitud.java
│   │   │   └── SolicitudRepository.java
│   │   └── resources/
│   └── test/
│       ├── java/ar/edu/utn/sgsa/
│       │   └── SistemaGestionTest.java
│       └── resources/
├── build.gradle.kts
├── gradlew
├── gradlew.bat
├── settings.gradle.kts
└── README.md
```

## 7. Requisitos para ejecutar el proyecto

Para ejecutar el sistema se necesita:

- JDK 21 o superior.
- Git, si se desea clonar el repositorio.
- Windows PowerShell o una terminal compatible con Gradle Wrapper.

No es necesario instalar Gradle por separado, ya que el proyecto incluye el Gradle Wrapper.

## 8. Instalación y ejecución

Clonar el repositorio:

```bash
git clone https://github.com/andresro87/SGSA.git
```

Ingresar a la carpeta:

```bash
cd SGSA
```

En Windows, ejecutar la aplicación mediante:

```powershell
.\gradlew.bat run
```

## 9. Ejecución de pruebas automatizadas

Para ejecutar la suite completa de pruebas:

```powershell
.\gradlew.bat clean test
```

El proyecto cuenta con 12 pruebas automatizadas desarrolladas con JUnit 5.

En la última ejecución verificada se obtuvieron los siguientes resultados:

- Pruebas ejecutadas: 12.
- Pruebas aprobadas: 12.
- Pruebas fallidas: 0.
- Errores: 0.
- Pruebas omitidas: 0.

Resultado de Gradle: `BUILD SUCCESSFUL`.

El reporte detallado se genera en:

```text
build/test-results/test/
```

## 10. Documentación y evidencias

En la carpeta `docs/` se encuentra la documentación técnica del trabajo:

- `diagnostico-code-smells.md`: identificación de problemas de calidad del código.
- `analisis-solid-clean-code.md`: análisis de principios SOLID y criterios de Clean Code.
- `refactorings.md`: descripción de las técnicas de refactoring aplicadas.
- `pruebas.md`: documentación de las pruebas automatizadas.
- `codereview.md`: revisión técnica y observaciones detectadas.

La carpeta `evidencias/` contiene los registros de las distintas etapas del proyecto. Actualmente incluye las evidencias correspondientes a la versión inicial.

## 11. Uso de inteligencia artificial

Se utilizó ChatGPT como herramienta de asistencia durante el desarrollo y la documentación del trabajo, principalmente para analizar problemas de diseño, comprender técnicas de refactoring, revisar código, interpretar resultados de pruebas y mejorar la redacción de la documentación.

Las decisiones finales, modificaciones del proyecto y verificaciones fueron realizadas y revisadas por los integrantes del equipo.

---

**Trabajo Práctico Integrador – Metodología de Sistemas II**  
**UTN – Tecnicatura Universitaria en Programación**