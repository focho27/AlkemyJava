package ar.alkemy.ejercicio;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class BaseDeDatos extends Materia implements Comparable<Materia>{
    private Set<Alumno> alumnosInscriptos;
    private Set<Profesor> profesoresInscriptos;
    public BaseDeDatos(Integer codigo, String nombre,Integer cantidadMaximaDeAlumnos,Date horario) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.alumnosInscriptos = new HashSet<>();
        this.cupoMaximo=cantidadMaximaDeAlumnos;
        this.horario = horario;
        this.profesoresInscriptos = new HashSet<>();
    }

    public Set<Alumno> getAlumnosInscriptos() {
        return this.alumnosInscriptos;
    }

    public void setAlumnosInscriptos(Set<Alumno> alumnosInscriptos) {
        this.alumnosInscriptos = alumnosInscriptos;
    }

    @Override
    public Integer getCupoMaximo() {
        return this.cupoMaximo;
    }
    @Override
    public String getNombre() {
        return this.nombre;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public Integer getCodigo() {
        return codigo;
    }

    @Override
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    @Override
    public Boolean agregarAlumnoAMateria(Alumno alumno) {
        if(alumno!=null){
            return  this.alumnosInscriptos.add(alumno);
        }
        return false;
    }

    @Override
    public Boolean agregarProfesor(Profesor profesor) {
        if(profesor!=null)
            return this.profesoresInscriptos.add(profesor);
        return false;
    }

    @Override
    public Integer horario() {
        return this.horario.getHours();
    }


    @Override
    public int compareTo(Materia o) {
        if(nombre.equals(o.nombre))
            return codigo.compareTo(o.getCodigo());
        return this.nombre.compareTo(o.getNombre());
    }
}
