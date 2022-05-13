package com.imaleex.esportapp.Views;

import javax.swing.*;

public class UserView {
    private JTextField tfUserName;
    private JMenu jmUltimaJornada;
    private JMenu jmClasificacion;
    private JPanel jpUsuario;

    public static void main(String[] args) {
        JFrame frame = new JFrame("UserView");
        frame.setContentPane(new UserView().jpUsuario);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
