package ar.alkemy.ejercicio;

public abstract class Usuario {
     protected  Integer DNI;
     protected Integer legajo;

    public abstract Integer getDNI() ;



    public abstract Integer getLegajo();

    public abstract void setLegajo(Integer legajo);

}
