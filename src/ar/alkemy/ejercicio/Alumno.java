package ar.alkemy.ejercicio;

public class Alumno extends Usuario {
    private String nombre;
    public Alumno(Integer dni, Integer legajo,String nombre){

        this.DNI = dni;
        this.legajo = legajo;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public Integer getDNI() {
        return DNI;
    }



    @Override
    public Integer getLegajo() {
        return legajo;
    }
    @Override
    public void setLegajo(Integer legajo) {
        this.legajo = legajo;
    }
}
