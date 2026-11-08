package ar.edu.utn.sgsa;

import java.util.List;

public interface SolicitudRepository {
    List<Solicitud> cargarSolicitudes();
    void guardarSolicitudes(List<Solicitud> solicitudes);
}