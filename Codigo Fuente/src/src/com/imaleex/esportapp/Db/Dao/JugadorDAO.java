package com.imaleex.esportapp.Db.Dao;

import com.imaleex.esportapp.Db.Db;
import com.imaleex.esportapp.Exceptions.DataNotFoundException;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Models.Personas.Jugador;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Alex Cortes
 */
public class JugadorDAO {
    public static Jugador searchJugador(String dni) throws DataNotFoundException {

        String sql = "SELECT p.dni, p.nombre, p.telefono, j.* FROM jugadores j, personas p  WHERE p.dni = ? AND p.id = j.id";
        Jugador persona = new Jugador();

        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, dni);

            //Ejecutamos el statement
            java.sql.ResultSet rs = stmt.executeQuery();

            //Recorremos el resultado
            if (rs.next()) {
                persona.setId(rs.getInt("id"));
                persona.setDni(rs.getString("dni"));
                persona.setNombre(rs.getString("nombre"));
                persona.setTelefono(rs.getString("telefono"));
                persona.setNickname(rs.getString("nickname"));
                persona.setSueldo(rs.getDouble("sueldo"));
                //TODO Falta meter datos de equipo y rol
            } else {
                throw new DataNotFoundException("El jugador no existe");
            }

        } catch (DbException | SQLException e) {
            e.printStackTrace();
        }
        return persona;
    }

    public static void insertJugador(Jugador jugador) throws DbException {
        String sql = "INSERT INTO jugadores (id, nickname, sueldo) VALUES (?, ?, ?)";
        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, jugador.getId());
            stmt.setString(2, jugador.getNickname());
            stmt.setDouble(3, jugador.getSueldo());
            //Ejecutamos el statement
            stmt.executeUpdate();
            //Actualizamos la persona
            PersonaDAO.createPersona(jugador);
        } catch (DbException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateJugador(Jugador jugador) throws DbException {
        String sql = "UPDATE jugadores SET nickname = ?, sueldo = ? WHERE id = ?";
        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, jugador.getNickname());
            stmt.setDouble(2, jugador.getSueldo());
            stmt.setInt(3, jugador.getId());
            //Ejecutamos el statement
            stmt.executeUpdate();
            //Actualizamos la persona
            PersonaDAO.updatePersona(jugador);

        } catch (DbException | SQLException e) {
            e.printStackTrace();
        }
    }

    //method to delete a jugador
    public static void deleteJugador(Jugador jugador) throws DbException {
        String sql = "DELETE FROM jugadores WHERE id = ?";
        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, jugador.getId());
            //Borramos la persona asociada a la id
            stmt.executeUpdate();
            PersonaDAO.deletePersona(jugador);
        } catch (DbException | SQLException e) {
            e.printStackTrace();
        }
    }

}

