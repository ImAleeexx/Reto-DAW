package com.imaleex.esportapp.Views;

import com.imaleex.esportapp.Main;

import javax.swing.*;

public class GestionEquipo {
    private JTextField tfUsuario;
    private JMenuItem jmClasificacion;
    private JMenuItem jmSalir;
    private JMenu jmGestion;
    private JMenuItem jmiJugador;
    private JMenuItem jmiEquipo;
    private JMenuItem jmiDueno;
    private JMenuItem jmiUsuario;
    private JMenuItem jmUltimaJornada;
    private JLabel lNombre;
    private JTextField tfEquipo;
    private JLabel lEntrenador;
    private JLabel lAsistente;
    private JTextField tfEntrenador;
    private JTextField tfAsistente;
    private JLabel lDueño;
    private JTextField tfDueno;
    private JButton bBuscar;
    private JButton bAnadir;
    private JPanel jpEscondido;
    private JButton bEliminar;
    private JButton bModificar;
    private JPanel jpEquipo;

    public GestionEquipo() {
        tfUsuario.setText(Main.user.getNombre());


    }

        public static void main(String[] args) {
        JFrame frame = new JFrame("GestionEquipo");
        frame.setContentPane(new GestionEquipo().jpEquipo);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
