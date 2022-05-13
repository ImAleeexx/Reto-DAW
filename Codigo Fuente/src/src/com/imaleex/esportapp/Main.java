package com.imaleex.esportapp;

import com.imaleex.esportapp.Db.Dao.UserDAO;
import com.imaleex.esportapp.Db.Db;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Exceptions.UserNotFoundException;
import com.imaleex.esportapp.Models.Users.Usuario;
import com.imaleex.esportapp.Utils.CryptoUtils;
import com.imaleex.esportapp.Utils.WindowUtils;
import com.imaleex.esportapp.Views.Login;
import sun.misc.MessageUtils;

import javax.swing.*;

import static com.imaleex.esportapp.Db.Dao.UserDAO.searchUsername;

public class Main {

    public static Db db;
    private static JFrame login;

    public static void main(String[] args) {
        initDbConnection();
        displayLoginModal();
    }
    private static void initDbConnection() {
        try {
        Db.setDbParams("esports", "ijG4ZHcn81QlxfOA", "esports", "server.imaleex.com");
        db = Db.getInstance(1);
            System.out.println("Conectado");
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
                        return true;
                    }
                } else {
                    throw new UserNotFoundException("El usuario no existe");
                }
            } catch (UserNotFoundException e) {
                WindowUtils.showErrorMessage(e.getMessage());
            }
        return false;
    }

    private static void displayLoginModal(){
        login = new JFrame("Login");
        login.setContentPane(new Login().getJPanel());
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login.setLocationRelativeTo(null);
        login.pack();
        login.setVisible(true);
    }


}
