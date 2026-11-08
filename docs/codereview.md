# Code Review – Sistema SGSA

## 1. Objetivo

Realizar una revisión técnica del Sistema de Gestión de Solicitudes Académicas (SGSA) después de los refactorings, para identificar posibles problemas de calidad interna, verificar el cumplimiento de buenas prácticas y comprobar que las funcionalidades existentes continúen funcionando correctamente.

## 2. Modalidad de revisión

Se realizó una autorrevisión técnica asistida por ChatGPT, utilizando criterios de Clean Code, principios SOLID, manejo de excepciones y pruebas automatizadas.

La revisión se concentró en las clases `Main`, `SistemaGestion`, `JsonSolicitudRepository` y `Solicitud`, junto con las pruebas de `SistemaGestionTest`.

## 3. Checklist de revisión

| Criterio | Resultado | Observación |
|---|---|---|
| Nombres de clases y métodos comprensibles | Cumple | Los nombres permiten identificar las responsabilidades principales. |
| Métodos con responsabilidades definidas | Cumple parcialmente | Se extrajeron métodos, aunque existen oportunidades de simplificación. |
| Separación entre negocio y persistencia | Cumple | La persistencia se delegó a `JsonSolicitudRepository`. |
| Validación de datos de entrada | Cumple parcialmente | Se validan los datos principales, pero existen posibles mejoras. |
| Manejo de excepciones específicas | Corregido | Se reemplazó la captura genérica de excepciones en `Main`. |
| Consistencia de mensajes al usuario | Corregido | Se ajustó el rango de prioridades mostrado en el menú. |
| Protección de la colección interna | Cumple parcialmente | La lista es inmutable, pero los objetos `Solicitud` siguen siendo modificables. |
| Pruebas automatizadas | Cumple | Se ejecutaron correctamente las 12 pruebas existentes. |
| Persistencia de información | Cumple | Se mantiene el almacenamiento de solicitudes en formato JSON. |

## 4. Observaciones detectadas

### Observación 1: Inconsistencia en el rango de prioridades

**Archivo:** `Main.java`

**Problema:** El menú indicaba prioridades de 1 a 3, mientras que las validaciones del sistema aceptan valores de 1 a 5.

**Impacto:** Podía generar confusión en el usuario.

**Corrección aplicada:** Se modificó el mensaje del menú para indicar el rango de 1 a 5.

**Estado:** Corregido.

### Observación 2: Captura genérica de excepciones

**Archivo:** `Main.java`

**Problema:** Se utilizaba `catch (Exception e)` para capturar cualquier excepción.

**Impacto:** Podía ocultar errores de programación y dificultar su diagnóstico.

**Corrección aplicada:** Se reemplazó por una captura específica de `IllegalArgumentException` e `IllegalStateException`, manteniendo el tratamiento separado de `NumberFormatException`.

**Estado:** Corregido.

### Observación 3: Objetos del dominio modificables

**Archivo:** `Solicitud.java`

**Problema:** La clase permite modificar sus atributos mediante métodos setters públicos. Aunque `obtenerSolicitudes()` devuelve una lista inmutable, los objetos contenidos en ella todavía pueden modificarse.

**Impacto:** Existe el riesgo de alterar información sin pasar por las reglas de negocio de `SistemaGestion`.

**Recomendación:** Evaluar una encapsulación más estricta de las entidades o utilizar objetos de solo lectura.

**Estado:** Pendiente como mejora futura.

### Observación 4: Dependencia concreta del repositorio

**Archivo:** `SistemaGestion.java`

**Problema:** La clase crea directamente una instancia de `JsonSolicitudRepository`.

**Impacto:** Esto dificulta reemplazar el mecanismo de persistencia o utilizar implementaciones alternativas en las pruebas.

**Recomendación:** Incorporar una abstracción de repositorio e inyección de dependencias en una evolución posterior.

**Estado:** Pendiente como mejora futura.

## 5. Verificación posterior a las correcciones

Después de aplicar las correcciones se ejecutó la suite completa de pruebas automatizadas mediante el siguiente comando:

`.\gradlew.bat clean test --no-daemon`

**Resultado:** BUILD SUCCESSFUL.

El reporte de JUnit 5 confirmó los siguientes resultados:

- Pruebas ejecutadas: 12.
- Pruebas aprobadas: 12.
- Pruebas fallidas: 0.
- Errores: 0.
- Pruebas omitidas: 0.

Esto permitió comprobar que las correcciones realizadas no afectaron el comportamiento esperado de las funcionalidades cubiertas por las pruebas.

## 6. Resultado de la revisión

Las dos observaciones seleccionadas para corrección inmediata fueron resueltas y verificadas mediante pruebas automatizadas.

Las observaciones pendientes se registraron como oportunidades de mejora, ya que no forman parte de los cambios realizados en esta etapa del proyecto. Se considera que abordarlas en una evolución futura permitiría fortalecer la encapsulación de las entidades y reducir el acoplamiento entre la lógica de negocio y la persistencia.

En conclusión, la revisión permitió identificar problemas concretos, aplicar correcciones y dejar documentadas las mejoras pendientes, contribuyendo a una mayor calidad y mantenibilidad del sistema.