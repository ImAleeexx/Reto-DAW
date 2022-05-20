package com.imaleex.esportapp.Db.Dao.Personas;

import com.imaleex.esportapp.Db.Db;
import com.imaleex.esportapp.Exceptions.DataNotFoundException;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Models.Personas.Dueno;
import com.imaleex.esportapp.Models.Personas.Entrenador;
import com.imaleex.esportapp.Utils.WindowUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * @author Alex Cortes
 */
public class DuenoDAO {

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

        } catch (DbException | SQLException e) {
            e.printStackTrace();
        }
    }

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

        } catch (DbException | SQLException e) {
            e.printStackTrace();
        }
    }

    //method to delete a jugador
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
        } catch (DbException | SQLException e) {
            e.printStackTrace();
        }
    }



}
