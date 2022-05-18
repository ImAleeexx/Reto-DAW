package com.imaleex.esportapp.Controllers;

import com.imaleex.esportapp.Db.Dao.EquipoDAO;
import com.imaleex.esportapp.Db.Dao.Personas.DuenoDAO;
import com.imaleex.esportapp.Db.Dao.Personas.EntrenadorDAO;
import com.imaleex.esportapp.Db.Dao.Personas.JugadorDAO;
import com.imaleex.esportapp.Db.Dao.UserDAO;
import com.imaleex.esportapp.Exceptions.DataNotFoundException;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Exceptions.UserNotFoundException;
import com.imaleex.esportapp.Main;
import com.imaleex.esportapp.Models.Equipo;
import com.imaleex.esportapp.Models.Personas.Dueno;
import com.imaleex.esportapp.Models.Personas.Entrenador;
import com.imaleex.esportapp.Models.Personas.Jugador;
import com.imaleex.esportapp.Models.Users.Usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Alex Cortes
 */
public class AdminController {


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


    //Usuarios

    public static Usuario buscarUsuario(String username) throws UserNotFoundException {
        assert Main.user.getType() == 1;
        return UserDAO.searchUsername(username);
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


    //Equpos

    public static Equipo buscarEquipo(String equipo) throws  DataNotFoundException {
        assert Main.user.getType() == 1;
        return EquipoDAO.searchEquipo(equipo);
    }
    public static void loadJugadoresToEquipo (Equipo equipo) {
        assert Main.user.getType() == 1;
        try {
            EquipoDAO.getJugadoresByEquipo(equipo);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }






    public static void generateMatches(){

        ArrayList<String> listaEquipos = new ArrayList<>();
        ArrayList<String> listaEquipos1 = new ArrayList<>();
        ArrayList<String> listaEquipos2 = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            listaEquipos.add("Equipo" + i);
        }

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
        int numJornadas = (listaEquipos.size() -1);
        int numPartidos = listaEquipos.size() / 2;
        StringBuilder sb = new StringBuilder();
        //Loop por cada jornada
        for (int i = 0; i < numJornadas; i++) {
            sb.append("Jornada " + (i + 1) + "\n");
            //Loop por cada partido de la jornada
            for (int j = 0; j < numPartidos; j++) {
                String equipo1 = listaEquipos1.get(j);
                String equipo2 = listaEquipos2.get(j);
                sb.append(equipo1 + " vs " + equipo2 + "\n");
            }
            sb.append("\n");
            String equipoMoverLista1 = listaEquipos1.get(listaEquipos1.size() - 1);
            String equipoMoverLista2 = listaEquipos2.get(0);

            //Rotamos con orientacion de agujas del reloj
            listaEquipos1.remove(listaEquipos1.size() - 1);
            listaEquipos2.add(equipoMoverLista1);

            listaEquipos2.remove(0);
            listaEquipos1.add(1, equipoMoverLista2);
        }
        System.out.println(sb.toString());
        //Todo make vuelta partidos
    }


    public static ArrayList<Equipo> listaEquipos() {
        assert Main.user.getType() == 1;
        return EquipoDAO.listEquipos();
    }

    public static Jugador buscarJugador(String dni) throws DataNotFoundException {
        assert Main.user.getType() == 1;
        return JugadorDAO.searchJugador(dni);
    }

    public static Equipo buscarEquipoId(Integer id)  throws DataNotFoundException  {
        assert Main.user.getType() == 1;
        return EquipoDAO.searchEquipoById(id);
    }
}
