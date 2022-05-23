package com.imaleex.esportapp.Db.Dao.Personas;

import com.imaleex.esportapp.Db.Db;
import com.imaleex.esportapp.Exceptions.DataNotFoundException;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Models.Personas.Dueno;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * @author Alex Cortes
 */
public class DuenoDAO {

    /**
     * Busca un dueno por su dni
     * @param dni
     * @return Dueno encontrado
     * @throws DataNotFoundException
     */
    //Buscar un dueno por su dni
    public static Dueno searchDueno(String dni) throws DataNotFoundException {

        String sql = "SELECT p.dni, p.nombre, p.telefono, d.* FROM duenos d, personas p  WHERE p.dni = ? AND p.id = d.id";
        Dueno persona = new Dueno();

        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, dni);

            //Ejecutamos el statement
            java.sql.ResultSet rs = stmt.executeQuery();

            //Recorremos el resultado
            loadElement(persona, rs);

        } catch (DbException | SQLException e) {
            e.printStackTrace();
        }
        return persona;
    }


    /**
     * Busca todos los duenos
     * @return Lista de todos los duenos
     * @throws DbException si hay un error en la conexion o en la consulta
     */
    public static ArrayList<Dueno> listDuenos() throws DbException {
        String sql = "SELECT p.dni, p.nombre, p.telefono, d.* FROM duenos d, personas p  WHERE p.id = d.id";
        ArrayList<Dueno> duenos = new ArrayList<>();

        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);

            //Ejecutamos el statement
            java.sql.ResultSet rs = stmt.executeQuery();

            //Recorremos el resultado
            while (rs.next()) {
                Dueno dueno = new Dueno();
                dueno.setId(rs.getInt("id"));
                dueno.setDni(rs.getString("dni"));
                dueno.setNombre(rs.getString("nombre"));
                dueno.setTelefono(rs.getString("telefono"));
                dueno.setEmail(rs.getString("email"));
                duenos.add(dueno);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return duenos;
    }


    /**
     * Busca un dueno por su id
     * @param id  id del dueno
     * @return
     * @throws DataNotFoundException
     */
    public static Dueno searchDuenoById(int id) throws DataNotFoundException {

        String sql = "SELECT p.dni, p.nombre, p.telefono, d.* FROM duenos d, personas p  WHERE p.id = ? AND p.id = d.id";
        Dueno persona = new Dueno();

        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            //Ejecutamos el statement
            java.sql.ResultSet rs = stmt.executeQuery();

            //Recorremos el resultado
            loadElement(persona, rs);

        } catch (DbException | SQLException e) {
            e.printStackTrace();
        }
        return persona;
    }

    /**
     * Carga un objeto de tipo Dueno con los datos de un ResultSet
     * @param persona
     * @param rs
     * @throws SQLException
     * @throws DataNotFoundException
     */
    private static void loadElement(Dueno persona, ResultSet rs) throws SQLException, DataNotFoundException {
        if (rs.next()) {
            persona.setId(rs.getInt("id"));
            persona.setDni(rs.getString("dni"));
            persona.setNombre(rs.getString("nombre"));
            persona.setTelefono(rs.getString("telefono"));
            persona.setEmail(rs.getString("email"));
        } else {
            throw new DataNotFoundException("El dueño no existe");
        }
    }

    /**
     * Inserta un dueno en la base de datos
     * @param dueno
     * @throws DbException Si hay algun error en la conexion o en la consulta
     */
    public static void insertDueno(Dueno dueno) throws DbException {
        String sql = "INSERT INTO duenos (id, email) VALUES (?, ?)";
        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, dueno.getId());
            stmt.setString(2, dueno.getEmail());
            //Ejecutamos el statement
            stmt.executeUpdate();
            //Actualizamos la persona

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    /**
     * Actualiza un dueno en la base de datos
     * @param dueno
     * @throws DbException Si hay algun error en la conexion o en la consulta
     */
    public static void updateDueno(Dueno dueno) throws DbException {
        String sql = "UPDATE duenos SET email = ? WHERE id = ?";
        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, dueno.getEmail());
            stmt.setInt(2, dueno.getId());
            //Ejecutamos el statement
            stmt.executeUpdate();
            //Actualizamos la persona
            PersonaDAO.updatePersona(dueno);

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    //method to delete a jugador
    /**
     * Elimina un dueno de la base de datos
     * @param dueno
     * @throws DbException Si hay algun error en la conexion o en la consulta
     */
    public static void deleteDueno(Dueno dueno) throws DbException {
        String sql = "DELETE FROM duenos WHERE id = ?";
        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, dueno.getId());
            //Borramos la persona asociada a la id
            stmt.executeUpdate();
            PersonaDAO.deletePersona(dueno);
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }


}
