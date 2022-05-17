package com.imaleex.esportapp.Db.Dao.Personas;

import com.imaleex.esportapp.Db.Db;
import com.imaleex.esportapp.Exceptions.DataNotFoundException;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Models.Personas.Entrenador;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * @author Alex Cortes
 */
public class EntrenadorDAO {

    //Buscar un entrenador por su dni
    public static Entrenador searchEntrenador(int id) throws DataNotFoundException {

        String sql = "SELECT p.dni, p.nombre, p.telefono, e.* FROM entrenadores e, personas p  WHERE p.id = ? AND p.id = e.id";
        Entrenador persona = new Entrenador();

        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            //Ejecutamos el statement
            java.sql.ResultSet rs = stmt.executeQuery();

            //Recorremos el resultado
            if (rs.next()) {
                persona.setId(rs.getInt("id"));
                persona.setDni(rs.getString("dni"));
                persona.setNombre(rs.getString("nombre"));
                persona.setTelefono(rs.getString("telefono"));
                persona.setSueldo(rs.getDouble("sueldo"));
            } else {
                throw new DataNotFoundException("El entrenador no existe");
            }

        } catch (DbException | SQLException e) {
            e.printStackTrace();
        }
        return persona;
    }


    public static void insertEntrenador(Entrenador entrenador) throws DbException {
        String sql = "INSERT INTO entrenadores (id, sueldo) VALUES (?, ?)";
        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, entrenador.getId());
            stmt.setDouble(2, entrenador.getSueldo());
            //Ejecutamos el statement
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException("Error al insertar el entrenador");
        }
    }

    public static void updateEntrenador(Entrenador entrenador) throws DbException {
        String sql = "UPDATE entrenadores SET sueldo = ? WHERE id = ?";
        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setDouble(1, entrenador.getSueldo());
            stmt.setInt(2, entrenador.getId());
            //Ejecutamos el statement
            stmt.executeUpdate();
            //Actualizamos la persona
            PersonaDAO.updatePersona(entrenador);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException("Error al actualizar el entrenador");
        }
    }

    //method to delete a entrenador
    public static void deleteEntrenador(Entrenador entrenador) throws DbException {
        String sql = "DELETE FROM entrenadores WHERE id = ?";
        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, entrenador.getId());
            //Borramos la persona asociada a la id
            stmt.executeUpdate();
            PersonaDAO.deletePersona(entrenador);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException("Error al borrar el entrenador");
        }
    }

    //Buscar un entrenador por su nombre
    public static Entrenador searchEntrendorByNombre(String nombre) throws DbException {
        Entrenador entrenador = new Entrenador();
        String sql = "SELECT * FROM entrenadores WHERE nombre = ?";
        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nombre);
            //Ejecutamos el statement
            java.sql.ResultSet rs = stmt.executeQuery();
            //Recorremos el resultado
            if (rs.next()) {
                entrenador.setId(rs.getInt("id"));
                entrenador.setNombre(rs.getString("nombre"));
                entrenador.setSueldo(rs.getDouble("sueldo"));
            } else {
                throw new DataNotFoundException("El entrenador no existe");
            }
        } catch (SQLException | DataNotFoundException e) {
            e.printStackTrace();
            throw new DbException("Error al buscar el entrenador");
        }
        return entrenador;
    }
}
