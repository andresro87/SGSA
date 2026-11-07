
# Refactorings realizados

## Refactoring 1: Separación de la persistencia

**Técnica aplicada:** Move Method (reubicación de métodos).

**Problema detectado:**

La clase `SistemaGestion` concentraba tanto la lógica de negocio como las operaciones de persistencia de solicitudes en archivos JSON. Esto generaba una responsabilidad excesiva y dificultaba el mantenimiento.

**Cambio realizado:**

Se creó la clase `JsonSolicitudRepository`, trasladando allí las operaciones de guardar y cargar solicitudes. `SistemaGestion` ahora delega esas tareas al repositorio.

**Resultado:**

Se logró separar la lógica de negocio del acceso a los datos. La clase `SistemaGestion` quedó más enfocada en administrar las solicitudes, mientras que `JsonSolicitudRepository` se ocupa de la persistencia.

**Verificación:**

Se ejecutó `.\gradlew.bat clean test`.

Resultado: 7 pruebas ejecutadas, 0 fallidas.


## Refactoring 2: Extracción de métodos en el menú principal

**Técnica aplicada:** Extract Method.

**Problema detectado:**

El método `main()` concentraba la presentación del menú, la lectura de datos, la interacción con el usuario y la ejecución de todas las operaciones. Esto dificultaba su lectura y mantenimiento.

**Cambio realizado:**

Se extrajeron métodos privados para mostrar el encabezado, presentar el menú, registrar solicitudes, cambiar estados, asignar responsables, buscar solicitudes y listar los registros.

**Resultado:**

El método `main()` quedó reducido al control del ciclo principal y la selección de opciones. Cada operación tiene ahora una responsabilidad más clara y delimitada.

**Verificación:**

Se ejecutaron las pruebas automatizadas mediante `.\gradlew.bat clean test` para comprobar que el refactoring no afectara el comportamiento existente.


## Refactoring 3: Introducción de un objeto de parámetros

**Técnica aplicada:** Introduce Parameter Object.

**Problema detectado:**

El método `registrarSolicitud()` recibía seis parámetros independientes relacionados con una misma solicitud. Esto dificultaba su lectura y hacía menos práctica su utilización.

**Cambio realizado:**

Se creó la clase `DatosSolicitud`, que agrupa los datos del estudiante y de la solicitud: nombre, legajo, email, tipo, prioridad y descripción.

Se modificó el método `registrarSolicitud()` para recibir un único objeto de esta clase.

**Resultado:**

Se simplificó la firma del método y se mejoró la organización de los datos necesarios para registrar una solicitud.

**Verificación:**

Se ejecutó `.\gradlew.bat clean test`.

Resultado: BUILD SUCCESSFUL. Las siete pruebas automatizadas finalizaron correctamente.



## Refactoring 4: Extracción de las validaciones

**Técnica aplicada:** Extract Method.

**Problema detectado:**

El método `registrarSolicitud()` concentraba la validación de todos los datos junto con la creación y el almacenamiento de la solicitud. Esto aumentaba su extensión y dificultaba identificar cada responsabilidad.

**Cambio realizado:**

Se extrajo la validación a un nuevo método privado llamado `validarDatosSolicitud()`. Este método comprueba los datos recibidos antes de permitir el registro.

**Resultado:**

Se separaron las responsabilidades de validación y registro. El método principal quedó más breve y fácil de comprender.

**Verificación:**

Se ejecutaron las pruebas automatizadas mediante `.\gradlew.bat clean test` para comprobar que el cambio conservara el comportamiento esperado.
