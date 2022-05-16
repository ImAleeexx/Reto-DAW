package com.imaleex.esportapp.Db.Dao.Personas;

import com.imaleex.esportapp.Db.Db;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Models.Personas.Persona;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * @author Alex Cortes
 */
public class PersonaDAO {


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
            persona.setId(insertedId);
            return persona;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException("Error al crear la persona");
        }
    }

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
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException("Error al actualizar la persona");
        }
    }

    //Borra una persona en la base de datos
    public static void deletePersona(Persona persona) throws DbException {

        String sql = "DELETE FROM personas WHERE id = ?";

        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, persona.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException("Error al borrar la persona");
        }
    }


}
