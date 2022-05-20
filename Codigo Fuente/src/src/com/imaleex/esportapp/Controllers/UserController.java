package com.imaleex.esportapp.Controllers;

import com.imaleex.esportapp.Db.Dao.EquipoDAO;
import com.imaleex.esportapp.Db.Dao.PartidoDAO;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Models.Equipo;
import com.imaleex.esportapp.Models.Partido;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author Alex Cortes
 */
public class UserController {
    public static Object[][] generateClasificacion() throws DbException {
       ArrayList<Partido> listaPartidos =  PartidoDAO.listaPartidosJugados();
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
       for (int i = 0; i < listaPartidos.size(); i++) {
           for (int j = 0; j < listaEquipos.size(); j++) {
               if (listaPartidos.get(i).getGanador().getNombre().equals(clasificacion[j][0])) {
                   clasificacion[j][1] = (int) clasificacion[j][1] + 1;
                   clasificacion[j][2] = (int) clasificacion[j][2] + 1;
                   clasificacion[j][4] = (int) clasificacion[j][4] + 3;
               } else if (listaPartidos.get(i).getPerdedor().getNombre().equals(clasificacion[j][0])) {
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

    private static ArrayList<Equipo> listaEquipos() throws DbException {
        return EquipoDAO.listEquipos();
    }
}
