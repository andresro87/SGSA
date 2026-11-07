
package ar.edu.utn.sgsa;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SistemaGestion {

    private List<Solicitud> solicitudes;
    private final Gson gson;
    private final Path archivo;
    private int siguienteId;

    public SistemaGestion() {
        solicitudes = new ArrayList<>();
        gson = new GsonBuilder().setPrettyPrinting().create();

        archivo = Path.of(
                System.getProperty("user.dir"),
                "data",
                "solicitudes.json"
        );

        siguienteId = 1;
        cargarSolicitudes();
    }

    // REGISTRAR SOLICITUD

    public void registrarSolicitud(String nombre, String legajo,
                                   String email, String tipo,
                                   int prioridad, String descripcion) {

        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("Error: el nombre es obligatorio.");
            return;
        }

        if (legajo == null || legajo.trim().isEmpty()) {
            System.out.println("Error: el legajo es obligatorio.");
            return;
        }

        if (email == null || !email.contains("@")) {
            System.out.println("Error: el email no es válido.");
            return;
        }

        if (tipo == null || tipo.trim().isEmpty()) {
            System.out.println("Error: el tipo de solicitud es obligatorio.");
            return;
        }

        if (prioridad < 1 || prioridad > 3) {
            System.out.println("Error: la prioridad debe ser 1, 2 o 3.");
            return;
        }

        if (descripcion == null || descripcion.trim().isEmpty()) {
            System.out.println("Error: la descripción es obligatoria.");
            return;
        }

        Estudiante estudiante = new Estudiante(nombre, legajo, email);

        Solicitud solicitud = new Solicitud(
                siguienteId,
                estudiante,
                tipo.toUpperCase(),
                prioridad,
                descripcion,
                "NUEVA",
                null,
                LocalDate.now().toString()
        );

        solicitudes.add(solicitud);
        siguienteId++;

        guardarSolicitudes();

        System.out.println(
                "Solicitud registrada correctamente. ID: "
                        + solicitud.getId()
        );
    }

    // CAMBIAR ESTADO

    public void cambiarEstado(int id, String nuevoEstado) {

        Solicitud solicitudEncontrada = null;

        for (Solicitud solicitud : solicitudes) {
            if (solicitud.getId() == id) {
                solicitudEncontrada = solicitud;
                break;
            }
        }

        if (solicitudEncontrada == null) {
            System.out.println("Error: no existe una solicitud con ese ID.");
            return;
        }

        String estadoActual = solicitudEncontrada.getEstado();

        if (estadoActual.equals("RESUELTA")
                || estadoActual.equals("RECHAZADA")) {
            System.out.println(
                    "Error: la solicitud ya se encuentra en un estado final."
            );
            return;
        }

        if (estadoActual.equals("NUEVA")
                && !nuevoEstado.equals("EN_PROCESO")) {
            System.out.println(
                    "Error: una solicitud NUEVA solo puede pasar a EN_PROCESO."
            );
            return;
        }

        if (estadoActual.equals("EN_PROCESO")
                && !nuevoEstado.equals("RESUELTA")
                && !nuevoEstado.equals("RECHAZADA")) {
            System.out.println(
                    "Error: una solicitud EN_PROCESO debe pasar a RESUELTA o RECHAZADA."
            );
            return;
        }

        solicitudEncontrada.setEstado(nuevoEstado);
        guardarSolicitudes();

        System.out.println("Estado actualizado correctamente.");
    }

    // ASIGNAR RESPONSABLE

    public void asignarResponsable(int id, String nombreResponsable,
                                   String area) {

        for (Solicitud solicitud : solicitudes) {

            if (solicitud.getId() == id) {

                if (solicitud.getEstado().equals("RESUELTA")
                        || solicitud.getEstado().equals("RECHAZADA")) {
                    System.out.println(
                            "Error: no se puede asignar un responsable a una solicitud finalizada."
                    );
                    return;
                }

                if (nombreResponsable == null
                        || nombreResponsable.trim().isEmpty()
                        || area == null
                        || area.trim().isEmpty()) {
                    System.out.println(
                            "Error: debe ingresar el nombre y el área del responsable."
                    );
                    return;
                }

                Responsable responsable =
                        new Responsable(nombreResponsable, area);

                solicitud.setResponsable(responsable);

                guardarSolicitudes();

                System.out.println("Responsable asignado correctamente.");
                return;
            }
        }

        System.out.println("Error: no se encontró la solicitud.");
    }

    // BUSCAR Y FILTRAR SOLICITUDES

    public List<Solicitud> buscarSolicitudes(String estado, String tipo,
                                             Integer prioridad) {

        List<Solicitud> resultado = new ArrayList<>();

        for (Solicitud solicitud : solicitudes) {

            boolean coincideEstado = estado == null
                    || solicitud.getEstado().equalsIgnoreCase(estado);

            boolean coincideTipo = tipo == null
                    || solicitud.getTipo().equalsIgnoreCase(tipo);

            boolean coincidePrioridad = prioridad == null
                    || solicitud.getPrioridad() == prioridad;

            if (coincideEstado && coincideTipo && coincidePrioridad) {
                resultado.add(solicitud);
            }
        }

        return resultado;
    }

    // GENERAR REPORTE

    public String generarReporte() {

        StringBuilder reporte = new StringBuilder();

        int nuevas = 0;
        int enProceso = 0;
        int resueltas = 0;
        int rechazadas = 0;

        for (Solicitud solicitud : solicitudes) {

            if (solicitud.getEstado().equals("NUEVA")) {
                nuevas++;
            } else if (solicitud.getEstado().equals("EN_PROCESO")) {
                enProceso++;
            } else if (solicitud.getEstado().equals("RESUELTA")) {
                resueltas++;
            } else if (solicitud.getEstado().equals("RECHAZADA")) {
                rechazadas++;
            }
        }

        reporte.append("\n========== REPORTE DE SOLICITUDES ==========\n");
        reporte.append("Total de solicitudes: ")
                .append(solicitudes.size()).append("\n");
        reporte.append("Nuevas: ").append(nuevas).append("\n");
        reporte.append("En proceso: ").append(enProceso).append("\n");
        reporte.append("Resueltas: ").append(resueltas).append("\n");
        reporte.append("Rechazadas: ").append(rechazadas).append("\n");

        reporte.append("\nDETALLE ORDENADO POR PRIORIDAD:\n");

        List<Solicitud> ordenadas = new ArrayList<>(solicitudes);
        ordenadas.sort(
                Comparator.comparingInt(Solicitud::getPrioridad)
        );

        for (Solicitud solicitud : ordenadas) {
            reporte.append("ID: ").append(solicitud.getId())
                    .append(" | Estudiante: ")
                    .append(solicitud.getEstudiante().getNombre())
                    .append(" | Tipo: ").append(solicitud.getTipo())
                    .append(" | Prioridad: ")
                    .append(solicitud.getPrioridad())
                    .append(" | Estado: ")
                    .append(solicitud.getEstado())
                    .append("\n");
        }

        reporte.append("============================================\n");

        return reporte.toString();
    }

    // PERSISTENCIA JSON

    private void guardarSolicitudes() {

        try {
            Files.createDirectories(archivo.getParent());

            try (FileWriter writer = new FileWriter(
                    archivo.toFile(),
                    StandardCharsets.UTF_8
            )) {
                gson.toJson(solicitudes, writer);
            }

        } catch (IOException e) {
            throw new IllegalStateException(
                    "No se pudieron guardar las solicitudes.",
                    e
            );
        }
    }

    private void cargarSolicitudes() {

        if (!Files.exists(archivo)) {
            solicitudes = new ArrayList<>();
            guardarSolicitudes();
            return;
        }

        try (FileReader reader = new FileReader(
                archivo.toFile(),
                StandardCharsets.UTF_8
        )) {

            Type tipoLista =
                    new TypeToken<ArrayList<Solicitud>>() {}.getType();

            List<Solicitud> datos = gson.fromJson(reader, tipoLista);

            solicitudes = datos != null
                    ? datos
                    : new ArrayList<>();

            for (Solicitud solicitud : solicitudes) {
                if (solicitud.getId() >= siguienteId) {
                    siguienteId = solicitud.getId() + 1;
                }
            }

        } catch (IOException | JsonParseException e) {
            throw new IllegalStateException(
                    "No se pudieron recuperar las solicitudes. "
                            + "El archivo JSON no será sobrescrito.",
                    e
            );
        }
    }

    // CONSULTAR TODAS LAS SOLICITUDES

    public List<Solicitud> obtenerSolicitudes() {
        return solicitudes;
    }
}
