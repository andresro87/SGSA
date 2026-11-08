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
        siguienteId = calcularSiguienteId();
    }

    public Solicitud registrarSolicitud(DatosSolicitud datos) {
        if (!validarDatosSolicitud(datos)) {
            return null;
        }

        Estudiante estudiante = new Estudiante(datos.getNombre(), datos.getLegajo(), datos.getEmail());

        Solicitud solicitud = new Solicitud(
                siguienteId,
                estudiante,
                datos.getTipo(),
                datos.getPrioridad(),
                datos.getDescripcion(),
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
        validarTextoObligatorio(nuevoEstado, "El nuevo estado es obligatorio.");

        Solicitud solicitud = buscarPorId(id);
        String estadoActual = solicitud.getEstado();

        // Refactoring: delega la regla de transicion a la entidad Solicitud
        if (!solicitud.esTransicionValida(nuevoEstado)) {
            System.out.println("No se permite cambiar de " + estadoActual + " a " + nuevoEstado + ".");
            return;
        }

        solicitud.setEstado(nuevoEstado);
        repositorio.guardar(solicitudes);
    }

    public void asignarResponsable(int id, String nombreResponsable, String area) {
        Solicitud solicitud = buscarPorId(id);

        if (solicitud.esFinalizada()) {
            throw new IllegalStateException("No se puede asignar un responsable a una solicitud finalizada.");
        }

        validarTextoObligatorio(nombreResponsable, "El nombre del responsable es obligatorio.");
        validarTextoObligatorio(area, "El área del responsable es obligatoria.");

        Responsable responsable = new Responsable(nombreResponsable, area);
        solicitud.setResponsable(responsable);
        repositorio.guardar(solicitudes);
    }

    public List<Solicitud> buscarSolicitudes(String estado, String tipo, Integer prioridad) {
        List<Solicitud> resultado = new ArrayList<>();

        for (Solicitud solicitud : solicitudes) {
            boolean coincideEstado = estado == null || estado.isBlank() || solicitud.getEstado().equals(estado);
            boolean coincideTipo = tipo == null || tipo.isBlank() || solicitud.getTipo().equals(tipo);
            boolean coincidePrioridad = prioridad == null || solicitud.getPrioridad() == prioridad;

            if (coincideEstado && coincideTipo && coincidePrioridad) {
                resultado.add(solicitud);
            }
        }

        return resultado;
    }

    public String generarReporte() {
        int nuevas = contarPorEstado("NUEVA");
        int enProceso = contarPorEstado("EN_PROCESO");
        int resueltas = contarPorEstado("RESUELTA");
        int rechazadas = contarPorEstado("RECHAZADA");

        List<Solicitud> ordenadas = new ArrayList<>(solicitudes);
        ordenadas.sort(Comparator.comparingInt(Solicitud::getPrioridad));

        StringBuilder reporte = new StringBuilder();
        reporte.append("\n========== REPORTE DE SOLICITUDES ==========\n");
        reporte.append("Total de solicitudes: ").append(solicitudes.size()).append("\n");
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

    // --- Metodos privados extraidos (Extract Method) ---

    private int calcularSiguienteId() {
        int max = 0;
        for (Solicitud s : solicitudes) {
            if (s.getId() >= max) {
                max = s.getId();
            }
        }
        return max + 1;
    }

    private Solicitud buscarPorId(int id) {
        return solicitudes.stream()
                .filter(solicitud -> solicitud.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No se encontró una solicitud con ID " + id + "."));
    }

    private int contarPorEstado(String estado) {
        int contador = 0;
        for (Solicitud s : solicitudes) {
            if (s.getEstado().equals(estado)) {
                contador++;
            }
        }
        return contador;
    }

    private void validarTextoObligatorio(String valor, String mensajeError) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(mensajeError);
        }
    }

    private boolean validarDatosSolicitud(DatosSolicitud datos) {
        if (datos == null) {
            throw new IllegalArgumentException("Los datos de la solicitud son obligatorios.");
        }
        if (datos.getNombre() == null || datos.getNombre().isBlank()) {
            System.out.println("Error: el nombre es obligatorio.");
            return false;
        }
        validarTextoObligatorio(datos.getLegajo(), "El legajo es obligatorio.");
        validarTextoObligatorio(datos.getEmail(), "El email es obligatorio.");
        validarTextoObligatorio(datos.getTipo(), "El tipo es obligatorio.");

        if (datos.getPrioridad() < 1 || datos.getPrioridad() > 5) {
            throw new IllegalArgumentException("La prioridad debe estar entre 1 y 5.");
        }
        validarTextoObligatorio(datos.getDescripcion(), "La descripción es obligatoria.");

        return true;
    }
}