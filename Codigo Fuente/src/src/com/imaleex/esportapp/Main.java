package com.imaleex.esportapp;

import com.imaleex.esportapp.Controllers.AdminController;
import com.imaleex.esportapp.Db.Dao.EquipoDAO;
import com.imaleex.esportapp.Db.Dao.JornadaDAO;
import com.imaleex.esportapp.Db.Db;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Exceptions.UserNotFoundException;
import com.imaleex.esportapp.Models.Users.Usuario;
import com.imaleex.esportapp.Utils.CryptoUtils;
import com.imaleex.esportapp.Views.AdminView;
import com.imaleex.esportapp.Views.Login;
import com.imaleex.esportapp.Views.UserView;

import javax.swing.*;
import java.io.Console;

import static com.imaleex.esportapp.Db.Dao.UserDAO.searchUsername;

public class Main {

    public static Db db;
    public static Usuario user;
    private static JFrame login;
    private static JFrame adminV;
    private static JFrame userV;

    public static void main(String[] args) {
        initDbConnection();
        displayLoginModal();

        //JornadaDAO.generateMatchCalendar();

    }

    private static void initDbConnection() {
        try {
            //Get password from enviroment variables
            String password = System.getenv("DB_PASSWORD");
            if (password == null) {
                throw new DbException("No se encontró la contraseña de la base de datos en la variable de entorno DB_PASSWORD");
            }
            Db.setDbParams("esports", password, "esports", "server.imaleex.com");
            db = Db.getInstance(1);
            System.out.println("Conectado a la base de datos");
        } catch (DbException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }




    /*---------------------------------------------------------------------------------------------------------------------*/
    /* Login */
    public static boolean login(String username, String password) {
        Usuario usuario = null;
        try {
            usuario = searchUsername(username);
            if (usuario != null) {
                if (CryptoUtils.checkHash(password, usuario.getClave())) {
                    user = usuario;
                    return true;
                }
            } else {
                throw new UserNotFoundException("El usuario no existe");
            }
        } catch (UserNotFoundException ignored) {
        }
        return false;
    }

    public static void mostrarVentana() {
        if (user.checkAdmin()) {
            AdminView.main();
        } else {
            UserView.main();
        }
    }

    private static void displayLoginModal() {
        login = new JFrame("Login");
        login.setContentPane(new Login().getJPanel());
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login.setLocationRelativeTo(null);
        login.pack();
        login.setVisible(true);
    }

    public static void closeLogin() {
        login.dispose();
    }

}
