
package ar.edu.utn.sgsa;

public class Solicitud {

    private int id;
    private Estudiante estudiante;
    private String tipo;
    private int prioridad;
    private String descripcion;
    private String estado;
    private Responsable responsable;
    private String fechaRegistro;

    public Solicitud() {
    }

    public Solicitud(int id, Estudiante estudiante, String tipo,
                     int prioridad, String descripcion, String estado,
                     Responsable responsable, String fechaRegistro) {

        this.id = id;
        this.estudiante = estudiante;
        this.tipo = tipo;
        this.prioridad = prioridad;
        this.descripcion = descripcion;
        this.estado = estado;
        this.responsable = responsable;
        this.fechaRegistro = fechaRegistro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Responsable getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "Solicitud{" +
                "id=" + id +
                ", estudiante=" + estudiante +
                ", tipo='" + tipo + '\'' +
                ", prioridad=" + prioridad +
                ", descripcion='" + descripcion + '\'' +
                ", estado='" + estado + '\'' +
                ", responsable=" + responsable +
                ", fechaRegistro='" + fechaRegistro + '\'' +
                '}';
    }
}
