package com.imaleex.esportapp;

import com.imaleex.esportapp.Db.Db;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Utils.CryptoUtils;
import com.imaleex.esportapp.Views.Login;

import javax.swing.*;

public class Main {

    public static Db db;
    private static JFrame login;

    public static void main(String[] args) {
        initDbConnection();
        login();
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
    private static void login(){
        login = new JFrame("Login");
        login.setContentPane(new Login().getJPanel());
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login.setLocationRelativeTo(null);
        login.pack();
        login.setVisible(true);
    }


}
