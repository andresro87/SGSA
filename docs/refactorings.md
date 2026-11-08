# Refactorings Realizados – SGSA

**Materia:** Metodología de Sistemas II – UTN  
**Integrantes:** Andrés Rodríguez, Francisco López

---

## Refactoring 1: Traslado de reglas de estado a la entidad (Move Method)

* **Técnica aplicada:** Move Method (reubicación de métodos) / Encapsulamiento.
* **Problema detectado:** `SistemaGestion` consultaba el estado interno de `Solicitud` para validar si el cambio de estado o la asignación de responsable eran válidos, violando el encapsulamiento.
* **Cambio realizado:** Se crearon los métodos `esTransicionValida(nuevoEstado)` y `esFinalizada()` dentro de `Solicitud.java`. `SistemaGestion` ahora delega la validación directamente a la entidad.
* **Resultado:** Se reforzó el encapsulamiento del dominio y se simplificó la lógica en `SistemaGestion`.

---

## Refactoring 2: Separación de la persistencia (Extract Class / Move Method)

* **Técnica aplicada:** Separation of Concerns (Separación de Responsabilidades).
* **Problema detectado:** La clase `SistemaGestion` concentraba la lógica de negocio y las operaciones de lectura/escritura en JSON.
* **Cambio realizado:** Se centralizó la persistencia en `JsonSolicitudRepository`, trasladando las operaciones `guardar()` y `cargar()`. `SistemaGestion` delega la persistencia al repositorio.
* **Resultado:** Desacoplamiento entre la lógica de negocio y la infraestructura de almacenamiento.

---

## Refactoring 3: Extracción de métodos en el menú y servicio (Extract Method)

* **Técnica aplicada:** Extract Method.
* **Problema detectado:** El método `main()` en `Main.java` y varios métodos de `SistemaGestion.java` acumulaban múltiples responsabilidades (UI, lecturas, validaciones repetidas, búsquedas).
* **Cambio realizado:** 
  - En `Main.java`: Se extrajeron métodos privados para cada opción del menú (`registrarSolicitud`, `cambiarEstado`, `asignarResponsable`, `buscarFiltrar`, etc.).
  - En `SistemaGestion.java`: Se extrajeron métodos de validación como `validarDatosSolicitud()`, `validarTextoObligatorio()`, `calcularSiguienteId()` y `contarPorEstado()`.
* **Resultado:** Métodos breves, legibles y con una sola responsabilidad.

---

## Refactoring 4: Introducción de un objeto de parámetros (Introduce Parameter Object)

* **Técnica aplicada:** Introduce Parameter Object.
* **Problema detectado:** El registro de solicitudes requería pasar una lista extensa de parámetros independientes.
* **Cambio realizado:** Se utilizó la clase `DatosSolicitud` para empaquetar los atributos requeridos (`nombre`, `legajo`, `email`, `tipo`, `prioridad`, `descripcion`).
* **Resultado:** Simplificación de la firma de `registrarSolicitud(DatosSolicitud datos)` y mayor mantenibilidad.

---

## Verificación de Regresión

Después de aplicar los refactorings se ejecutó:

`.\gradlew.bat clean test`

**Resultado:** **BUILD SUCCESSFUL** (12/12 pruebas unitarias pasadas correctamente).