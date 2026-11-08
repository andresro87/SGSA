# Etapa de Pruebas y Verificación – SGSA

**Materia:** Metodología de Sistemas II – UTN  
**Integrantes:** Andrés Rodríguez, Francisco López

---

## 1. Objetivo

Verificar que las modificaciones realizadas durante el proceso de refactoring no hayan alterado el comportamiento esperado del Sistema de Gestión de Solicitudes Académicas (SGSA), garantizando **regresión cero** y comprobando el correcto funcionamiento de las validaciones y el encapsulamiento.

---

## 2. Pruebas Automatizadas (JUnit 5)

La suite `SistemaGestionTest` cuenta con 12 pruebas automatizadas que cubren las operaciones del sistema:

| N.º | Caso de Prueba | Comportamiento Verificado | Resultado |
|---|---|---|---|
| 1 | Registro correcto | Permite registrar una solicitud válida con datos completos. | Exitoso |
| 2 | Nombre vacío | Impide registrar una solicitud si el nombre está vacío. | Exitoso |
| 3 | Cambio de estado | Permite pasar el estado de `NUEVA` a `EN_PROCESO`. | Exitoso |
| 4 | Transición inválida | Impide realizar saltos de estado no permitidos (ej. `NUEVA` a `RESUELTA`). | Exitoso |
| 5 | Asignación de responsable | Permite asignar un responsable a una solicitud activa. | Exitoso |
| 6 | Búsqueda y filtrado | Filtra adecuadamente solicitudes según tipo, estado y prioridad. | Exitoso |
| 7 | Persistencia JSON | Guarda y recupera solicitudes correctamente en el archivo JSON. | Exitoso |
| 8 | Legajo vacío | Rechaza el registro si el legajo del estudiante está vacío. | Exitoso |
| 9 | Prioridad inválida | Rechaza prioridades fuera del rango permitido de 1 a 5. | Exitoso |
| 10 | Solicitud resuelta | Impide asignar un responsable a una solicitud en estado finalizado. | Exitoso |
| 11 | ID inexistente | Lanza excepción al intentar operar sobre un ID de solicitud no registrado. | Exitoso |
| 12 | Lista inmutable | Verifica que `obtenerSolicitudes()` devuelva una lista inmutable (`List.copyOf`). | Exitoso |

---

## 3. Resultado de la Ejecución

Se ejecutó la suite completa mediante Gradle:

`.\gradlew.bat clean test`

```text
BUILD SUCCESSFUL in 2s
4 actionable tasks: 4 executed