package com.imaleex.esportapp.Views;

import com.imaleex.esportapp.Main;

import javax.swing.*;

public class GestionJugador {
    private JTextField tfUsuario;
    private JMenuItem jmClasificacion;
    private JMenuItem jmSalir;
    private JMenu jmGestion;
    private JMenuItem jmiJugador;
    private JMenuItem jmiEquipo;
    private JMenuItem jmiDueno;
    private JMenuItem jmiUsuario;
    private JMenuItem jmiEntrenador;
    private JMenuItem jmUltimaJornada;
    private JLabel lDNI;
    private JTextField tfDNI;
    private JLabel lNombre;
    private JTextField tfJugador;
    private JLabel lSueldo;
    private JTextField tfSueldo;
    private JLabel lNickname;
    private JTextField tfNickname;
    private JLabel lTelefono;
    private JTextField tfTelefono;
    private JLabel lRol;
    private JComboBox cbRol;
    private JLabel lEquipo;
    private JComboBox cbEquipo;
    private JButton bBuscar;
    private JButton bAnadir;
    private JPanel jpEscondido;
    private JButton bEliminar;
    private JButton bModificar;
    private JPanel jpJugador;


    public GestionJugador(){
            tfUsuario.setText(Main.user.getNombre());
            llenarCb();

    }



    public static void main() {
        JFrame frame = new JFrame("GestionJugador");
        frame.setContentPane(new GestionJugador().jpJugador);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void llenarCb(){

    }
}
