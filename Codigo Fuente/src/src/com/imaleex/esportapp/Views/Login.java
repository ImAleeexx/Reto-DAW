package com.imaleex.esportapp.Views;

import javax.swing.*;

public class Login {
    private JLabel lUsername;
    private JTextField tfUsername;
    private JLabel lPassword;
    private JPasswordField pfPassword;
    private JButton bLogin;
    private JPanel jpPrincipal;
    private JButton bExit;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new Login().jpPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }






}
