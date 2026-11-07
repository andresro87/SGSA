

package ar.edu.utn.sgsa;

public class DatosSolicitud {

    private final String nombre;
    private final String legajo;
    private final String email;
    private final String tipo;
    private final int prioridad;
    private final String descripcion;

    public DatosSolicitud(
            String nombre,
            String legajo,
            String email,
            String tipo,
            int prioridad,
            String descripcion
    ) {
        this.nombre = nombre;
        this.legajo = legajo;
        this.email = email;
        this.tipo = tipo;
        this.prioridad = prioridad;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getLegajo() {
        return legajo;
    }

    public String getEmail() {
        return email;
    }

    public String getTipo() {
        return tipo;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
