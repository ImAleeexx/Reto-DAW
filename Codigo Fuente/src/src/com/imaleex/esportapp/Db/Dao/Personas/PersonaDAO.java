package com.imaleex.esportapp.Db.Dao.Personas;

import com.imaleex.esportapp.Db.Db;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Models.Personas.Persona;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author Alex Cortes
 */
public class PersonaDAO {


    /**
     * Inserta una persona en la base de datos
     * @param persona Persona a insertar en la base de datos
     * @return Devuelve objeto persona con el id de la persona insertada
     * @throws DbException Si no se ha podido insertar la persona
     */
    //Crea una persona en la base de datos
    public static Persona createPersona(Persona persona) throws DbException {

        String sql = "INSERT INTO personas (nombre, dni, telefono) VALUES (?, ?, ?)";

        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getDni());
            stmt.setString(3, persona.getTelefono());
            // ejecutamos la consulta y guardamos el id en una variable
            int insertedId = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                persona.setId(rs.getInt(1));
            }
            return persona;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException("Error al crear la persona");
        }
    }

    /**
     * Actualiza una persona en la base de datos
     * @param persona Persona a actualizar en la base de datos
     * @throws DbException Si no se ha podido actualizar la persona o no existe
     */
    //Actualiza una persona en la base de datos
    public static void updatePersona(Persona persona) throws DbException {

        String sql = "UPDATE personas SET nombre = ?, dni = ?, telefono = ? WHERE id = ?";

        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getDni());
            stmt.setString(3, persona.getTelefono());
            stmt.setInt(4, persona.getId());
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                throw new DbException("No se ha actualizado ninguna persona");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException("Error al actualizar la persona");
        }
    }

    //Borra una persona en la base de datos

    /**
     * Borra una persona en la base de datos
     * @param persona Persona a borrar en la base de datos
     * @throws DbException Si no se ha podido borrar la persona o no existe
     */
    public static void deletePersona(Persona persona) throws DbException {

        String sql = "DELETE FROM personas WHERE id = ?";

        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, persona.getId());
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                throw new DbException("No se ha borrado ninguna persona");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException("Error al borrar la persona");
        }
    }


}
