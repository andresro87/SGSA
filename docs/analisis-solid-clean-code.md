**Materia:** Metodología de Sistemas II – UTN  
**Integrantes:** Andrés Rodríguez, Francisco López

---

## 1. Análisis de principios SOLID

### SRP – Principio de Responsabilidad Única

* **Estado Inicial:** `SistemaGestion` concentraba la administración de solicitudes, las validaciones de negocio, la generación de reportes y la persistencia JSON.
* **Solución Aplicada:** Se separó la persistencia en `JsonSolicitudRepository`, se delegó a `Solicitud` la evaluación de sus transiciones de estado mediante `esTransicionValida()` y se extrajeron métodos auxiliares de validación.
* **Resultado:** Se mejoró la distribución de responsabilidades y se redujo la concentración de tareas en `SistemaGestion`.

### OCP – Principio Abierto/Cerrado

* **Estado Inicial:** Las reglas de transición de estado estaban dispersas en estructuras condicionales de `SistemaGestion`.
* **Solución Aplicada:** Se encapsularon las reglas de transición dentro de `Solicitud.esTransicionValida()`.
* **Resultado:** Se mejoró la organización de las reglas de negocio. Sin embargo, incorporar nuevas transiciones todavía puede requerir modificar el método existente, por lo que no se considera una aplicación completa del principio.

### LSP – Principio de Sustitución de Liskov

* **Estado Inicial:** El sistema no presentaba jerarquías de herencia complejas ni una estructura basada en polimorfismo.
* **Solución Aplicada:** Se mantuvo un diseño simple, sin introducir relaciones de herencia innecesarias.
* **Resultado:** No se identificaron problemas relacionados con la sustitución de subtipos. El principio no se aplica directamente a la estructura actual del sistema.

### ISP – Principio de Segregación de Interfaces

* **Estado Inicial:** El proyecto no contaba con una arquitectura de persistencia basada en interfaces. Durante la evolución se incorporó `SolicitudRepository`, que define las operaciones de carga y almacenamiento.
* **Solución Aplicada:** Se definió una interfaz con las operaciones relacionadas con la persistencia de solicitudes.
* **Resultado:** Aunque existe una abstracción, actualmente `SistemaGestion` depende directamente de `JsonSolicitudRepository` y no utiliza la interfaz. Por lo tanto, su integración queda como una mejora pendiente.

### DIP – Principio de Inversión de Dependencias

* **Estado Inicial:** `SistemaGestion` instanciaba y dependía directamente de la implementación concreta `JsonSolicitudRepository`.
* **Solución Aplicada:** Se separó la persistencia de la lógica de gestión, delegando las operaciones de almacenamiento y recuperación a `JsonSolicitudRepository`.
* **Resultado:** Se redujo la concentración de responsabilidades, pero no se implementó una inversión de dependencias completa. Como mejora futura se propone incorporar una abstracción de repositorio e inyectar su implementación desde el exterior.

---

## 2. Análisis y criterios de Clean Code aplicados

1. **Nombres expresivos y reveladores de intención:** Se utilizaron métodos auxiliares descriptivos como `esTransicionValida()`, `esFinalizada()`, `validarTextoObligatorio()` y `calcularSiguienteId()`.

2. **Métodos pequeños y enfocados:** Se redujo la extensión de `main()` en `Main.java` y de los métodos de `SistemaGestion.java`, delegando subtareas a métodos auxiliares.

3. **Protección de la colección interna:** Se utilizó `List.copyOf(solicitudes)` en `obtenerSolicitudes()` para devolver una lista que no permita agregar o eliminar elementos. Sin embargo, los objetos `Solicitud` contenidos en ella siguen siendo modificables.

4. **Manejo específico de excepciones:** Se sustituyó la captura genérica `catch (Exception e)` en `Main.java` por excepciones específicas como `IllegalArgumentException` e `IllegalStateException`, manteniendo el tratamiento correspondiente de `NumberFormatException`.

---

## 3. Decisiones de mejora consolidadas

1. Separar formalmente la persistencia de la gestión de solicitudes mediante `JsonSolicitudRepository`.
2. Reducir la extensión de los métodos mediante **Extract Method**.
3. Reubicar responsabilidades en entidades del dominio mediante **Move Method**.
4. Agrupar los datos relacionados del registro mediante **Introduce Parameter Object**, utilizando `DatosSolicitud`.
5. Proteger la colección devuelta por `obtenerSolicitudes()` mediante `List.copyOf()`.
6. Verificar las modificaciones a través de la suite automatizada de JUnit 5.
7. Documentar como mejoras futuras la encapsulación de los objetos del dominio y la incorporación de abstracciones para desacoplar la persistencia.

