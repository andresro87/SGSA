
package ar.edu.utn.sgsa;

public class Estudiante {

    private String nombre;
    private String legajo;
    private String email;

    public Estudiante() {
    }

    public Estudiante(String nombre, String legajo, String email) {
        this.nombre = nombre;
        this.legajo = legajo;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "nombre='" + nombre + '\'' +
                ", legajo='" + legajo + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
