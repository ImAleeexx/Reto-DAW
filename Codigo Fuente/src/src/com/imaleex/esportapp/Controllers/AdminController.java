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
 * <h1>Controlador del Administrador</h1>
 *
 * @author Alex Cortes
 * @author Aritz Castillo
 * @version 1.0
 */
public class AdminController extends UserController {


    /**
     * @param persona persona a insertar
     * @return persona inertada en la base de datos
     */
    public static Persona insertPersona(Persona persona) throws DbException {
        assert Main.user.getType() == 1;
        return PersonaDAO.createPersona(persona);
    }


    //Jugador

    /**
     * @param dni dni del jugador a buscar
     * @return jugador encontrado
     */
    public static Jugador buscarJugador(String dni) throws DataNotFoundException {
        assert Main.user.getType() == 1;
        return JugadorDAO.searchJugador(dni);
    }

    /**
     * @param id id del equipo a buscar
     * @return equipo encontrado
     */
    public static Equipo buscarEquipoId(Integer id) throws DataNotFoundException {
        assert Main.user.getType() == 1;
        return EquipoDAO.searchEquipoById(id);
    }

    /**
     * @param jugador jugador a insertar
     *
     */
    public static void insertjugador(Jugador jugador) throws DbException {
        assert Main.user.getType() == 1;
        if (sueldoTotalJugadores() + jugador.getSueldo() > 2000000) {
            throw new DbException("No se puede insertar el jugador porque sobrepasaría el sueldo máximo");
        }
        JugadorDAO.insertJugador(jugador);
    }

    /**
     * @param jugador jugador a actualizar
     *
     */
    public static void updatejugador(Jugador jugador) throws DbException {
        assert Main.user.getType() == 1;
        if (sueldoTotalJugadores() + jugador.getSueldo() > 2000000) {
            throw new DbException("No se puede modificar el jugador porque sobrepasaría el sueldo máximo");
        }
        JugadorDAO.updateJugador(jugador);
    }

    /**
     * @param jugador jugador a eliminar
     *
     */
    public static void deleteJugador(Jugador jugador) throws DbException {
        assert Main.user.getType() == 1;
        JugadorDAO.deleteJugador(jugador);
    }

    /**
     * @param idPartido id del partido a buscar
     * @return partido encontrado
     */
    public static Partido buscarPartidoId(int idPartido) throws DbException {
        assert Main.user.getType() == 1;
        return PartidoDAO.searchPartido(idPartido);
    }

    /**
     *
     * @return sueldo total de los jugadores
     */
    public static Double sueldoTotalJugadores() throws DbException {
        assert Main.user.getType() == 1;
        ArrayList<Jugador> jugadores = listaJugadores();
        double sueldoTotal = 0.0;
        for (Jugador jugador : jugadores) {
            sueldoTotal += jugador.getSueldo();
        }
        return sueldoTotal;
    }

    //Dueños
    /**
     * Buscar un dueño por su dni en la base de datos
     * @param dni
     * @return dueño encontrado
     */
    public static Dueno buscarDueno(String dni) throws DataNotFoundException {
        assert Main.user.getType() == 1;
        return DuenoDAO.searchDueno(dni);
    }

    /**
     * Buscar un dueño por su id en la base de datos
     * @param id
     * @return dueño encontrado
     */
    public static Dueno buscarDuenoId(int id) throws DataNotFoundException {
        assert Main.user.getType() == 1;
        return DuenoDAO.searchDuenoById(id);
    }

    /**
     * Lista de los dueños de la base de datos
     * @return lista de los dueños
     */
    public static ArrayList<Dueno> listDuenos() throws DbException {
        assert Main.user.getType() == 1;
        return DuenoDAO.listDuenos();
    }

    /**
     * Actualizar dueño de la base de datos
     * @param dueno
     */
    public static void updateDueno(Dueno dueno) throws DbException {
        assert Main.user.getType() == 1;
        DuenoDAO.updateDueno(dueno);
    }

    /**
     * Insertar dueño en la base de datos
     * @param dueno
     */
    public static void insertarDueno(Dueno dueno) throws DbException {
        assert Main.user.getType() == 1;
        DuenoDAO.insertDueno(dueno);
    }

    /**
     * Eliminar dueño de la base de datos
     * @param dueno
     */
    public static void eliminarDueno(Dueno dueno) throws DbException {
        assert Main.user.getType() == 1;
        DuenoDAO.deleteDueno(dueno);
    }

