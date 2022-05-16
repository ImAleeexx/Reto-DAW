package com.imaleex.esportapp.Views;

import com.imaleex.esportapp.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestionUsuario{
    private JTextField tfUserName;
    private JPanel jpAdmin;
    private JMenuItem jmUltimaJornada;
    private JMenuItem jmClasificacion;
    private JMenuItem jmSalir;
    private JLabel lNombre;
    private JTextField tfUsuario;
    private JTextField tfContraseña;
    private JLabel lContrasena;
    private JLabel lTipoUsuario;
    private JComboBox cbTipoUsuario;
    private JMenu jmGestion;
    private JMenuItem jmiJugador;
    private JMenuItem jmiEquipo;
    private JMenuItem jmiDueno;
    private JMenuItem jmiUsuario;
    private JButton bEliminar;
    private JButton bModificar;
    private JButton bAnadir;
    private JButton bBuscar;
    private JPanel jpEscondido;


    public GestionUsuario() {
        tfUserName.setText(Main.user.getNombre());
        jpEscondido.setVisible(false);
        jmSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        bBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               jpEscondido.setVisible(true);
               bAnadir.setVisible(false);
               

            }
        });
    }


    public static void main() {
        JFrame frame = new JFrame("GestionUsuario");
        frame.setContentPane(new GestionUsuario().jpAdmin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        Main.closeLogin();
        frame.setVisible(true);



    }
}
