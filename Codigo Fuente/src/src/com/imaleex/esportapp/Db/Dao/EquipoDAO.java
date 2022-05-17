package com.imaleex.esportapp.Db.Dao;

import com.imaleex.esportapp.Db.Db;
import com.imaleex.esportapp.Exceptions.DataNotFoundException;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Models.Equipo;
import com.imaleex.esportapp.Models.Personas.Dueno;
import com.imaleex.esportapp.Models.Personas.Entrenador;
import com.imaleex.esportapp.Utils.DaoUtils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Alex Cortes
 */
public class EquipoDAO {


    //Metodo para buscar un equipo
    public static Equipo searchEquipo(String nombreEquipo) throws DataNotFoundException {

        String sql = "SELECT * FROM equipos  WHERE nombre LIKE ? LIMIT 1";
        Equipo equipo = new Equipo();

        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, '%' + nombreEquipo + '%');

            //Ejecutamos el statement
            java.sql.ResultSet rs = stmt.executeQuery();

            //Recorremos el resultado
            if (rs.next()) {
                equipo.setId(rs.getInt("id"));
                equipo.setNombre(rs.getString("nombre"));
                if (DaoUtils.checkNullableInteger(rs, "entrenador")){
                    equipo.setEntrenador(new Entrenador(rs.getInt("entrenador")));
                }
                if (DaoUtils.checkNullableInteger(rs, "asistente")) {
                    equipo.setEntrenadorAsistente(new Entrenador(rs.getInt("asistente")));
                }
                if (DaoUtils.checkNullableInteger(rs, "dueno")){
                    equipo.setDueno(new Dueno(rs.getInt("dueno")));
                }

            } else {
                throw new DataNotFoundException("El equipo no existe");
            }


        } catch (DbException | SQLException e) {
            e.printStackTrace();
        } return equipo;
    }

    public static Equipo insertEquipo(Equipo equipo) throws DbException {
        String sql = "INSERT INTO equipos (nombre, entrenador, asistente, dueno) VALUES (?, ?, ?, ?)";
        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, equipo.getNombre());
            stmt.setInt(2, equipo.getEntrenador().getId());
            stmt.setInt(3, equipo.getEntrenadorAsistente().getId());
            stmt.setInt(4, equipo.getDueno().getId());
            //Ejecutamos el statement
            int id = stmt.executeUpdate();
            //Actualizamos id
            equipo.setId(id);
            return equipo;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException("Error al insertar el equipo");
        }
    }


    public static void updateEquipo(Equipo equipo) throws DbException {
        String sql = "UPDATE equipos SET nombre = ?, entrenador = ?, asistente = ?, dueno = ? WHERE id = ?";
        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, equipo.getNombre());
            stmt.setInt(2, equipo.getEntrenador().getId());
            stmt.setInt(3, equipo.getEntrenadorAsistente().getId());
            stmt.setInt(4, equipo.getDueno().getId());
            stmt.setInt(5, equipo.getId());
            //Ejecutamos el statement
            stmt.executeUpdate();
            //Actualizamos la persona

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException("Error al actualizar el equipo");
        }
    }

    public static void deleteEquipo(Equipo equipo) throws DbException {
        String sql = "DELETE FROM equipos WHERE id = ?";
        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, equipo.getId());
            //Ejecutamos el statement
            stmt.executeUpdate();
            //Actualizamos la persona

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException("Error al borrar el equipo");
        }
    }
}
