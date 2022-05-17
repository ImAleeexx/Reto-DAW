package com.imaleex.esportapp.Views;

import com.imaleex.esportapp.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminView{
    private JTextField tfUserName;
    private JPanel jpAdmin;
    private JMenuItem jmUltimaJornada;
    private JMenuItem jmClasificacion;
    private JMenuItem jmSalir;
    private JMenu jmGestion;
    private JMenuItem jmiJugador;
    private JMenuItem jmiEquipo;
    private JMenuItem jmiDueno;
    private JMenuItem jmiUsuario;


    public AdminView() {
        tfUserName.setText(Main.user.getNombre());

        jmSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        jmiUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               GestionUsuario.main();
            }
        });
        jmiDueno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestionDueno.main();
            }
        });
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
