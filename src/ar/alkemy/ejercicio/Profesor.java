package ar.alkemy.ejercicio;

public class Profesor extends Usuario {
    private String nombre;
    private String apellido;
    private final Integer DNI;
    private Boolean Activo;
    public Profesor(Integer DNI,String nombre, String apellido){
        this.nombre=nombre;
        this.DNI = DNI;
        this.apellido = apellido;
        this.Activo = false;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Boolean getActivo() {
        return Activo;
    }

    public void setActivo(Boolean activo) {
        Activo = activo;
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
        this.legajo =legajo;
    }
}
