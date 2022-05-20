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

    public static void deleteAllJornadas() throws DbException {
        String sql = "DELETE FROM jornadas";
        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            //Ejecutamos el statement
            int rows = stmt.executeUpdate();
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





}
