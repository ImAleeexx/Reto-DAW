package com.imaleex.esportapp.Db.Dao;

import com.imaleex.esportapp.Db.Db;
import com.imaleex.esportapp.Exceptions.DataNotFoundException;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Models.Personas.Dueno;

import java.sql.Connection;
import java.sql.SQLException;


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
            if (rs.next()) {
                persona.setId(rs.getInt("id"));
                persona.setDni(rs.getString("dni"));
                persona.setNombre(rs.getString("nombre"));
                persona.setTelefono(rs.getString("telefono"));
                persona.setEmail(rs.getString("email"));
                //TODO Falta meter datos de equipo y rol
            } else {
                throw new DataNotFoundException("El jugador no existe");
            }

        } catch (DbException | SQLException e) {
            e.printStackTrace();
        }
        return persona;
    }
    


}
