package com.imaleex.esportapp.Controllers;

import com.imaleex.esportapp.Db.Dao.EquipoDAO;
import com.imaleex.esportapp.Db.Dao.JornadaDAO;
import com.imaleex.esportapp.Db.Dao.PartidoDAO;
import com.imaleex.esportapp.Db.Dao.Personas.DuenoDAO;
import com.imaleex.esportapp.Db.Dao.Personas.EntrenadorDAO;
import com.imaleex.esportapp.Db.Dao.Personas.JugadorDAO;
import com.imaleex.esportapp.Db.Dao.Personas.PersonaDAO;
import com.imaleex.esportapp.Db.Dao.UserDAO;
import com.imaleex.esportapp.Exceptions.DataNotFoundException;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Exceptions.UserNotFoundException;
import com.imaleex.esportapp.Main;
import com.imaleex.esportapp.Models.Equipo;
import com.imaleex.esportapp.Models.Jornada;
import com.imaleex.esportapp.Models.Partido;
import com.imaleex.esportapp.Models.Personas.Dueno;
import com.imaleex.esportapp.Models.Personas.Entrenador;
import com.imaleex.esportapp.Models.Personas.Jugador;
import com.imaleex.esportapp.Models.Personas.Persona;
import com.imaleex.esportapp.Models.Users.Usuario;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Alex Cortes
 */
public class AdminController extends UserController {


    //Personas
    public static Persona insertPersona(Persona persona) throws DbException {
        assert Main.user.getType() == 1;
        return PersonaDAO.createPersona(persona);
    }

    public static void deletePersona(Persona persona) throws DbException {
        assert Main.user.getType() == 1;
        PersonaDAO.deletePersona(persona);
    }

    public static void updatePersona(Persona persona) throws DbException {
        assert Main.user.getType() == 1;
        PersonaDAO.updatePersona(persona);
    }


    //Jugador
    public static Jugador buscarJugador(String dni) throws DataNotFoundException {
        assert Main.user.getType() == 1;
        return JugadorDAO.searchJugador(dni);
    }

    public static Equipo buscarEquipoId(Integer id) throws DataNotFoundException {
        assert Main.user.getType() == 1;
        return EquipoDAO.searchEquipoById(id);
    }

    public static void insertjugador(Jugador jugador) throws DbException {
        assert Main.user.getType() == 1;
        JugadorDAO.insertJugador(jugador);
    }

    public static void updatejugador(Jugador jugador) throws DbException {
        assert Main.user.getType() == 1;
        JugadorDAO.updateJugador(jugador);
    }

    public static void deleteJugador(Jugador jugador) throws DbException {
        assert Main.user.getType() == 1;
        JugadorDAO.deleteJugador(jugador);
    }

    public static Partido buscarPartidoId(int idPartido) throws DbException {
        assert Main.user.getType() == 1;
        return PartidoDAO.searchPartido(idPartido);
    }

    //Dueños
    public static Dueno buscarDueno(String dni) throws DataNotFoundException {
        assert Main.user.getType() == 1;
        return DuenoDAO.searchDueno(dni);
    }

    public static Dueno buscarDuenoId(int id) throws DataNotFoundException {
        assert Main.user.getType() == 1;
        return DuenoDAO.searchDuenoById(id);
    }

    public static ArrayList<Dueno> listDuenos() throws DbException {
        assert Main.user.getType() == 1;
        return DuenoDAO.listDuenos();
    }

    public static void updateDueno(Dueno dueno) throws DbException {
        assert Main.user.getType() == 1;
        DuenoDAO.updateDueno(dueno);
    }

    public static void insertarDueno(Dueno dueno) throws DbException {
        assert Main.user.getType() == 1;
        DuenoDAO.insertDueno(dueno);
    }

    public static void eliminarDueno(Dueno dueno) throws DbException {
        assert Main.user.getType() == 1;
        DuenoDAO.deleteDueno(dueno);
    }

    //Usuarios

    public static Usuario buscarUsuario(String username) throws UserNotFoundException {
        assert Main.user.getType() == 1;
        return UserDAO.searchUsername(username);
    }

    public static void deleteUserByName(String username) throws DbException {
        assert Main.user.getType() == 1;
        UserDAO.deleteUserByName(username);
    }

    public static void editUser(Usuario user) throws DbException {
        assert Main.user.getType() == 1;
        UserDAO.editUser(user);
    }

    public static void createUser(Usuario user) throws DbException {
        assert Main.user.getType() == 1;
        UserDAO.createUser(user);
    }

    public static ArrayList<Usuario> listUsers() throws DbException {
        assert Main.user.getType() == 1;
        return UserDAO.listUsers();
    }
    //Entrenadores

    public static Entrenador buscarEntrenadorDni(String dni) throws DataNotFoundException, DbException {
        assert Main.user.getType() == 1;
        return EntrenadorDAO.searchEntrenadorByDni(dni);
    }

    public static Entrenador buscarEntrenadorId(int id) throws DbException, DataNotFoundException {
        assert Main.user.getType() == 1;
        return EntrenadorDAO.searchEntrenador(id);
    }

    public static ArrayList<Entrenador> listaEntrenadores() throws DbException {
        assert Main.user.getType() == 1;
        return EntrenadorDAO.listEntrenadores();
    }

