package ar.edu.utn.sgsa;

public enum EstadoSolicitud {
    PENDIENTE,
    EN_REVISION,
    RESUELTA,
    RECHAZADA;

    public static EstadoSolicitud desdeTexto(String texto) {
        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException("El estado no puede estar vacio.");
        }
        try {
            return EstadoSolicitud.valueOf(texto.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Estado invalido: " + texto);
        }
    }
}