# Análisis SOLID y Clean Code – SGSA

**Materia:** Metodología de Sistemas II – UTN  
**Integrantes:** Andrés Rodríguez , Francisco López

---

## 1. Análisis de principios SOLID

### SRP – Principio de Responsabilidad Única
* **Estado Inicial:** `SistemaGestion` concentraba la administración de solicitudes, las validaciones de negocio, la generación de reportes y la persistencia JSON.
* **Solución Aplicada:** Se separó la persistencia en `JsonSolicitudRepository`, se delegó a `Solicitud` la evaluación de sus transiciones de estado (`esTransicionValida`) y se extrajeron métodos de validación auxiliares.

### OCP – Principio Abierto/Cerrado
* **Estado Inicial:** Las reglas de transición de estado estaban dispersas en estructuras condicionales en `SistemaGestion`.
* **Solución Aplicada:** Se encapsularon las reglas de transición dentro de `Solicitud.esTransicionValida()`, permitiendo modificar o extender las transiciones sin alterar la clase de gestión.

### LSP – Principio de Sustitución de Liskov
* **Estado Inicial:** No se registraban jerarquías de herencia complejas en la versión inicial.
* **Solución Aplicada:** Se mantuvo el diseño simple garantizando que las entidades del dominio respeten los contratos de comportamiento previstos.

### ISP – Principio de Segregación de Interfaces
* **Estado Inicial:** Ausencia de interfaces infladas o innecesarias.
* **Solución Aplicada:** Se conservó una arquitectura delgada enfocada en contratos claros de persistencia y dominio.

### DIP – Principio de Inversión de Dependencias
* **Estado Inicial:** `SistemaGestion` instanciaba y dependía directamente del manejo concreto de la persistencia JSON.
* **Solución Aplicada:** Se desacopló la lógica de gestión manteniendo una separación clara de responsabilidades con la capa de persistencia delegada.

---

## 2. Análisis y Criterios de Clean Code Aplicados

1. **Nombres Expresivos y Reveladores de Intención:** Métodos auxiliares descriptivos como `esTransicionValida()`, `esFinalizada()`, `validarTextoObligatorio()` y `calcularSiguienteId()`.
2. **Métodos Pequeños y Enfocados:** Se redujo la extensión de `main()` en `Main.java` y de los métodos de `SistemaGestion.java` delegando sub-tareas a métodos privados.
3. **Encapsulamiento del Estado Interno:** Se protegió la colección interna devolviendo copias inmutables mediante `List.copyOf(solicitudes)`.
4. **Manejo Específico de Excepciones:** Se sustituyó la captura genérica `catch (Exception e)` en `Main.java` por excepciones específicas como `IllegalArgumentException` e `IllegalStateException`.

---

## 3. Decisiones de Mejora Consolidadas

1. Separar formalmente la persistencia de la gestión de solicitudes.
2. Reducir la extensión de los métodos mediante **Extract Method**.
3. Reubicar responsabilidades en entidades del dominio mediante **Move Method**.
4. Agrupar los datos relacionados de registro mediante **Introduce Parameter Object** (`DatosSolicitud`).
5. Garantizar la inmutabilidad de la colección devuelta en `obtenerSolicitudes()`.
6. Verificar cada modificación a través de la suite automatizada de JUnit 5.