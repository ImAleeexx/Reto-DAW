package com.imaleex.esportapp.Db.Dao.Personas;

import com.imaleex.esportapp.Db.Db;
import com.imaleex.esportapp.Exceptions.DataNotFoundException;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Models.Equipo;
import com.imaleex.esportapp.Models.Personas.Jugador;
import com.imaleex.esportapp.Models.Personas.Rol;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

                try {
                    Equipo equipo = new Equipo();
                    equipo.setId(rs.getInt("id_equipo"));
                    persona.setEquipo(equipo);
                } catch (NullPointerException ignored) {
                }   //Si no tiene equipo, no hace nada
                try {
                    persona.setRol(Rol.valueOf(rs.getString("rol")));
                } catch (NullPointerException ignored) {
                }   //Si no tiene rol, no hace nada
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
            try {
                if (jugador.getEquipo().getJugadores().size() < 6) {
                    stmt.setObject(4, jugador.getEquipo().getId(), java.sql.Types.INTEGER);
                } else {
                    throw new DbException("El equipo tiene demasiados jugadores");
                }
            } catch (NullPointerException e) {
                stmt.setNull(4, java.sql.Types.INTEGER);
            }
            try {
                stmt.setString(5, jugador.getRol().name());
            } catch (NullPointerException e) {
                stmt.setNull(5, java.sql.Types.VARCHAR);
            }

            //Ejecutamos el statement
            stmt.executeUpdate();
            //Actualizamos la persona

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException("Error al insertar el jugador");
        }
    }

    public static void updateJugador(Jugador jugador) throws DbException {
        String sql = "UPDATE jugadores SET nickname = ?, sueldo = ?, id_equipo = ?, rol = ? WHERE id = ?";
        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, jugador.getNickname());
            stmt.setDouble(2, jugador.getSueldo());
            try {
                if (jugador.getEquipo().getJugadores().size() >= 6) {
                    stmt.setObject(4, jugador.getEquipo().getId(), java.sql.Types.INTEGER);
                } else {
                    throw new DbException("El equipo tiene demasiados jugadores");
                }
            } catch (NullPointerException e) {
                stmt.setNull(3, java.sql.Types.INTEGER);
            }
            try {
                stmt.setInt(5, jugador.getId());
            } catch (NullPointerException e) {
                stmt.setNull(5, java.sql.Types.INTEGER);
            }
            try {
                stmt.setString(4, jugador.getRol().name());
            } catch (NullPointerException e) {
                stmt.setNull(4, java.sql.Types.VARCHAR);
            }
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

    public static ArrayList<Jugador> listaJugadores() throws DbException {
        String sql = "SELECT p.dni, p.nombre, p.telefono, j.* FROM jugadores j, personas p  WHERE p.id = j.id";
        ArrayList<Jugador> jugadores = new ArrayList<>();
        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Jugador jugador = new Jugador();
                jugador.setId(rs.getInt("id"));
                jugador.setNombre(rs.getString("nombre"));
                jugador.setDni(rs.getString("dni"));
                jugador.setTelefono(rs.getString("telefono"));
                jugador.setNickname(rs.getString("nickname"));
                jugador.setSueldo(rs.getDouble("sueldo"));
                try {
                jugador.setRol(Rol.valueOf(rs.getString("rol")));
                } catch (NullPointerException e) {}
                try {
                Equipo equipo = new Equipo();
                equipo.setId(rs.getInt("id_equipo"));
                jugador.setEquipo(equipo);
                } catch (NullPointerException e) {}
                jugadores.add(jugador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException("Error al listar los jugadores");
        }
        return jugadores;
    }
}

