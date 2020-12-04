package ar.alkemy.ejercicio;

public class Administrador extends Usuario{
    private String nombre;
    public Administrador(Integer dni,Integer legajo,String nombre){
        this.DNI = dni;
        this.legajo = legajo;
        this.nombre=nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public Integer getDNI() {
        return this.DNI;
    }

    @Override
    public Integer getLegajo() {
        return this.legajo;
    }

    @Override
    public void setLegajo(Integer legajo) {

    }
}
