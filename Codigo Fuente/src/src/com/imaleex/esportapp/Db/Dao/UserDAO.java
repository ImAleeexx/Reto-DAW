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



    //Editar el usuario

    public static void editUser(Usuario user) throws DbException {
        String sql = "UPDATE usuarios SET nombre = ?, password = ? WHERE id = ?";
        try {
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, user.getNombre());
            stmt.setString(2, user.getClave());
            stmt.setInt(3, user.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Error al editar el usuario");
        }
    }


    //Borrar el usuario

    public static void deleteUser(Usuario user) throws DbException {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try {
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, user.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Error al borrar el usuario");
        }
    }

    //Crear el usuario

    public static void createUser(Usuario user) throws DbException {
        String sql = "INSERT INTO usuarios (nombre, password) VALUES (?, ?)";
        try {
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, user.getNombre());
            stmt.setString(2, user.getClave());
            stmt.executeUpdate();
        }   catch (SQLException e) {
            throw new DbException("Error al crear el usuario");
        }
    }

}
