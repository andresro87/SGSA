
package ar.edu.utn.sgsa;

public class Responsable {

    private String nombre;
    private String area;

    public Responsable() {
    }

    public Responsable(String nombre, String area) {
        this.nombre = nombre;
        this.area = area;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Responsable{" +
                "nombre='" + nombre + '\'' +
                ", area='" + area + '\'' +
                '}';
    }
}
