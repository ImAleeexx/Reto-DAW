package com.imaleex.esportapp.Controllers;

import com.imaleex.esportapp.Db.Dao.EquipoDAO;
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
import com.imaleex.esportapp.Models.Partido;
import com.imaleex.esportapp.Models.Personas.Dueno;
import com.imaleex.esportapp.Models.Personas.Entrenador;
import com.imaleex.esportapp.Models.Personas.Jugador;
import com.imaleex.esportapp.Models.Personas.Persona;
import com.imaleex.esportapp.Models.Users.Usuario;

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
    public  static  void  insertEntrenador(Entrenador entrenador) throws DbException {
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
            EquipoDAO.getJugadoresByEquipo(equipo);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }
    //Equipos

    //Persona

    public static Persona insertarPersona(Persona persona) throws DbException {
        assert Main.user.getType() == 1;
        PersonaDAO.createPersona(persona);
        return persona;
    }


    public static ArrayList<Equipo> listaEquipos() {
        assert Main.user.getType() == 1;
        return EquipoDAO.listEquipos();
    }


    public static void actualizarMarcador(Partido partido) throws DbException {
        assert Main.user.getType() == 1;
        PartidoDAO.updatePartido(partido);
    }


}
