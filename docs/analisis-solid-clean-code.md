# Análisis SOLID y Clean Code – SGSA

## 1. Análisis de principios SOLID

### SRP – Principio de responsabilidad única

Se identifica una oportunidad de mejora en la clase `SistemaGestion`, que concentra la administración de solicitudes, las validaciones de negocio, la generación de reportes y la persistencia JSON.

Esta concentración dificulta el mantenimiento y aumenta el impacto de los cambios. Se propone separar la responsabilidad de persistencia de la lógica de gestión.

### OCP – Principio abierto/cerrado

La lógica de estados y tipos de solicitudes utiliza cadenas literales y condiciones distribuidas en diferentes métodos. Incorporar nuevos estados o modificar las reglas puede requerir cambios en varias partes del código.

Se propone centralizar la representación de los estados y tipos mediante enumeraciones, reduciendo la dispersión de valores y condiciones.

### LSP – Principio de sustitución de Liskov

En la versión inicial no se identifican jerarquías de herencia ni clases derivadas que permitan demostrar una infracción de este principio. Por lo tanto, no se registra un incumplimiento concreto.

### ISP – Principio de segregación de interfaces

El proyecto no utiliza interfaces con múltiples responsabilidades ni implementaciones que deban depender de métodos innecesarios. No se identifica una infracción concreta en esta versión.

### DIP – Principio de inversión de dependencias

La clase `SistemaGestion` depende directamente de implementaciones concretas de persistencia, como Gson y las clases de manejo de archivos. Esto vincula la lógica de gestión con una tecnología específica.

Se propone evaluar la separación de la persistencia mediante una abstracción que permita desacoplar ambas responsabilidades.

## 2. Análisis de Clean Code

Se identifican las siguientes oportunidades de mejora:

- **Legibilidad:** `Main.main()` concentra demasiadas operaciones y dificulta seguir el flujo de ejecución.
- **Simplicidad:** algunos métodos realizan varias tareas que podrían separarse en operaciones más pequeñas.
- **Nombres expresivos:** los valores representados mediante cadenas y números primitivos no comunican completamente su significado.
- **Encapsulamiento:** `obtenerSolicitudes()` expone directamente la colección interna.
- **Manejo de errores:** los bloques de persistencia utilizan excepciones genéricas y pueden ocultar problemas.
- **Mantenibilidad:** las reglas de negocio y la persistencia están concentradas en una misma clase.

## 3. Decisiones de mejora

A partir del análisis se establece el siguiente criterio de trabajo:

1. Separar la persistencia de la gestión de solicitudes.
2. Reducir la extensión de los métodos mediante Extract Method.
3. Reubicar responsabilidades en clases más adecuadas mediante Move Method.
4. Agrupar los datos relacionados de registro mediante Introduce Parameter Object.
5. Reemplazar cadenas literales de estados y tipos por enumeraciones.
6. Mejorar el encapsulamiento de las colecciones.
7. Fortalecer el manejo de excepciones.

Los cambios se realizarán progresivamente y se verificarán mediante las pruebas automatizadas existentes, procurando conservar el comportamiento funcional del sistema.