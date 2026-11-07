# Etapa de pruebas y verificación

## Objetivo

Verificar que las modificaciones realizadas durante el proceso de refactoring no hayan alterado el comportamiento esperado del Sistema de Gestión de Solicitudes Académicas (SGSA). También se busca comprobar el funcionamiento de las validaciones incorporadas y la protección de los datos internos.

## Pruebas automatizadas

Se utilizaron pruebas unitarias con JUnit 5 para verificar el comportamiento de las operaciones principales del sistema.

| N.º | Prueba | Comportamiento verificado |
|---|---|---|
| 1 | Registro correcto | Permite registrar una solicitud válida. |
| 2 | Nombre vacío | Impide registrar una solicitud sin nombre. |
| 3 | Cambio de estado | Permite pasar de NUEVA a EN_PROCESO. |
| 4 | Transición inválida | Impide realizar transiciones de estado no permitidas. |
| 5 | Asignación de responsable | Permite asignar un responsable correctamente. |
| 6 | Búsqueda y filtrado | Filtra solicitudes según tipo y estado. |
| 7 | Persistencia JSON | Permite guardar y recuperar solicitudes. |
| 8 | Legajo vacío | Rechaza solicitudes sin legajo. |
| 9 | Prioridad inválida | Rechaza prioridades fuera del rango permitido de 1 a 5. |
| 10 | Solicitud resuelta | Impide asignar un responsable a una solicitud finalizada. |
| 11 | ID inexistente | Rechaza operaciones sobre solicitudes que no existen. |
| 12 | Lista inmutable | Impide modificar directamente la colección interna de solicitudes. |

## Resultado de la ejecución

Se ejecutó la suite completa de pruebas mediante Gradle:

`./gradlew.bat clean test`

**Resultado:** BUILD SUCCESSFUL.

Las 12 pruebas automatizadas finalizaron correctamente.

## Conclusión

Los resultados permiten comprobar que las funcionalidades principales continúan operativas después de los refactorings. Además, las pruebas incorporadas verifican las nuevas validaciones y la protección del estado interno del sistema, contribuyendo a mejorar la confiabilidad y mantenibilidad del código.