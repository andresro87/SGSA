


package ar.edu.utn.sgsa;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SistemaGestion {

    private final List<Solicitud> solicitudes;
    private final JsonSolicitudRepository repositorio;
    private int siguienteId;

    public SistemaGestion() {
        repositorio = new JsonSolicitudRepository();
        solicitudes = new ArrayList<>(repositorio.cargar());

        siguienteId = 1;

        for (Solicitud solicitud : solicitudes) {
            if (solicitud.getId() >= siguienteId) {
                siguienteId = solicitud.getId() + 1;
            }
        }
    }

    public Solicitud registrarSolicitud(DatosSolicitud datos) {

        if (datos == null) {
            throw new IllegalArgumentException(
                    "Los datos de la solicitud son obligatorios."
            );
        }

        String nombre = datos.getNombre();
        String legajo = datos.getLegajo();
        String email = datos.getEmail();
        String tipo = datos.getTipo();
        int prioridad = datos.getPrioridad();
        String descripcion = datos.getDescripcion();

        if (nombre == null || nombre.isBlank()) {
            System.out.println("Error: el nombre es obligatorio.");
            return null;
        }

        if (legajo == null || legajo.isBlank()) {
            throw new IllegalArgumentException("El legajo es obligatorio.");
        }

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("El email es obligatorio.");
        }

        if (tipo == null || tipo.isBlank()) {
            throw new IllegalArgumentException("El tipo es obligatorio.");
        }

        if (prioridad < 1 || prioridad > 5) {
            throw new IllegalArgumentException(
                    "La prioridad debe estar entre 1 y 5."
            );
        }

        if (descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException(
                    "La descripción es obligatoria."
            );
        }

        Estudiante estudiante = new Estudiante(nombre, legajo, email);

        Solicitud solicitud = new Solicitud(
                siguienteId,
                estudiante,
                tipo,
                prioridad,
                descripcion,
                "NUEVA",
                null,
                java.time.LocalDateTime.now().toString()
        );

        solicitudes.add(solicitud);
        siguienteId++;

        repositorio.guardar(solicitudes);

        return solicitud;
    }

    public void cambiarEstado(int id, String nuevoEstado) {
        Solicitud solicitud = buscarPorId(id);

        if (nuevoEstado == null || nuevoEstado.isBlank()) {
            throw new IllegalArgumentException(
                    "El nuevo estado es obligatorio."
            );
        }

        String estadoActual = solicitud.getEstado();

        boolean transicionValida =
                (estadoActual.equals("NUEVA")
                        && nuevoEstado.equals("EN_PROCESO"))
                        ||
                        (estadoActual.equals("EN_PROCESO")
                                && (nuevoEstado.equals("RESUELTA")
                                || nuevoEstado.equals("RECHAZADA")));

        if (!transicionValida) {
            System.out.println(
                    "No se permite cambiar de "
                            + estadoActual + " a " + nuevoEstado + "."
            );
            return;
        }

        solicitud.setEstado(nuevoEstado);
        repositorio.guardar(solicitudes);
    }

    public void asignarResponsable(
            int id,
            String nombreResponsable,
            String area
    ) {
        Solicitud solicitud = buscarPorId(id);

        if (solicitud.getEstado().equals("RESUELTA")
                || solicitud.getEstado().equals("RECHAZADA")) {
            throw new IllegalStateException(
                    "No se puede asignar un responsable "
                            + "a una solicitud finalizada."
            );
        }

        if (nombreResponsable == null || nombreResponsable.isBlank()) {
            throw new IllegalArgumentException(
                    "El nombre del responsable es obligatorio."
            );
        }

        if (area == null || area.isBlank()) {
            throw new IllegalArgumentException(
                    "El área del responsable es obligatoria."
            );
        }

        Responsable responsable = new Responsable(
                nombreResponsable,
                area
        );

        solicitud.setResponsable(responsable);
        repositorio.guardar(solicitudes);
    }

    public List<Solicitud> buscarSolicitudes(
            String estado,
            String tipo,
            Integer prioridad
    ) {
        List<Solicitud> resultado = new ArrayList<>();

        for (Solicitud solicitud : solicitudes) {
            boolean coincideEstado =
                    estado == null
                            || estado.isBlank()
                            || solicitud.getEstado().equals(estado);

            boolean coincideTipo =
                    tipo == null
                            || tipo.isBlank()
                            || solicitud.getTipo().equals(tipo);

            boolean coincidePrioridad =
                    prioridad == null
                            || solicitud.getPrioridad() == prioridad;

            if (coincideEstado && coincideTipo && coincidePrioridad) {
                resultado.add(solicitud);
            }
        }

        return resultado;
    }

    public String generarReporte() {
        int nuevas = 0;
        int enProceso = 0;
        int resueltas = 0;
        int rechazadas = 0;

        for (Solicitud solicitud : solicitudes) {
            switch (solicitud.getEstado()) {
                case "NUEVA" -> nuevas++;
                case "EN_PROCESO" -> enProceso++;
                case "RESUELTA" -> resueltas++;
                case "RECHAZADA" -> rechazadas++;
                default -> {
                    // No se contabilizan estados desconocidos.
                }
            }
        }

        List<Solicitud> ordenadas = new ArrayList<>(solicitudes);

        ordenadas.sort(
                Comparator.comparingInt(Solicitud::getPrioridad)
        );

        StringBuilder reporte = new StringBuilder();

        reporte.append("\n========== REPORTE DE SOLICITUDES ==========\n");
        reporte.append("Total de solicitudes: ")
                .append(solicitudes.size())
                .append("\n");
        reporte.append("Nuevas: ").append(nuevas).append("\n");
        reporte.append("En proceso: ").append(enProceso).append("\n");
        reporte.append("Resueltas: ").append(resueltas).append("\n");
        reporte.append("Rechazadas: ").append(rechazadas).append("\n");

        reporte.append("\n--- Solicitudes ordenadas por prioridad ---\n");

        for (Solicitud solicitud : ordenadas) {
            reporte.append(solicitud).append("\n");
        }

        return reporte.toString();
    }

    public List<Solicitud> obtenerSolicitudes() {
        return List.copyOf(solicitudes);
    }

    private Solicitud buscarPorId(int id) {
        return solicitudes.stream()
                .filter(solicitud -> solicitud.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "No se encontró una solicitud con ID " + id + "."
                ));
    }
}