    //Usuarios

    /**
     * Buscar usuario por nombre en la base de datos
     * @param username
     * @return usuario encontrado
     */
    public static Usuario buscarUsuario(String username) throws UserNotFoundException {
        assert Main.user.getType() == 1;
        return UserDAO.searchUsername(username);
    }

    /**
     * Eliminar a un usuario por nombre en la base de datos
     * @param username
     */
    public static void deleteUserByName(String username) throws DbException {
        assert Main.user.getType() == 1;
        UserDAO.deleteUserByName(username);
    }

    /**
     * Editar usuario en la base de datos
     * @param user
     */
    public static void editUser(Usuario user) throws DbException {
        assert Main.user.getType() == 1;
        UserDAO.editUser(user);
    }

    /**
     * Insertar usuario en la base de datos
     * @param user
     */
    public static void createUser(Usuario user) throws DbException {
        assert Main.user.getType() == 1;
        UserDAO.createUser(user);
    }

    /**
     * Lista de los usuarios en la base de datos
     * @return lista de los usuarios
     */
    public static ArrayList<Usuario> listUsers() throws DbException {
        assert Main.user.getType() == 1;
        return UserDAO.listUsers();
    }
    //Entrenadores

    /**
     * Buscar entrenador por dni en la base de datos
     * @param dni
     * @return entrenador encontrado
     */
    public static Entrenador buscarEntrenadorDni(String dni) throws DataNotFoundException, DbException {
        assert Main.user.getType() == 1;
        return EntrenadorDAO.searchEntrenadorByDni(dni);
    }

    /**
     * Buscar entrenador por Id en la base de datos
     * @param id
     * @return entrenador encontrado
     */
    public static Entrenador buscarEntrenadorId(int id) throws DbException, DataNotFoundException {
        assert Main.user.getType() == 1;
        return EntrenadorDAO.searchEntrenador(id);
    }

    /**
     *  Lista de los entrenadores de la base de datos
     * @return lista de los entrenadores
     */
    public static ArrayList<Entrenador> listaEntrenadores() throws DbException {
        assert Main.user.getType() == 1;
        return EntrenadorDAO.listEntrenadores();
    }

    /**
     * Entrenador para actualizarlo en la base de datos
     * @param entrenador
     */
    public static void updateEntrenador(Entrenador entrenador) throws DbException {
        assert Main.user.getType() == 1;
        EntrenadorDAO.updateEntrenador(entrenador);
    }

    /**
     * Entrenador para insertarlo en la base de datos
     * @param entrenador
     */
    public static void insertEntrenador(Entrenador entrenador) throws DbException {
        assert Main.user.getType() == 1;
        EntrenadorDAO.insertEntrenador(entrenador);
    }

    /**
     * Entrenador para eliminarlo de la base de datos
     * @param entrenador
     */
    public static void deleteEntrenador(Entrenador entrenador) throws DbException {
        assert Main.user.getType() == 1;
        EntrenadorDAO.deleteEntrenador(entrenador);
    }

    //Equpos

    /**
     *  Nombre de equipo para buscarlo en la base de datos
     * @param equipo
     * @return equipo encontrado
     */
    public static Equipo buscarEquipo(String equipo) throws DataNotFoundException {
        assert Main.user.getType() == 1;
        return EquipoDAO.searchEquipo(equipo);
    }

