package com.imaleex.esportapp.Db.Dao;

import com.imaleex.esportapp.Db.Db;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Exceptions.UserNotFoundException;
import com.imaleex.esportapp.Models.Users.Usuario;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Alex Cortes
 */
public class UserDAO {


    public static Usuario searchUsername(String username) throws UserNotFoundException {

        String sql = "SELECT * FROM usuarios WHERE username = ?";
        Usuario user = null;

        try {
            //Instanciamos la conexion y creamos el statement
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, username);

            //Ejecutamos el statement
            java.sql.ResultSet rs = stmt.executeQuery();

            //Recorremos el resultado
            if (rs.next()) {
                //checkeamos si el usuario existe
                if (rs.getString("username").equals(username)) {
                    user = new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("password"));
                }
            } else {
                throw new UserNotFoundException("El usuario no existe");
            }

        } catch (DbException | SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
