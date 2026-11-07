
package ar.edu.utn.sgsa;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        SistemaGestion sistema = new SistemaGestion();

        int opcion = 0;

        System.out.println("========================================");
        System.out.println(" SISTEMA DE GESTION DE SOLICITUDES");
        System.out.println("              ACADEMICAS");
        System.out.println("========================================");

        while (opcion != 7) {

            System.out.println("\n--------------- MENU ----------------");
            System.out.println("1. Registrar solicitud");
            System.out.println("2. Cambiar estado");
            System.out.println("3. Asignar responsable");
            System.out.println("4. Buscar y filtrar solicitudes");
            System.out.println("5. Generar reporte");
            System.out.println("6. Listar todas las solicitudes");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opcion: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {

                    case 1:
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

                        sistema.registrarSolicitud(
                                nombre,
                                legajo,
                                email,
                                tipo,
                                prioridad,
                                descripcion
                        );
                        break;

                    case 2:
                        System.out.println("\n--- CAMBIAR ESTADO ---");

                        System.out.print("Ingrese el ID de la solicitud: ");
                        int idEstado = Integer.parseInt(scanner.nextLine());

                        System.out.println("Estados disponibles:");
                        System.out.println("EN_PROCESO");
                        System.out.println("RESUELTA");
                        System.out.println("RECHAZADA");

                        System.out.print("Nuevo estado: ");
                        String nuevoEstado = scanner.nextLine().toUpperCase();

                        sistema.cambiarEstado(idEstado, nuevoEstado);
                        break;

                    case 3:
                        System.out.println("\n--- ASIGNAR RESPONSABLE ---");

                        System.out.print("Ingrese el ID de la solicitud: ");
                        int idResponsable = Integer.parseInt(scanner.nextLine());

                        System.out.print("Nombre del responsable: ");
                        String nombreResponsable = scanner.nextLine();

                        System.out.print("Area responsable: ");
                        String area = scanner.nextLine();

                        sistema.asignarResponsable(
                                idResponsable,
                                nombreResponsable,
                                area
                        );
                        break;

                    case 4:
                        System.out.println("\n--- BUSCAR SOLICITUDES ---");

                        System.out.print("Estado (Enter para omitir): ");
                        String estado = scanner.nextLine();

                        System.out.print("Tipo (Enter para omitir): ");
                        String tipoBusqueda = scanner.nextLine();

                        System.out.print("Prioridad (0 para omitir): ");
                        String prioridadTexto = scanner.nextLine();

                        Integer prioridadBusqueda = null;

                        if (!prioridadTexto.trim().isEmpty()
                                && !prioridadTexto.equals("0")) {
                            prioridadBusqueda = Integer.parseInt(prioridadTexto);
                        }

                        List<Solicitud> resultados = sistema.buscarSolicitudes(
                                estado.trim().isEmpty() ? null : estado,
                                tipoBusqueda.trim().isEmpty() ? null : tipoBusqueda,
                                prioridadBusqueda
                        );

                        if (resultados.isEmpty()) {
                            System.out.println("No se encontraron solicitudes.");
                        } else {
                            for (Solicitud solicitud : resultados) {
                                System.out.println(solicitud);
                            }
                        }
                        break;

                    case 5:
                        System.out.println(sistema.generarReporte());
                        break;

                    case 6:
                        System.out.println("\n--- LISTADO DE SOLICITUDES ---");

                        List<Solicitud> todas = sistema.obtenerSolicitudes();

                        if (todas.isEmpty()) {
                            System.out.println("No hay solicitudes registradas.");
                        } else {
                            for (Solicitud solicitud : todas) {
                                System.out.println(solicitud);
                            }
                        }
                        break;

                    case 7:
                        System.out.println("\nGracias por utilizar el SGSA.");
                        break;

                    default:
                        System.out.println("Opcion incorrecta. Intente nuevamente.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un numero valido.");
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