    /**
     * Cargar los jugadores en un objeto Equipo
     * @param equipo
     */
    public static void loadJugadoresToEquipo(Equipo equipo) {
        assert Main.user.getType() == 1;
        try {
            equipo.setJugadores(EquipoDAO.getJugadoresByEquipo(equipo));
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Insertar el equipo que le pasamos en la base de datos
     * @param equipo
     */
    public static void insertEquipo(Equipo equipo) throws DbException {
        assert Main.user.getType() == 1;
        checkDuplicatesEquipo(equipo);
        EquipoDAO.insertEquipo(equipo);
    }

    /**
     * Actualizar el equipo que le pasamos en la base de datos
     * @param equipo
     */
    public static void updateEquipo(Equipo equipo) throws DbException {
        assert Main.user.getType() == 1;
        checkDuplicatesEquipo(equipo);
        EquipoDAO.updateEquipo(equipo);
    }

    /**
     * Equipo para mirar si el dueño, el entrenador o el asistente ya tienen un equipo asignado
     * @param equipo
     */
    private static void checkDuplicatesEquipo(Equipo equipo) throws DbException {
        Equipo equipoDueno = EquipoDAO.searchEquipoByDueno(equipo.getDueno());
        Equipo equipoEntrenador = EquipoDAO.searchEquipoByEntrenador(equipo.getEntrenador());
        Equipo equipoAsistente = EquipoDAO.searchEquipoByEntrenador(equipo.getEntrenadorAsistente());
        if (equipoDueno != null && equipoDueno.getId() != equipo.getId()) {
            throw new DbException("Ya existe un equipo con ese dueno");
        }
        if (equipoEntrenador != null && equipoEntrenador.getId() != equipo.getId()) {
            throw new DbException("Ya existe un equipo con ese entrenador");
        }
        if (equipoAsistente != null && equipoAsistente.getId() != equipo.getId()) {
            throw new DbException("Ya existe un equipo con ese entrenador asistente");
        }
    }

    /**
     * Lista los equipos de la base de datos
     * @return lista de equipos
     */
    public static ArrayList<Equipo> listaEquipos() throws DbException {
        assert Main.user.getType() == 1;
        return EquipoDAO.listEquipos();
    }

    //Persona

    /**
     * Insertamos una Persona en la base de datos
     * @param persona
     */
    public static Persona insertarPersona(Persona persona) throws DbException {
        assert Main.user.getType() == 1;
        PersonaDAO.createPersona(persona);
        return persona;
    }
    //Jornadas

    /**
     * Devuelve una lista con las jornadas
     * @return lista de jornadas
     */
    public static ArrayList<Jornada> getJornadas() throws DbException {
        assert Main.user.getType() == 1;
        return JornadaDAO.getJornadas();
    }

    //Partidos
    /**
     * Actualiza el marcador de un partido
     * @param partido
     */
    public static void actualizarMarcador(Partido partido) throws DbException {
        assert Main.user.getType() == 1;
        PartidoDAO.updatePartido(partido);
    }


    //Generacion partidos
    /**
     * Generador de los partidos del calendario
     */
    public static void generateMatchCalendar() throws DbException {
        assert Main.user.getType() == 1;

        if (!JornadaDAO.getJornadas().isEmpty()) {
            throw new DbException("Ya existen jornadas");
        }


        ArrayList<Equipo> listaEquipos = EquipoDAO.listEquipos();
        ArrayList<Equipo> listaEquipos1 = new ArrayList<>();
        ArrayList<Equipo> listaEquipos2 = new ArrayList<>();

        Collections.shuffle(listaEquipos);

        for (int i = 0; i < listaEquipos.size(); i++) {
            if (i <= (listaEquipos.size() / 2) - 1) {
                listaEquipos1.add(listaEquipos.get(i));
            } else {
                listaEquipos2.add(listaEquipos.get(i));
            }
        }
        //Reverseamos la lista 2
        Collections.reverse(listaEquipos2);
        int numJornadas = (listaEquipos.size() - 1);
        int numPartidos = listaEquipos.size() / 2;
        ArrayList<Partido> listaPartidos = new ArrayList<>();
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
                    JornadaDAO.deleteAllJornadas();
                    PartidoDAO.deleteAllPartidos();
                    throw new DbException("Error en la generacion de partidos, el equipo " + equipo1.getNombre() + " no tiene suficientes jugadores");
                }
                if (equipo2.getJugadores().size() <= 2) {
                    JornadaDAO.deleteAllJornadas();
                    PartidoDAO.deleteAllPartidos();
                    throw new DbException("Error en la generacion de partidos, el equipo " + equipo2.getNombre() + " no tiene suficientes jugadores");
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
        for (Partido listaPartido : listaPartidos) {
            try {
                PartidoDAO.insertPartido(listaPartido);
            } catch (DbException e) {
                throw new RuntimeException(e);
            }

        }


    }
    /**
     * Revisamos si la liga esta iniciada
     */
    public static boolean checkLeagueStarted(){
        try {
            return JornadaDAO.getJornadas().size() > 0;
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Saca una lista de los jugadores
     * @return lista de jugadores
     */
    public static ArrayList<Jugador> listaJugadores() throws DbException {
        assert Main.user.getType() == 1;
        return JugadorDAO.listaJugadores();
    }
}
