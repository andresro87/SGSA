
package ar.edu.utn.sgsa;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaGestion sistema = new SistemaGestion();

        int opcion = 0;

        mostrarEncabezado();

        while (opcion != 7) {
            mostrarMenu();

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1 -> registrarSolicitud(scanner, sistema);
                    case 2 -> cambiarEstado(scanner, sistema);
                    case 3 -> asignarResponsable(scanner, sistema);
                    case 4 -> buscarSolicitudes(scanner, sistema);
                    case 5 -> System.out.println(sistema.generarReporte());
                    case 6 -> listarSolicitudes(sistema);
                    case 7 -> System.out.println(
                            "\nGracias por utilizar el SGSA."
                    );
                    default -> System.out.println(
                            "Opcion incorrecta. Intente nuevamente."
                    );
                }

            } catch (NumberFormatException e) {
                System.out.println(
                        "Error: debe ingresar un numero valido."
                );
            } catch (Exception e) {
                System.out.println(
                        "Error inesperado: " + e.getMessage()
                );
            }
        }

        scanner.close();
    }

    private static void mostrarEncabezado() {
        System.out.println("========================================");
        System.out.println(" SISTEMA DE GESTION DE SOLICITUDES");
        System.out.println("              ACADEMICAS");
        System.out.println("========================================");
    }

    private static void mostrarMenu() {
        System.out.println("\n--------------- MENU ----------------");
        System.out.println("1. Registrar solicitud");
        System.out.println("2. Cambiar estado");
        System.out.println("3. Asignar responsable");
        System.out.println("4. Buscar y filtrar solicitudes");
        System.out.println("5. Generar reporte");
        System.out.println("6. Listar todas las solicitudes");
        System.out.println("7. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    private static void registrarSolicitud(
            Scanner scanner,
            SistemaGestion sistema
    ) {
        System.out.println("\n--- REGISTRAR SOLICITUD ---");

        System.out.print("Nombre del estudiante: ");
        String nombre = scanner.nextLine();

        System.out.print("Legajo: ");
        String legajo = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.println("\nTipos de solicitud:");
        System.out.println("CERTIFICADO");
        System.out.println("EQUIVALENCIA");
        System.out.println("REINCORPORACION");
        System.out.println("BAJA");

        System.out.print("Tipo de solicitud: ");
        String tipo = scanner.nextLine();

        System.out.print("Prioridad (1-Alta, 2-Media, 3-Baja): ");
        int prioridad = Integer.parseInt(scanner.nextLine());

        System.out.print("Descripcion: ");
        String descripcion = scanner.nextLine();

        DatosSolicitud datos = new DatosSolicitud(
                nombre,
                legajo,
                email,
                tipo,
                prioridad,
                descripcion
        );

        sistema.registrarSolicitud(datos);
    }

    private static void cambiarEstado(
            Scanner scanner,
            SistemaGestion sistema
    ) {
        System.out.println("\n--- CAMBIAR ESTADO ---");

        System.out.print("Ingrese el ID de la solicitud: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.println("Estados disponibles:");
        System.out.println("EN_PROCESO");
        System.out.println("RESUELTA");
        System.out.println("RECHAZADA");

        System.out.print("Nuevo estado: ");
        String nuevoEstado = scanner.nextLine().toUpperCase();

        sistema.cambiarEstado(id, nuevoEstado);
    }

    private static void asignarResponsable(
            Scanner scanner,
            SistemaGestion sistema
    ) {
        System.out.println("\n--- ASIGNAR RESPONSABLE ---");

        System.out.print("Ingrese el ID de la solicitud: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Nombre del responsable: ");
        String nombreResponsable = scanner.nextLine();

        System.out.print("Area responsable: ");
        String area = scanner.nextLine();

        sistema.asignarResponsable(
                id,
                nombreResponsable,
                area
        );
    }

    private static void buscarSolicitudes(
            Scanner scanner,
            SistemaGestion sistema
    ) {
        System.out.println("\n--- BUSCAR SOLICITUDES ---");

        System.out.print("Estado (Enter para omitir): ");
        String estado = scanner.nextLine();

        System.out.print("Tipo (Enter para omitir): ");
        String tipo = scanner.nextLine();

        System.out.print("Prioridad (0 para omitir): ");
        String prioridadTexto = scanner.nextLine();

        Integer prioridad = null;

        if (!prioridadTexto.trim().isEmpty()
                && !prioridadTexto.equals("0")) {
            prioridad = Integer.parseInt(prioridadTexto);
        }

        List<Solicitud> resultados = sistema.buscarSolicitudes(
                estado.trim().isEmpty() ? null : estado,
                tipo.trim().isEmpty() ? null : tipo,
                prioridad
        );

        if (resultados.isEmpty()) {
            System.out.println("No se encontraron solicitudes.");
        } else {
            for (Solicitud solicitud : resultados) {
                System.out.println(solicitud);
            }
        }
    }

    private static void listarSolicitudes(SistemaGestion sistema) {
        System.out.println("\n--- LISTADO DE SOLICITUDES ---");

        List<Solicitud> solicitudes = sistema.obtenerSolicitudes();

        if (solicitudes.isEmpty()) {
            System.out.println("No hay solicitudes registradas.");
        } else {
            for (Solicitud solicitud : solicitudes) {
                System.out.println(solicitud);
            }
        }
    }
}
