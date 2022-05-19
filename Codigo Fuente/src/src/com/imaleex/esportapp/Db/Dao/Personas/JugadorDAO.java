package com.imaleex.esportapp.Db.Dao.Personas;

import com.imaleex.esportapp.Db.Db;
import com.imaleex.esportapp.Exceptions.DataNotFoundException;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Models.Equipo;
import com.imaleex.esportapp.Models.Personas.Jugador;
import com.imaleex.esportapp.Models.Personas.Rol;

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
                Equipo equipo = new Equipo();
                equipo.setId(rs.getInt("id_equipo"));
                persona.setEquipo(equipo);
                persona.setRol(Rol.valueOf(rs.getString("rol")));
            } else {
                throw new DataNotFoundException("El jugador no existe");
            }

        } catch (DbException | SQLException e) {
            e.printStackTrace();
        }
        return persona;
    }

    public static void insertJugador(Jugador jugador) throws DbException {
        String sql = "INSERT INTO jugadores (id, nickname, sueldo, id_equipo, rol) VALUES (?, ?, ?, ?, ?)";
        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, jugador.getId());
            stmt.setString(2, jugador.getNickname());
            stmt.setDouble(3, jugador.getSueldo());
            stmt.setInt(4, jugador.getEquipo().getId());
            stmt.setString(5, jugador.getRol().name());

            //Ejecutamos el statement
            stmt.executeUpdate();
            //Actualizamos la persona

        } catch ( SQLException e) {
            e.printStackTrace();
            throw new DbException("Error al insertar el jugador");
        }
    }

    public static void updateJugador(Jugador jugador) throws DbException {
        String sql = "UPDATE jugadores SET nickname = ?, sueldo = ?, id_equipo = ?, rol = ? WHERE id = ?";
        try {
            //Instanciamos la conexion y creamos el statement
            //TODO Valores nulos en insert y update
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, jugador.getNickname());
            stmt.setDouble(2, jugador.getSueldo());
            stmt.setInt(3, jugador.getEquipo().getId());
            stmt.setInt(5, jugador.getId());
            stmt.setString(4, jugador.getRol().name());
            System.out.println(jugador.getRol().name());
            //Ejecutamos el statement
            int rows = stmt.executeUpdate();
            if (rows > 0) {
            //Actualizamos la persona
            PersonaDAO.updatePersona(jugador);
            } else {
                throw new DbException("No se ha podido actualizar el jugador");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException("Error al actualizar el jugador");
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
            int rows = stmt.executeUpdate();
            if (rows > 0) {
            PersonaDAO.deletePersona(jugador);
            } else {
                throw new DbException("No se ha podido borrar el jugador");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException("Error al borrar el jugador");
        }
    }

}

