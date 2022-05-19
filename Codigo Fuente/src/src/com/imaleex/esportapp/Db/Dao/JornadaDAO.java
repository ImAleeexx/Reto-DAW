package com.imaleex.esportapp.Db.Dao;

import com.imaleex.esportapp.Db.Db;
import com.imaleex.esportapp.Exceptions.DataNotFoundException;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Models.Equipo;
import com.imaleex.esportapp.Models.Jornada;
import com.imaleex.esportapp.Models.Partido;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class JornadaDAO {
    public static Jornada searchJornada(int id) throws DataNotFoundException {

        String sql = "SELECT * FROM jornadas  WHERE id LIKE ? LIMIT 1";
        Jornada jornada = new Jornada();

        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            //Ejecutamos el statement
            java.sql.ResultSet rs = stmt.executeQuery();

            //Recorremos el resultado
            if (rs.next()) {
                jornada.setId(rs.getInt("id"));
                jornada.setFecha(rs.getDate("fecha").toLocalDate());
            } else {
                throw new DataNotFoundException("La jornada no existe");
            }

        } catch (DbException | SQLException e) {
            e.printStackTrace();
        }
        return jornada;
    }

    public static Jornada insertJornada(Jornada jornada) throws DbException {
        try {
            String sql = "INSERT INTO jornadas (fecha) VALUES (?)";
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            try {
            stmt.setDate(1, java.sql.Date.valueOf(jornada.getFecha()));
            } catch (NullPointerException e) {
                stmt.setNull(1, java.sql.Types.DATE);
            }

            //Ejecutamos el statement
            if (stmt.executeUpdate() != 1) {
                throw new DbException("Error al insertar la jornada");
            }
            //Recuperamos el id generado
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next() ) {
                jornada.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jornada;
    }

    public static void updateJornada(Jornada jornada) throws DbException {
        String sql = "UPDATE jornadas SET fecha = ? WHERE id = ?";
        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setDate(1, java.sql.Date.valueOf(jornada.getFecha()));
            stmt.setInt(2, jornada.getId());

            //Ejecutamos el statement
            int rows = stmt.executeUpdate();

            if (rows == 0) {
                throw new DbException("Error al actualizar la jornada");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteJornada(int id) throws DbException {
        String sql = "DELETE FROM jornadas WHERE id = ?";
        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            //Ejecutamos el statement
            int rows = stmt.executeUpdate();

            if (rows == 0) {
                throw new DbException("Error al eliminar la jornada");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Jornada> getJornadas() throws DbException {
        String sql = "SELECT * FROM jornadas";
        ArrayList<Jornada> jornadas = new ArrayList<>();

        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);

            //Ejecutamos el statement
            java.sql.ResultSet rs = stmt.executeQuery();

            //Recorremos el resultado
            while (rs.next()) {
                Jornada jornada = new Jornada();
                jornada.setId(rs.getInt("id"));
                jornada.setFecha(rs.getDate("fecha").toLocalDate());
                jornadas.add(jornada);
            }
        } catch (DbException | SQLException e) {
            e.printStackTrace();
        }
        return jornadas;
    }



    public static void generateMatchCalendar(){

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
        int numJornadas = (listaEquipos.size() -1);
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

}
