package com.imaleex.esportapp.Views;

import com.imaleex.esportapp.Main;
import com.imaleex.esportapp.Utils.WindowUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    private JLabel lUsername;
    private JTextField tfUsername;
    private JLabel lPassword;
    private JPasswordField pfPassword;
    private JButton bLogin;
    private JPanel jpPrincipal;
    private JButton bExit;

    public Login() {
        bLogin.setOpaque(true);
        bLogin.setBorderPainted(false);
        bExit.setOpaque(true);
        bExit.setBorderPainted(false);
        bLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    comprobaciones(tfUsername.getText(), String.valueOf(pfPassword.getPassword()));
                } catch (NullPointerException ex) {}
            }
        });
        bExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public JPanel getJPanel() {
        return jpPrincipal;
    }

    public void comprobaciones(String username, String password) {
        if (!username.isEmpty() && !password.isEmpty()) {
            if (Main.login(username, password)) {
                WindowUtils.showInfoMessage("Login correcto");
                Main.mostrarVentana();
            } else {
                WindowUtils.showErrorMessage("Login invalido");
            }
        } else {
            WindowUtils.showErrorMessage("Algun campo esta vacio.");
        }

    }


}
