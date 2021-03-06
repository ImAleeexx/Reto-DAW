package com.imaleex.esportapp.Db.Dao;

import com.imaleex.esportapp.Db.Db;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Exceptions.UserNotFoundException;
import com.imaleex.esportapp.Models.Users.Usuario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Alex Cortes
 */
public class UserDAO {


    /**
     * Busca un usuario por su nombre
     * @param username nombre de usuario
     * @return Usuario encontrado
     * @throws UserNotFoundException si no se encuentra el usuario en la base de datos o si hay un error en la base de datos
     */
    public static Usuario searchUsername(String username) throws UserNotFoundException {

        String sql = "SELECT * FROM usuarios WHERE nombre = ?";
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
                if (rs.getString("nombre").equals(username)) {
                    user = new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("password"), rs.getInt("type"));
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

    /**
     * Edita un usuario en la base de datos
     * @param user Usuario a editar
     * @throws DbException si hay un error en la base de datos o si el usuario no existe
     */
    public static void editUser(Usuario user) throws DbException {
        String sql = "UPDATE usuarios SET nombre = ?, password = ?, type = ? WHERE id = ?";
        try {
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, user.getNombre());
            stmt.setString(2, user.getClave());
            stmt.setInt(3, user.getType());
            stmt.setInt(4, user.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Error al editar el usuario");
        }
    }


    //Borrar el usuario

    /**
     * Borra un usuario de la base de datos
     * @param user Usuario a borrar
     * @throws DbException si hay un error en la base de datos o si el usuario no existe
     */
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

    /**
     * Borra un usuario de la base de datos por su nombre
     * @param name nombre del usuario
     * @throws DbException si hay un error en la base de datos o si el usuario no existe
     */
    public static void deleteUserByName(String name) throws DbException {
        String sql = "DELETE FROM usuarios WHERE nombre = ?";
        try {
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Error al borrar el usuario");
        }
    }
    //Crear el usuario

    /**
     * Crea un usuario en la base de datos
     * @param user Usuario a crear en la base de datos
     * @return el usuario creado en la base de datos con el id asignado
     * @throws DbException si hay un error en la base de datos
     */
    public static Usuario createUser(Usuario user) throws DbException {
        String sql = "INSERT INTO usuarios (nombre, password, type) VALUES (?, ?, ? )";
        try {
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getNombre());
            stmt.setString(2, user.getClave());
            stmt.setInt(3, user.getType());
            int id = stmt.executeUpdate();
            user.setId(id);
            return user;


        } catch (SQLException e) {
            e.printStackTrace();
            throw new DbException("Error al crear el usuario");
        }
    }

    /**
     * Obtiene una lista de usuarios de la base de datos
     * @return lista de usuarios
     * @throws DbException si hay un error en la base de datos
     */
    public static ArrayList<Usuario> listUsers() throws DbException {
        String sql = "SELECT * FROM usuarios";
        ArrayList<Usuario> users = new ArrayList<>();
        try {
            Connection con = Db.getConnection(1);
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                users.add(new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("password"), rs.getInt("type")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

}
