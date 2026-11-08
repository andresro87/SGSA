**Materia:** Metodología de Sistemas II – UTN  
**Integrantes:** Andrés Rodríguez, Francisco López

---

## Refactoring 1: Traslado de reglas de estado a la entidad (Move Method)

* **Técnica aplicada:** Move Method (reubicación de métodos) y encapsulamiento.
* **Problema detectado:** `SistemaGestion` consultaba el estado interno de `Solicitud` para validar si los cambios de estado o la asignación de responsables eran válidos, concentrando reglas de negocio que correspondían a la entidad.
* **Cambio realizado:** Se incorporaron los métodos `esTransicionValida(nuevoEstado)` y `esFinalizada()` dentro de `Solicitud.java`. `SistemaGestion` ahora delega en la entidad las verificaciones correspondientes.
* **Resultado:** Se mejoró el encapsulamiento del dominio, se acercaron las reglas de negocio a la entidad correspondiente y se simplificó la lógica de `SistemaGestion`.

---

## Refactoring 2: Separación de la persistencia (Extract Class)

* **Técnica aplicada:** Extract Class (extracción de clase) y separación de responsabilidades.
* **Problema detectado:** La clase `SistemaGestion` concentraba la lógica de negocio y las operaciones de lectura y escritura en JSON.
* **Cambio realizado:** Se creó `JsonSolicitudRepository` para centralizar las operaciones de persistencia, trasladando los métodos `guardar()` y `cargar()`. `SistemaGestion` delega estas operaciones al repositorio.
* **Resultado:** Se separaron las responsabilidades de gestión y almacenamiento, mejorando la organización del código y facilitando el mantenimiento. Sin embargo, la dependencia de `SistemaGestion` respecto de la implementación concreta del repositorio permanece como una oportunidad de mejora.

---

## Refactoring 3: Extracción de métodos en el menú y servicio (Extract Method)

* **Técnica aplicada:** Extract Method.
* **Problema detectado:** El método `main()` de `Main.java` y varios métodos de `SistemaGestion.java` acumulaban distintas tareas, como interacción con el usuario, lectura de datos, validaciones y búsquedas.
* **Cambio realizado:**
  - En `Main.java`: Se extrajeron métodos privados para organizar las distintas opciones del menú, como `registrarSolicitud`, `cambiarEstado`, `asignarResponsable` y `buscarFiltrar`.
  - En `SistemaGestion.java`: Se extrajeron métodos auxiliares de validación y procesamiento, como `validarDatosSolicitud()`, `validarTextoObligatorio()`, `calcularSiguienteId()` y `contarPorEstado()`.
* **Resultado:** Se redujo la extensión de los métodos originales y se mejoró la legibilidad, organización y comprensión del código.

---

## Refactoring 4: Introducción de un objeto de parámetros (Introduce Parameter Object)

* **Técnica aplicada:** Introduce Parameter Object.
* **Problema detectado:** El registro de solicitudes requería pasar una lista extensa de parámetros independientes, dificultando la lectura de las llamadas al método.
* **Cambio realizado:** Se incorporó la clase `DatosSolicitud` para agrupar los atributos necesarios para registrar una solicitud: `nombre`, `legajo`, `email`, `tipo`, `prioridad` y `descripcion`.
* **Resultado:** Se simplificó la firma del método `registrarSolicitud(DatosSolicitud datos)`, reduciendo la cantidad de parámetros y mejorando la mantenibilidad.

---

## Verificación de regresión

Después de aplicar los refactorings se ejecutó la suite completa de pruebas automatizadas mediante:

`.\gradlew.bat clean test --no-daemon`

**Resultado:** BUILD SUCCESSFUL.

El reporte de JUnit 5 confirmó:

- Pruebas ejecutadas: 12.
- Pruebas aprobadas: 12.
- Pruebas fallidas: 0.
- Errores: 0.
- Pruebas omitidas: 0.

La ejecución permitió verificar que los cambios realizados conservaron el comportamiento esperado de las funcionalidades cubiertas por las pruebas.