# Diagnóstico de Code Smells – SGSA

**Materia:** Metodología de Sistemas II – UTN  
**Integrantes:** Andrés Rodríguez, Francisco López 

---

## 1. Objetivo

El objetivo de este diagnóstico es identificar problemas de diseño y mantenimiento presentes en la versión inicial del Sistema de Gestión de Solicitudes Académicas (SGSA). Se analizan las responsabilidades de las clases, la organización de los métodos, el manejo de datos y las dependencias, con el propósito de establecer la base para los refactorings aplicados.

---

## 2. Code Smells identificados

### 1. Large Class – Responsabilidades concentradas
* **Ubicación:** `SistemaGestion.java`
* **Descripción:** La clase concentraba operaciones de negocio, validaciones, búsqueda, generación de reportes y persistencia JSON. Esta acumulación de responsabilidades aumentaba el acoplamiento y dificultaba el mantenimiento.
* **Impacto:** Los cambios en una responsabilidad podían afectar otras funcionalidades.
* **Prioridad:** Alta.

### 2. Long Method – Método extenso
* **Ubicación:** `Main.java`, método `main()`.
* **Descripción:** El método principal contenía la presentación del menú, lectura de datos, validaciones de entrada, selección de operaciones y tratamiento de errores. Esto dificultaba su comprensión.
* **Impacto:** Dificultaba localizar errores y modificar una opción sin afectar el flujo general.
* **Prioridad:** Alta.

### 3. Long Parameter List – Lista extensa de parámetros
* **Ubicación:** `SistemaGestion.java`, método `registrarSolicitud()`.
* **Descripción:** El método recibía seis parámetros independientes: nombre, legajo, email, tipo, prioridad y descripción.
* **Impacto:** Dificultaba la lectura de las llamadas y aumentaba la posibilidad de intercambiar argumentos accidentalmente.
* **Prioridad:** Media.

### 4. Duplicated Code – Código duplicado
* **Ubicación:** `SistemaGestion.java`, métodos `cambiarEstado()`, `asignarResponsable()` y validaciones de entrada.
* **Descripción:** Comprobaciones repetidas de campos obligatorios de texto y búsquedas duplicadas por ID.
* **Impacto:** Multiplicaba los puntos a modificar ante un cambio en las reglas de validación o búsqueda.
* **Prioridad:** Media.

### 5. Magic Strings – Cadenas literales
* **Ubicación:** `SistemaGestion.java` y `Solicitud.java`.
* **Descripción:** Los estados de las solicitudes (`NUEVA`, `EN_PROCESO`, `RESUELTA`, `RECHAZADA`) aparecían escritos directamente en distintas condiciones.
* **Impacto:** Aumentaba el riesgo de errores tipográficos y dificultaba mantener los valores de manera consistente.
* **Prioridad:** Alta.

### 6. Primitive Obsession – Uso excesivo de tipos primitivos
* **Ubicación:** `Solicitud.java` y `SistemaGestion.java`.
* **Descripción:** Conceptos del dominio como estado, tipo y prioridad se representaban mediante tipos genéricos (`String` e `int`) sin tipos explícitos que restrinjan sus valores.
* **Impacto:** Las validaciones quedaban distribuidas entre distintos métodos, complicando la garantía de consistencia.
* **Prioridad:** Media.

### 7. Exposed Internal State – Exposición del estado interno
* **Ubicación:** `SistemaGestion.java`, método `obtenerSolicitudes()`.
* **Descripción:** El método devolvía directamente la lista interna de solicitudes, permitiendo que otras clases la modificaran sin pasar por las validaciones del sistema.
* **Impacto:** Pérdida de control sobre las modificaciones de la colección y debilitamiento del encapsulamiento.
* **Prioridad:** Alta.

### 8. Generic Exception Handling – Manejo genérico de excepciones
* **Ubicación:** `Main.java` y `JsonSolicitudRepository.java`.
* **Descripción:** Captura de excepciones demasiado genéricas (`catch (Exception e)`) en la interacción del menú y en la persistencia.
* **Impacto:** Ocultaba errores de programación y dificultaba el diagnóstico de la causa real ante fallas.
* **Prioridad:** Alta.

### 9. Hardcoded Configuration – Configuración fija en el código
* **Ubicación:** `JsonSolicitudRepository.java`, atributo `archivo`.
* **Descripción:** La ubicación del archivo JSON estaba definida directamente mediante la cadena `data/solicitudes.json`.
* **Impacto:** Dificultaba adaptar la persistencia a otros entornos de ejecución o pruebas unitarias aisladas.
* **Prioridad:** Media.

---

## 3. Conclusión del diagnóstico

El diagnóstico permitió establecer una hoja de ruta para mejorar la calidad interna del sistema. A partir de los problemas identificados se aplicaron distintos refactorings, entre ellos **Move Method**, **Extract Method** e **Introduce Parameter Object**, además de mejoras en la protección de la colección de solicitudes mediante `List.copyOf()`.

Como resultado, algunos Code Smells fueron resueltos, mientras que otros se mitigaron parcialmente o permanecen como oportunidades de mejora. Entre los problemas pendientes se encuentran el uso de cadenas literales para representar los estados, la utilización de tipos primitivos para conceptos del dominio y la configuración fija de la ubicación del archivo JSON.

Esta distinción permite reflejar con mayor precisión la evolución del proyecto, evitando afirmar que todos los problemas fueron eliminados. Los aspectos pendientes quedan documentados para futuras mejoras, manteniendo como objetivo la calidad, la mantenibilidad y la preservación del comportamiento funcional del sistema.