package ar.alkemy.ejercicio;

import java.util.Date;
import java.util.Objects;

public abstract class Materia {

    protected String nombre;
    protected Integer codigo;
    protected Integer cupoMaximo;
    protected Date horario;
    public abstract Integer getCupoMaximo();
    public abstract String getNombre();

    public abstract void setNombre(String nombre);

    public abstract Integer getCodigo();

    public abstract void setCodigo(Integer codigo);
    public abstract Boolean agregarAlumnoAMateria(Alumno alumno);
    public abstract Boolean agregarProfesor(Profesor profesor);
    public abstract Integer horario();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Materia)) return false;
        Materia materia = (Materia) o;
        return codigo.equals(materia.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

}
