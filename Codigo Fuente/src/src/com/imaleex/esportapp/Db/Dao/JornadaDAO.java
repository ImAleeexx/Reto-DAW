package com.imaleex.esportapp.Db.Dao;

import com.imaleex.esportapp.Db.Db;
import com.imaleex.esportapp.Exceptions.DataNotFoundException;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Models.Jornada;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * <h1>DAO de objeto jornada</h1></h1>
 *
 * @author Alex Cortes
 * @author Aritz Castillo
 * @version 1.0
 */
public class JornadaDAO {
    /**
     * Metodo para buscar una jornada por su id
     * @param id id de la jornada
     * @return Jornada con el id dado
     * @throws DataNotFoundException si no se encuentra la jornada
     */
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
    /**
     * Metodo insertar una jornada
     * @param jornada jornada a insertar
     * @throws DataNotFoundException si se da un error al insertar la jornada
     */
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
            if (rs.next()) {
                jornada.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jornada;
    }
    /**
     * Metodo para actualizar una jornada
     * @param jornada jornada a actualizar
     * @throws DataNotFoundException si se da un error al actualizar la jornada
     */
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

    /**
     * Metodo para eliminar una jornada por su id
     * @param id id de la jornada
     * @throws DataNotFoundException si se da un error al eliminar la jornada
     */
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

    /**
     * Metodo para eliminar todas las jornadas
     * @throws DataNotFoundException si se da un error al eliminar todas las jornadas
     */
    public static void deleteAllJornadas() throws DbException {
        String sql = "DELETE FROM jornadas";
        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            //Ejecutamos el statement
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para obtener todas las jornadas
     * @return lista de jornadas
     * @throws DataNotFoundException si se da un error al obtener la lista de jornadas
     */
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
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return jornadas;
    }


}
