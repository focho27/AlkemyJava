package ar.alkemy.test;

import ar.alkemy.ejercicio.*;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class TestAplicacion {
private static Calendar calendario;
    @Test
    public void queSePuedaRegistrarUsuarioAdministrador(){
        Usuario administrador = new Administrador(14123123,1414,"Alejandro");
        Aplicacion aplicacionUniverisdad= new Aplicacion("Universidad De La Matanza");
        assertTrue(aplicacionUniverisdad.registrarUsuario(administrador));
    }

    @Test
    public void queSePuedaRegistrarUsuarioAlumno(){
        Usuario alumno = new Alumno(14123123,1414,"Alejandro");
        Aplicacion aplicacionUniverisdad= new Aplicacion("Universidad De La Matanza");
        assertTrue(aplicacionUniverisdad.registrarUsuario(alumno));
    }
    @Test
    public void queSePuedaRegistrarUsuarioProfesor(){
        Aplicacion aplicacionUniverisdad= new Aplicacion("Universidad De La Matanza");
        Usuario profesor = new Profesor(14123123,"Alejandro","Martinez");
        Usuario administrador = new Administrador(14123123,1414,"Alejandro");
        aplicacionUniverisdad.registrarUsuario(administrador);
        aplicacionUniverisdad.login(administrador);
        assertTrue(aplicacionUniverisdad.agregarProfesorALaUniverisdad(administrador,profesor));

    }

    @Test
    public void quesePuedaLoguearUnUsuarioAdministrador(){
        Usuario administrador = new Administrador(14123123,1414,"Alejandro");
        Aplicacion aplicacionUniverisdad= new Aplicacion("Universidad De La Matanza");
        aplicacionUniverisdad.registrarUsuario(administrador);
        assertTrue(aplicacionUniverisdad.login(administrador));
    }

    @Test
    public void quesePuedaLoguearUnUsuarioAlumno(){
        Usuario alumno = new Alumno(14123123,1414,"Alejandro");
        Aplicacion aplicacionUniverisdad= new Aplicacion("Universidad De La Matanza");
        aplicacionUniverisdad.registrarUsuario(alumno);
        assertTrue(aplicacionUniverisdad.login(alumno));
    }

    @Test
    public void quesePuedaLoguearUnUsuarioProfesor(){
        Aplicacion aplicacionUniverisdad= new Aplicacion("Universidad De La Matanza");
        Usuario profesor = new Profesor(14123123,"Alejandro","Martinez");
        Usuario administrador = new Administrador(14123123,1414,"Alejandro");
        aplicacionUniverisdad.registrarUsuario(administrador);
        aplicacionUniverisdad.login(administrador);
        aplicacionUniverisdad.agregarProfesorALaUniverisdad(administrador,profesor);
        assertTrue(aplicacionUniverisdad.login(profesor));
    }
    @Test
    public void queNoSePuedaLoguearUnUsuarioProfesor() {
        Aplicacion aplicacionUniverisdad = new Aplicacion("Universidad De La Matanza");
        Usuario profesor = new Profesor(14123123, "Alejandro", "Martinez");
        Usuario administrador = new Administrador(14123123, 1414, "Alejandro");
        aplicacionUniverisdad.registrarUsuario(administrador);
        aplicacionUniverisdad.login(administrador);
        aplicacionUniverisdad.agregarProfesorALaUniverisdad(administrador, profesor);
        if (profesor instanceof Profesor)
        ((Profesor) profesor).setActivo(false);
        assertFalse(aplicacionUniverisdad.login(profesor));
    }

    @Test
    public void queSePuedaAgregarUnaMateria(){
        Aplicacion aplicacionUniverisdad= new Aplicacion("Universidad De La Matanza");
        Usuario administrador = new Administrador(14123123,1414,"Alejandro");
        Date horario = new Date();
        Materia materia = new Matematica(1,"Matematica",80,horario);
        aplicacionUniverisdad.registrarUsuario(administrador);
        aplicacionUniverisdad.login(administrador);
        assertTrue(aplicacionUniverisdad.agregarMateria(administrador,materia));

    }

    @Test
    public void queSeAgreguenTodasLasMaterias(){
        Aplicacion aplicacionUniverisdad= new Aplicacion("Universidad De La Matanza");
        Usuario administrador = new Administrador(14123123,1414,"Alejandro");
        Date horario = new Date();
        Materia materia = new Matematica(1,"Matematica",80,horario);
        Materia materia2 = new BaseDeDatos(2,"Matematica",80,horario);
        Materia materia3 = new Fisica(3,"Matematica",80,horario);
        Materia materia4 = new Programacion(4,"Matematica",80,horario);
        aplicacionUniverisdad.registrarUsuario(administrador);
        aplicacionUniverisdad.login(administrador);

        aplicacionUniverisdad.agregarMateria(administrador,materia);
        aplicacionUniverisdad.agregarMateria(administrador,materia2);
        aplicacionUniverisdad.agregarMateria(administrador,materia3);
        aplicacionUniverisdad.agregarMateria(administrador,materia4);

        assertEquals(4,aplicacionUniverisdad.getMateriasAInscribirse().size());
    }
    @Test 
    public  void queMuestreListaDeMateriasDisponibles(){
        Aplicacion aplicacionUniverisdad= new Aplicacion("Universidad De La Matanza");
        Usuario administrador = new Administrador(14123123,1414,"Alejandro");

        calendario = Calendar.getInstance();
        Date horario = calendario.getTime();
        horario.setHours(4);

        Materia materia = new Matematica(1,"Matematica",80,horario);
        Materia materia2 = new BaseDeDatos(2,"BD",80,horario);
        Materia materia3 = new Fisica(3,"Fisica",80,horario);
        Materia materia4 = new Programacion(4,"Programacion",80,horario);
        Usuario alumno = new Alumno(14123123,1414,"Alejandro");

        aplicacionUniverisdad.registrarUsuario(alumno);
        aplicacionUniverisdad.login(alumno);

        aplicacionUniverisdad.registrarUsuario(administrador);
        aplicacionUniverisdad.login(administrador);

        aplicacionUniverisdad.agregarMateria(administrador,materia);
        aplicacionUniverisdad.agregarMateria(administrador,materia2);
        aplicacionUniverisdad.agregarMateria(administrador,materia3);
        aplicacionUniverisdad.agregarMateria(administrador,materia4);

        aplicacionUniverisdad.inscribirseAMateria(alumno,materia);
        aplicacionUniverisdad.inscribirseAMateria(alumno,materia2);
        aplicacionUniverisdad.inscribirseAMateria(alumno,materia3);
        aplicacionUniverisdad.inscribirseAMateria(alumno,materia4);

        System.out.println(aplicacionUniverisdad.listaDeMaterias(administrador));
        assertTrue(true);
    }
    @Test
    public  void queNoPuedaInscribirseADosMateriasEnElMismoHorario(){
        Aplicacion aplicacionUniverisdad= new Aplicacion("Universidad De La Matanza");
        Usuario administrador = new Administrador(14123123,1414,"Alejandro");

        calendario = Calendar.getInstance();
        Date horario = calendario.getTime();
        horario.setHours(4);

        Materia materia = new Matematica(1,"Matematica",80,horario);
        Materia materia2 = new BaseDeDatos(2,"BD",80,horario);
        Materia materia3 = new Fisica(3,"Fisica",80,horario);
        Materia materia4 = new Programacion(4,"Programacion",80,horario);
        Usuario alumno = new Alumno(14123123,1414,"Alejandro");

        aplicacionUniverisdad.registrarUsuario(alumno);
        aplicacionUniverisdad.login(alumno);
        aplicacionUniverisdad.registrarUsuario(administrador);

        aplicacionUniverisdad.login(administrador);

        aplicacionUniverisdad.agregarMateria(administrador,materia);
        aplicacionUniverisdad.agregarMateria(administrador,materia2);
        aplicacionUniverisdad.agregarMateria(administrador,materia3);
        aplicacionUniverisdad.agregarMateria(administrador,materia4);
        aplicacionUniverisdad.inscribirseAMateria(alumno,materia);
        aplicacionUniverisdad.inscribirseAMateria(alumno,materia2);
        aplicacionUniverisdad.inscribirseAMateria(alumno,materia3);
        aplicacionUniverisdad.inscribirseAMateria(alumno,materia4);
        System.out.println(aplicacionUniverisdad.listaDeMaterias(administrador));
        assertEquals(0,((Fisica)materia3).getAlumnosInscriptos().size());
    }


    @Test
    public void quesePuedaAgregarProfesorAMateria(){
        Aplicacion aplicacionUniverisdad= new Aplicacion("Universidad De La Matanza");
        Usuario profesor = new Profesor(14123123,"Alejandro","Martinez");
        Usuario administrador = new Administrador(14123124,1414,"Alejandro");
        aplicacionUniverisdad.registrarUsuario(administrador);
        aplicacionUniverisdad.login(administrador);
        aplicacionUniverisdad.agregarProfesorALaUniverisdad(administrador,profesor);
        aplicacionUniverisdad.login(profesor);
        calendario = Calendar.getInstance();
        Date horario = calendario.getTime();
        horario.setHours(4);

        Materia materia = new Matematica(1,"Matematica",80,horario);
        aplicacionUniverisdad.agregarMateria(administrador,materia);

        assertTrue(aplicacionUniverisdad.agregarProfesorAMateria(administrador,14123123,1));

    }
    @Test
    public void queNoSePuedanAgregarInstanciaDeUsuarioConCodigosIguales(){
        Aplicacion aplicacionUniverisdad= new Aplicacion("Universidad De La Matanza");
        Usuario profesor = new Profesor(14123124,"Alejandro","Martinez");
        Usuario administrador = new Administrador(14123124,1414,"Alejandro");
        aplicacionUniverisdad.registrarUsuario(administrador);
        aplicacionUniverisdad.login(administrador);
        assertFalse(aplicacionUniverisdad.agregarProfesorALaUniverisdad(administrador,profesor));


    }
    @Test
    public void queNoSePuedanAgregarInstanciaDeMateriaoConCodigosIguales(){
        Aplicacion aplicacionUniverisdad= new Aplicacion("Universidad De La Matanza");
        Usuario administrador = new Administrador(14123123,1414,"Alejandro");
        Date horario = new Date();
        Materia materia = new Matematica(1,"Matematica",80,horario);
        Materia materia2 = new BaseDeDatos(1,"Matematica",80,horario);
        Materia materia3 = new Fisica(1,"Matematica",80,horario);
        Materia materia4 = new Programacion(1,"Matematica",80,horario);
        aplicacionUniverisdad.registrarUsuario(administrador);
        aplicacionUniverisdad.login(administrador);

        aplicacionUniverisdad.agregarMateria(administrador,materia);
        aplicacionUniverisdad.agregarMateria(administrador,materia2);
        aplicacionUniverisdad.agregarMateria(administrador,materia3);
        aplicacionUniverisdad.agregarMateria(administrador,materia4);

        assertEquals(1,aplicacionUniverisdad.getMateriasAInscribirse().size()); 
    }

    @Test
    public void queLosUsuariosLogueadosSeanTres(){
        Aplicacion aplicacionUniverisdad= new Aplicacion("Universidad De La Matanza");
        Usuario administrador = new Administrador(14123123,1414,"Alejandro");
        Usuario profesor = new Profesor(14123124,"Alejandro","Martinez");

        Usuario alumno = new Alumno(14123122,1414,"Carla");
        Date horario = new Date();
        Materia materia = new Matematica(1,"Matematica",80,horario);
        Materia materia2 = new BaseDeDatos(1,"Matematica",80,horario);
        Materia materia3 = new Fisica(1,"Matematica",80,horario);
        Materia materia4 = new Programacion(1,"Matematica",80,horario);

        aplicacionUniverisdad.registrarUsuario(administrador);
        aplicacionUniverisdad.login(administrador);

        aplicacionUniverisdad.registrarUsuario(alumno);
        aplicacionUniverisdad.login(alumno);

        aplicacionUniverisdad.agregarMateria(administrador,materia);
        aplicacionUniverisdad.agregarMateria(administrador,materia2);
        aplicacionUniverisdad.agregarMateria(administrador,materia3);
        aplicacionUniverisdad.agregarMateria(administrador,materia4);


        aplicacionUniverisdad.agregarProfesorALaUniverisdad(administrador,profesor);
        aplicacionUniverisdad.login(profesor);

        assertEquals(3,aplicacionUniverisdad.getUsuariosLogueados().size());
    }


    @Test
    public void queUnAlumnoPuedaVerInfoDeMateria(){
        Aplicacion aplicacionUniverisdad= new Aplicacion("Universidad De La Matanza");
        Usuario administrador = new Administrador(14123123,1414,"Alejandro");
        Usuario alumno = new Alumno(14123122,1414,"Carla");
        Date horario = new Date();
        Materia materia = new Matematica(1,"Matematica",80,horario);
        Materia materia2 = new BaseDeDatos(1,"Matematica",80,horario);
        Materia materia3 = new Fisica(1,"Matematica",80,horario);
        Materia materia4 = new Programacion(1,"Matematica",80,horario);
        aplicacionUniverisdad.registrarUsuario(administrador);
        aplicacionUniverisdad.login(administrador);

        aplicacionUniverisdad.registrarUsuario(alumno);
        aplicacionUniverisdad.login(alumno);

        System.out.println(aplicacionUniverisdad.verInfoYDescripcionDeMaterias(alumno,materia));

    }





}
