package com.imaleex.esportapp.Controllers;

import com.imaleex.esportapp.Db.Dao.EquipoDAO;
import com.imaleex.esportapp.Db.Dao.PartidoDAO;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Models.Equipo;
import com.imaleex.esportapp.Models.Jornada;
import com.imaleex.esportapp.Models.Partido;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * <h1>Controlador del Usuario</h1>
 *
 * @author Alex Cortes
 * @author Aritz Castillo
 * @version 1.0
 */
public class UserController {
    /**
     * Genera la classificacion de la liga para despues mostrarla en la vista
     * @return Objeto con la clasificacion
     */
    public static Object[][] generateClasificacion() throws DbException {
        ArrayList<Partido> listaPartidos = PartidoDAO.listaPartidosJugados();
        ArrayList<Equipo> listaEquipos = listaEquipos();
        Object[][] clasificacion = new Object[listaEquipos.size()][5];
        //Llenamos la clasificacion con los equipos y valores a 0
        for (int i = 0; i < listaEquipos.size(); i++) {
            clasificacion[i][0] = listaEquipos.get(i).getNombre();
            //Partidos jugados
            clasificacion[i][1] = 0;
            //Partidos ganados
            clasificacion[i][2] = 0;
            //Partidos perdidos
            clasificacion[i][3] = 0;
            //Puntos
            clasificacion[i][4] = 0;
        }
        for (Partido listaPartido : listaPartidos) {
            for (int j = 0; j < listaEquipos.size(); j++) {
                if (listaPartido.getGanador().getNombre().equals(clasificacion[j][0])) {
                    clasificacion[j][1] = (int) clasificacion[j][1] + 1;
                    clasificacion[j][2] = (int) clasificacion[j][2] + 1;
                    clasificacion[j][4] = (int) clasificacion[j][4] + 3;
                } else if (listaPartido.getPerdedor().getNombre().equals(clasificacion[j][0])) {
                    clasificacion[j][1] = (int) clasificacion[j][1] + 1;
                    clasificacion[j][3] = (int) clasificacion[j][3] + 1;
                }
            }
        }
        final Comparator<Object[]> arrayComparator = new Comparator<Object[]>() {
            @Override
            public int compare(Object[] o1, Object[] o2) {
                Integer puntos1 = (Integer) o1[4];
                Integer puntos2 = (Integer) o2[4];
                return puntos2.compareTo(puntos1);
            }
        };
        //Ordenamos la clasificacion
        java.util.Arrays.sort(clasificacion, arrayComparator);

        return clasificacion;
    }

    /**
     * Lista de los jugadores de la base de datos
     * @return lista de los equipos
     */
    private static ArrayList<Equipo> listaEquipos() throws DbException {
        return EquipoDAO.listEquipos();
    }

    /**
     * Lista de los partidos de la jornada
     * @param jornada
     * @return lista de los partidos de la jornada
     */
    public static ArrayList<Partido> listaPartidosByJornada(Jornada jornada) throws DbException {
        return PartidoDAO.listaPartidosByJornada(jornada);
    }
}
