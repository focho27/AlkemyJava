package ar.alkemy.ejercicio;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Aplicacion {
    private Set<Usuario> usuariosLogueados;
    private String nombre;
    private Set<Materia> materiasAInscribirse;
    private Set<Usuario> usuariosRegistrados;

    public Aplicacion(String nombre) {
        this.nombre = nombre;
        this.materiasAInscribirse = new HashSet<>();
        this.usuariosRegistrados = new HashSet<>();
        this.usuariosLogueados = new HashSet<>();
    }

    public Boolean registrarUsuario(Usuario usuario) {

        if (usuario != null && buscarUsuarioRegistrado(usuario.getDNI())==null) {
            if (usuario instanceof Profesor) {
                if (((Profesor) usuario).getActivo()) {
                    return usuariosRegistrados.add(usuario);
                }
            } else {
                return usuariosRegistrados.add(usuario);
            }
        }
        return false;
    }

    public Usuario buscarUsuarioRegistrado(Integer DNI) {
        if(DNI!=null){
        for (Usuario usuario :
                usuariosRegistrados) {
            if(usuario instanceof Administrador || usuario instanceof Alumno || usuario instanceof Profesor)
            if ((usuario).getDNI().equals(DNI)) {
                return usuario;
            }
        }}
        return null;
    }

    public Boolean login(Usuario usuario) {
        if(usuario!=null){
        if(usuario instanceof Administrador || usuario instanceof Alumno || (usuario instanceof Profesor && ((Profesor) usuario).getActivo()))
        if (buscarUsuarioRegistrado((usuario).getDNI()) != null) {
            return usuariosLogueados.add(usuario);
        }}
        return false;
    }

    public Boolean buscarLogueados(Integer DNI) {
        if(DNI!=null){
        for (Usuario usuario :
                usuariosLogueados) {
            if (DNI != null) {
                if (usuario.getDNI().equals(DNI)) {
                    return true;
                }
            }
        }}
        return false;
    }

    public Boolean agregarMateria(Usuario usuario, Materia materia) {
        if(usuario!=null && materia!=null){
        if (usuario instanceof Administrador) {
            if (materia != null && usuario != null && buscarMateriaDisponible(materia.getCodigo())==null) {
                return this.materiasAInscribirse.add(materia);
            }
        }}
        return false;
    }



    public Materia buscarMateriaDisponible(Integer codigoMateria) {
        if (codigoMateria != null)
            for (Materia materia :
                    materiasAInscribirse) {
                if (materia instanceof BaseDeDatos) {
                    if (materia.getCodigo().equals(codigoMateria) && ((BaseDeDatos) materia).getAlumnosInscriptos().size() < ((BaseDeDatos) materia).getCupoMaximo()) {
                        return materia;
                    }
                }
                if (materia instanceof Fisica) {
                    if (materia.getCodigo().equals(codigoMateria) && ((Fisica) materia).getAlumnosInscriptos().size() < ((Fisica) materia).getCupoMaximo()) {
                        return materia;
                    }
                }
                if (materia instanceof Matematica) {
                    if (materia.getCodigo().equals(codigoMateria) && ((Matematica) materia).getAlumnosInscriptos().size() < ((Matematica) materia).getCupoMaximo()) {
                        return materia;
                    }
                }
                if (materia instanceof Programacion) {
                    if (materia.getCodigo().equals(codigoMateria) && ((Programacion) materia).getAlumnosInscriptos().size() < ((Programacion) materia).cupoMaximo) {
                        return materia;
                    }
                }

            }
        return null;
    }
    public Boolean analizarSiNoTieneMismoHorarioDeMaterias(Usuario alumno,Materia materia){

        if (alumno instanceof Alumno) {

            for (Materia materiasDisponibles:
                 materiasAInscribirse) {
                if(materia.horario().equals(materiasDisponibles.horario())) {
                    if (materiasDisponibles instanceof Matematica) {

                        if ( (((Matematica) materiasDisponibles).getAlumnosInscriptos().contains(alumno))) {
                            return false;
                        }
                    }
                    if (materiasDisponibles instanceof Fisica) {

                        if ( (((Fisica) materiasDisponibles).getAlumnosInscriptos().contains(alumno))) {
                            return false;
                        }
                    }
                    if (materiasDisponibles instanceof BaseDeDatos) {

                        if ((((BaseDeDatos) materiasDisponibles).getAlumnosInscriptos().contains(alumno))) {
                            return false;
                        }
                    }
                    if (materiasDisponibles instanceof Programacion) {

                        if ( (((Programacion) materiasDisponibles).getAlumnosInscriptos().contains(alumno))) {
                            return false;
                        }
                    }
                    if (materia instanceof Matematica) {

                        if ((((Matematica) materia).getAlumnosInscriptos().contains(alumno))) {
                            return false;
                        }
                    }
                    if (materia instanceof Fisica) {

                        if ((((Fisica) materia).getAlumnosInscriptos().contains(alumno))) {
                            return false;
                        }
                    }
                    if (materia instanceof BaseDeDatos) {

                        if ( (((BaseDeDatos) materia).getAlumnosInscriptos().contains(alumno))) {
                            return false;
                        }
                    }
                    if (materia instanceof Programacion) {

                        if ((((Programacion) materia).getAlumnosInscriptos().contains(alumno))) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    public Boolean inscribirseAMateria(Usuario alumno, Materia materia) {
        if (alumno != null && materia != null) {
            if (alumno instanceof Alumno) {
                if (buscarLogueados(alumno.getDNI())) {
                    if (buscarMateriaDisponible(materia.getCodigo()) != null && analizarSiNoTieneMismoHorarioDeMaterias(alumno,materia)) {
                        return materia.agregarAlumnoAMateria(((Alumno) alumno));
                    }
                }
            }


        }
        return false;
    }

    public Boolean agregarProfesorALaUniverisdad(Usuario administrador, Usuario profesor) {
        if (administrador != null && profesor != null) {
            if (administrador instanceof Administrador) {
                if (buscarLogueados(administrador.getDNI())) {
                    ((Profesor) profesor).setActivo(true);
                    return registrarUsuario(profesor);
                }
            }
        }
        return false;
    }

    public Boolean agregarProfesorAMateria(Usuario usuario, Integer codigoProfesor, Integer codigoMateria) {
        if (usuario != null && codigoProfesor != null) {
            if (usuario instanceof Administrador)
                if (buscarUsuarioRegistrado(usuario.getDNI()) != null && buscarUsuarioRegistrado(codigoProfesor) != null) {
                    if (buscarMateriaDisponible(codigoMateria) != null) {
                        if (buscarUsuarioRegistrado(codigoProfesor) instanceof Profesor)
                           return buscarMateriaDisponible(codigoMateria).agregarProfesor((Profesor)buscarUsuarioRegistrado(codigoProfesor));


                    }
                }
        }
        return false;
    }
    public Integer getCantidadDeCuposDisponibles(Materia materia){

        if(materia instanceof BaseDeDatos){

            if(((BaseDeDatos) materia).getAlumnosInscriptos().size()==0)
                return materia.getCupoMaximo();
            return  (materia.getCupoMaximo() - ((BaseDeDatos) materia).getAlumnosInscriptos().size());
        }
        if(materia instanceof Matematica){
            if(((Matematica) materia).getAlumnosInscriptos().size()==0)
                return materia.getCupoMaximo();
            return  (materia.getCupoMaximo() - ((Matematica) materia).getAlumnosInscriptos().size());
        }
        if(materia instanceof Fisica){
            if(((Fisica) materia).getAlumnosInscriptos().size()==0)
                return materia.getCupoMaximo();
            return  (materia.getCupoMaximo() - ((Fisica) materia).getAlumnosInscriptos().size());
        }
        if(materia instanceof Programacion){
            if(((Programacion) materia).getAlumnosInscriptos().size()==0)
                return materia.getCupoMaximo();
            return  (materia.getCupoMaximo() - ((Programacion) materia).getAlumnosInscriptos().size());
        }
        return null;
    }
    public String listaDeMaterias(Usuario usuario) {
        String listaDeMaterias = "";
        TreeSet <Materia> materiasDisponibles = new TreeSet<>();
        materiasDisponibles.addAll(this.materiasAInscribirse);
        if (usuario instanceof Alumno || usuario instanceof Administrador) {
            for (Materia materia :
                    materiasDisponibles) {
                if (materia instanceof Matematica) {
                    listaDeMaterias += materia.getNombre() + " "+ materia.getCodigo() + " Horario de cursada" + materia.horario() + ". La cantidad de cupos disponibles son: "+getCantidadDeCuposDisponibles(materia)+"\n";
                }
                if (materia instanceof Programacion) {
                    listaDeMaterias += materia.getNombre() + " "+ materia.getCodigo() + " Horario de cursada" + materia.horario() + ". La cantidad de cupos disponibles son: "+getCantidadDeCuposDisponibles(materia)+ "\n";
                }
                if (materia instanceof BaseDeDatos) {
                    listaDeMaterias += materia.getNombre() + " "+ materia.getCodigo() + " Horario de cursada" + materia.horario() + ". La cantidad de cupos disponibles son: "+getCantidadDeCuposDisponibles(materia)+ "\n";
                }
                if (materia instanceof Fisica) {
                    listaDeMaterias += materia.getNombre() + " " + materia.getCodigo() + " Horario de cursada" + materia.horario() + ". La cantidad de cupos disponibles son: "+getCantidadDeCuposDisponibles(materia)+ "\n";
                }
            }
            return listaDeMaterias;

        }
        return null;
    }

    public String verInfoYDescripcionDeMaterias(Usuario usuario, Materia materia){

        String infoMateria="";

        if (usuario instanceof Alumno || usuario instanceof Administrador) {


            if (materia instanceof Matematica) {
                infoMateria += materia.getNombre() +" "+ materia.getCodigo() +" Horario de cursada" + materia.horario()  + "\n";
            }
            if (materia instanceof Programacion) {
                infoMateria += materia.getNombre() + " " + materia.getCodigo() +" Horario de cursada" + materia.horario()  + "\n";
            }
            if (materia instanceof BaseDeDatos) {
                infoMateria += materia.getNombre() + " " + materia.getCodigo() + " Horario de cursada" + materia.horario()  + "\n";
            }
            if (materia instanceof Fisica) {
                infoMateria += materia.getNombre() + " " + materia.getCodigo() + " Horario de cursada" + materia.horario() + "\n";
            }

            return infoMateria;
        }
        return null;
            }


    public Set<Usuario> getUsuariosLogueados() {
        return usuariosLogueados;
    }

    public void setUsuariosLogueados(Set<Usuario> usuariosLogueados) {
        this.usuariosLogueados = usuariosLogueados;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Materia> getMateriasAInscribirse() {
        return materiasAInscribirse;
    }

    public void setMateriasAInscribirse(Set<Materia> materiasAInscribirse) {
        this.materiasAInscribirse = materiasAInscribirse;
    }

    public Set<Usuario> getUsuariosRegistrados() {
        return usuariosRegistrados;
    }

    public void setUsuariosRegistrados(Set<Usuario> usuariosRegistrados) {
        this.usuariosRegistrados = usuariosRegistrados;
    }
}
