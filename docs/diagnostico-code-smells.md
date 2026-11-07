# Diagnóstico de Code Smells – SGSA

## 1. Objetivo

El objetivo de este diagnóstico es identificar problemas de diseño y mantenimiento presentes en la versión inicial del Sistema de Gestión de Solicitudes Académicas (SGSA). Se analizan las responsabilidades de las clases, la organización de los métodos, el manejo de datos y las dependencias, con el propósito de establecer una base para los refactorings posteriores.

## 2. Code Smells identificados

### 1. Large Class – Responsabilidades concentradas

**Ubicación:** `SistemaGestion.java`.

La clase concentra operaciones de negocio, validaciones, búsqueda, generación de reportes y persistencia JSON. Esta acumulación de responsabilidades aumenta el acoplamiento y dificulta el mantenimiento.

**Impacto:** los cambios en una responsabilidad pueden afectar otras funcionalidades.

**Prioridad:** Alta.

### 2. Long Method – Método extenso

**Ubicación:** `Main.java`, método `main()`.

El método principal contiene la presentación del menú, lectura de datos, validaciones de entrada, selección de operaciones y tratamiento de errores. Esto dificulta su comprensión y mantenimiento.

**Impacto:** dificulta localizar errores y modificar una opción sin afectar el flujo general.

**Prioridad:** Alta.

### 3. Long Parameter List – Lista extensa de parámetros

**Ubicación:** `SistemaGestion.java`, método `registrarSolicitud()`.

El método recibe seis parámetros: nombre, legajo, email, tipo, prioridad y descripción. Estos datos corresponden a información relacionada que podría agruparse en un objeto específico.

**Impacto:** dificulta la lectura de las llamadas y aumenta la posibilidad de intercambiar argumentos accidentalmente.

**Prioridad:** Media.

### 4. Duplicated Code – Código duplicado

**Ubicación:** `SistemaGestion.java`, métodos `cambiarEstado()` y `asignarResponsable()`.

Ambos métodos recorren la colección de solicitudes para localizar un registro mediante su ID. La lógica de búsqueda está repetida.

**Impacto:** si cambia el criterio de búsqueda, será necesario modificar más de un lugar.

**Prioridad:** Media.

### 5. Magic Strings – Cadenas literales

**Ubicación:** `SistemaGestion.java`.

Los estados de las solicitudes, como `NUEVA`, `EN_PROCESO`, `RESUELTA` y `RECHAZADA`, aparecen escritos directamente en distintas condiciones. También se utilizan cadenas para representar los tipos de solicitudes.

**Impacto:** aumenta el riesgo de errores tipográficos y dificulta mantener los valores de manera consistente.

**Prioridad:** Alta.

### 6. Primitive Obsession – Uso excesivo de tipos primitivos

**Ubicación:** `Solicitud.java` y `SistemaGestion.java`.

Los conceptos del dominio, como estado, tipo y prioridad, se representan mediante tipos genéricos como `String` e `int`, sin tipos específicos que restrinjan sus valores posibles.

**Impacto:** las validaciones quedan distribuidas entre distintos métodos y resulta más difícil garantizar la consistencia de los datos.

**Prioridad:** Media.

### 7. Exposed Internal State – Exposición del estado interno

**Ubicación:** `SistemaGestion.java`, método `obtenerSolicitudes()`.

El método devuelve directamente la lista interna de solicitudes, permitiendo que otras clases puedan modificarla sin pasar por las operaciones y validaciones de `SistemaGestion`.

**Impacto:** se pierde el control sobre las modificaciones de la colección y se debilita el encapsulamiento.

**Prioridad:** Alta.

### 8. Generic Exception Handling – Manejo genérico de excepciones

**Ubicación:** `SistemaGestion.java`, métodos `guardarSolicitudes()` y `cargarSolicitudes()`.

Los métodos de persistencia utilizan bloques `catch` que capturan excepciones demasiado generales. En particular, ante un error de lectura, `cargarSolicitudes()` reemplaza la colección por una lista vacía.

**Impacto:** puede ocultar la causa real de un problema y provocar que los datos recuperables queden fuera del estado de la aplicación.

**Prioridad:** Alta.

### 9. Hardcoded Configuration – Configuración fija en el código

**Ubicación:** `SistemaGestion.java`, atributo `archivo`.

La ubicación del archivo JSON está definida directamente mediante la cadena `data/solicitudes.json`.

**Impacto:** dificulta cambiar la ubicación o adaptar la persistencia a otros entornos sin modificar el código fuente.

**Prioridad:** Media.

## 3. Conclusión del diagnóstico

El análisis de la versión inicial muestra que el SGSA cumple sus funcionalidades principales, pero presenta oportunidades de mejora en la separación de responsabilidades, el encapsulamiento, la validación de datos y la persistencia.

Los problemas identificados servirán como base para planificar los refactorings, procurando preservar el comportamiento funcional existente y verificar cada modificación mediante pruebas automatizadas.