    public static void updateEntrenador(Entrenador entrenador) throws DbException {
        assert Main.user.getType() == 1;
        EntrenadorDAO.updateEntrenador(entrenador);
    }

    public static void insertEntrenador(Entrenador entrenador) throws DbException {
        assert Main.user.getType() == 1;
        EntrenadorDAO.insertEntrenador(entrenador);
    }

    public static void deleteEntrenador(Entrenador entrenador) throws DbException {
        assert Main.user.getType() == 1;
        EntrenadorDAO.deleteEntrenador(entrenador);
    }

    //Equpos

    public static Equipo buscarEquipo(String equipo) throws DataNotFoundException {
        assert Main.user.getType() == 1;
        return EquipoDAO.searchEquipo(equipo);
    }

    public static void loadJugadoresToEquipo(Equipo equipo) {
        assert Main.user.getType() == 1;
        try {
            equipo.setJugadores(EquipoDAO.getJugadoresByEquipo(equipo));
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertEquipo(Equipo equipo) throws DbException {
        assert Main.user.getType() == 1;
        EquipoDAO.insertEquipo(equipo);
    }

    public static void updateEquipo(Equipo equipo) throws DbException {
        assert Main.user.getType() == 1;
        EquipoDAO.updateEquipo(equipo);
    }

    public static ArrayList<Equipo> listaEquipos() throws DbException {
        assert Main.user.getType() == 1;
        return EquipoDAO.listEquipos();
    }

    //Persona

    public static Persona insertarPersona(Persona persona) throws DbException {
        assert Main.user.getType() == 1;
        PersonaDAO.createPersona(persona);
        return persona;
    }
    //Jornadas


    public static ArrayList<Jornada> getJornadas() throws DbException {
        assert Main.user.getType() == 1;
        return JornadaDAO.getJornadas();
    }

    //Partidos
    public static void actualizarMarcador(Partido partido) throws DbException {
        assert Main.user.getType() == 1;
        PartidoDAO.updatePartido(partido);
    }


    //Generacion partidos
    public static void generateMatchCalendar() throws DbException {
        assert Main.user.getType() == 1;

        if (!JornadaDAO.getJornadas().isEmpty()) {
            throw new DbException("Ya existen jornadas");
        }


        ArrayList<Equipo> listaEquipos = EquipoDAO.listEquipos();
        ArrayList<Equipo> listaEquipos1 = new ArrayList<Equipo>();
        ArrayList<Equipo> listaEquipos2 = new ArrayList<Equipo>();

        Collections.shuffle(listaEquipos);

        for (int i = 0; i < listaEquipos.size(); i++) {
            if (i <= 4) {
                listaEquipos1.add(listaEquipos.get(i));
            } else {
                listaEquipos2.add(listaEquipos.get(i));
            }
        }
        //Reverseamos la lista 2
        Collections.reverse(listaEquipos2);
        int numJornadas = (listaEquipos.size() - 1);
        int numPartidos = listaEquipos.size() / 2;
        ArrayList<Partido> listaPartidos = new ArrayList<Partido>();
        //Loop por cada jornada
        for (int i = 0; i < numJornadas; i++) {
            Jornada jornada = new Jornada();
            jornada.setFecha(LocalDate.now().plusWeeks(i).with(DayOfWeek.SATURDAY));
            try {
                jornada = JornadaDAO.insertJornada(jornada);
            } catch (DbException e) {
                throw new RuntimeException(e);
            }
            //Loop por cada partido de la jornada
            for (int j = 0; j < numPartidos; j++) {
                Equipo equipo1 = listaEquipos1.get(j);
                Equipo equipo2 = listaEquipos2.get(j);
                if (equipo1.getJugadores().size() <= 2) {
                    throw new RuntimeException("Error en la generacion de partidos, el equipo " + equipo1.getNombre() + " no tiene suficientes jugadores");
                }
                if (equipo2.getJugadores().size() <= 2) {
                    throw new RuntimeException("Error en la generacion de partidos, el equipo " + equipo2.getNombre() + " no tiene suficientes jugadores");
                }
                LocalTime time = LocalTime.of((16) + j, 0);
                Partido partido = new Partido();
                partido.setLocal(equipo1);
                partido.setVisitante(equipo2);
                partido.setMarcadorLocal(-1);
                partido.setMarcadorVisitante(-1);
                partido.setJornada(jornada);
                partido.setHora(time);
                listaPartidos.add(partido);
            }
            Equipo equipoMoverLista1 = listaEquipos1.get(listaEquipos1.size() - 1);
            Equipo equipoMoverLista2 = listaEquipos2.get(0);

            //Rotamos con orientacion de agujas del reloj
            listaEquipos1.remove(listaEquipos1.size() - 1);
            listaEquipos2.add(equipoMoverLista1);

            listaEquipos2.remove(0);
            listaEquipos1.add(1, equipoMoverLista2);
        }

        //Insertamos los partidos en la base de datos
        for (int i = 0; i < listaPartidos.size(); i++) {
            try {
                PartidoDAO.insertPartido(listaPartidos.get(i));
            } catch (DbException e) {
                throw new RuntimeException(e);
            }

        }


    }

    public static boolean checkLeagueStarted(){
        try {
            return JornadaDAO.getJornadas().size() > 0;
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Jugador> listaJugadores() throws DbException {
        assert Main.user.getType() == 1;
        return JugadorDAO.listaJugadores();
    }
}
