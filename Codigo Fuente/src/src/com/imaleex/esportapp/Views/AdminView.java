package com.imaleex.esportapp.Views;

import com.imaleex.esportapp.Main;

import javax.swing.*;

public class AdminView{
    private JTextField tfUserName;
    private JMenu jmUltimaJornada;
    private JMenu jmClasificacion;
    private JPanel jpAdmin;


    public AdminView() {

        tfUserName.setText(Main.user.getNombre());

    }

    public JPanel getJPanel(){
        return jpAdmin;
    }

    public static void main() {
        JFrame frame = new JFrame("AdminView");
        frame.setContentPane(new AdminView().jpAdmin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        Main.closeLogin();
        frame.setVisible(true);



    }
}
