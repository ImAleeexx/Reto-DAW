package com.imaleex.esportapp.Views;

import com.imaleex.esportapp.Main;

import javax.swing.*;

public class GestionEntrenador {
    private JTextField tfUsuario;
    private JMenuItem jmClasificacion;
    private JMenuItem jmSalir;
    private JMenu jmGestion;
    private JMenuItem jmiJugador;
    private JMenuItem jmiEquipo;
    private JMenuItem jmiDueno;
    private JMenuItem jmiUsuario;
    private JMenuItem jmUltimaJornada;
    private JLabel lDNI;
    private JTextField tfDNI;
    private JLabel lNombre;
    private JTextField tfEntrenador;
    private JLabel lTelefono;
    private JTextField tfTelefono;
    private JLabel lSueldo;
    private JTextField tfSueldo;
    private JButton bBuscar;
    private JButton bAnadir;
    private JPanel jpEscondido;
    private JButton bEliminar;
    private JButton bModificar;
    private JPanel jpEntrenador;


    public GestionEntrenador() {
        tfUsuario.setText(Main.user.getNombre());



    }



    public static void main(String[] args) {
        JFrame frame = new JFrame("GestionEntrenador");
        frame.setContentPane(new GestionEntrenador().jpEntrenador);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
