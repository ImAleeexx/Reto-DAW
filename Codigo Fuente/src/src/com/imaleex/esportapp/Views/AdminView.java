package com.imaleex.esportapp.Views;

import com.imaleex.esportapp.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminView {
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
    private JMenuItem jmiEntrenador;
    private JButton bGenerarCalendario;
    private JButton bGenerarJugadores;


    public AdminView() {
        tfUserName.setText(Main.user.getNombre());

        jmSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jmiUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getRoot(jpAdmin);
                frame.dispose();
                GestionUsuario.main();
            }
        });
        jmiDueno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getRoot(jpAdmin);
                frame.dispose();
                GestionDueno.main();
            }
        });
        jmiEquipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getRoot(jpAdmin);
                frame.dispose();
                GestionEquipo.main();
            }
        });
        jmiEntrenador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getRoot(jpAdmin);
                frame.dispose();
                GestionEntrenador.main();
            }
        });

        jmiJugador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getRoot(jpAdmin);
                frame.dispose();
                GestionJugador.main();
            }
        });

        jmUltimaJornada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getRoot(jpAdmin);
                frame.dispose();
                VerJornada.main();
            }
        });
        jmClasificacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getRoot(jpAdmin);
                frame.dispose();
                VerClasificacion.main();
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
