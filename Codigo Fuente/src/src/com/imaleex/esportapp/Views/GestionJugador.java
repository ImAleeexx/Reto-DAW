package com.imaleex.esportapp.Views;

import com.imaleex.esportapp.Controllers.AdminController;
import com.imaleex.esportapp.Exceptions.DataNotFoundException;
import com.imaleex.esportapp.Main;
import com.imaleex.esportapp.Models.Equipo;
import com.imaleex.esportapp.Models.Personas.Jugador;
import com.imaleex.esportapp.Models.Personas.Rol;
import com.imaleex.esportapp.Utils.Validator;
import com.imaleex.esportapp.Utils.WindowUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    private JComboBox<String> cbRol;
    private JLabel lEquipo;
    private JComboBox<Equipo> cbEquipo;
    private JButton bBuscar;
    private JButton bAnadir;
    private JPanel jpEscondido;
    private JButton bEliminar;
    private JButton bModificar;
    private JPanel jpJugador;

    private ArrayList<Equipo> equipos;


    public GestionJugador() {
        tfUsuario.setText(Main.user.getNombre());
        llenarCb();

        bBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tfDNI.getText().isEmpty()) {
                    try {
                        if (!Validator.checkDni(tfDNI.getText())) {
                            throw new DataNotFoundException("DNI incorrecto");
                        }
                        Jugador jugador = AdminController.buscarJugador(tfDNI.getText());
                        tfDNI.setText(jugador.getDni());
                        tfJugador.setText(jugador.getNombre());
                        tfSueldo.setText(String.valueOf(jugador.getSueldo()));
                        tfNickname.setText(jugador.getNickname());
                        tfTelefono.setText(jugador.getTelefono());


                        cbRol.setSelectedIndex(0);
                        cbEquipo.setSelectedIndex(0);
                        if (jugador.getEquipo() != null) {
                            cbEquipo.setSelectedItem(searchEquipoOnList(jugador.getEquipo().getId()));
                        }

                        if (jugador.getRol() != null) {
                            cbRol.setSelectedItem(jugador.getRol().getNombre());
                        }


                    } catch (DataNotFoundException ex) {
                        WindowUtils.showErrorMessage(ex.getMessage());
                    }
                }
            }
        });
    }


    public static void main() {
        JFrame frame = new JFrame("GestionJugador");
        frame.setContentPane(new GestionJugador().jpJugador);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void llenarCb() {

        cbEquipo.removeAllItems();
        equipos = AdminController.listaEquipos();
        cbEquipo.addItem(new Equipo());
        for (Equipo equipo : equipos) {
            cbEquipo.addItem(equipo);
        }
        cbRol.removeAllItems();
        cbRol.addItem("");
        Rol[] roles = Rol.values();
        for (Rol rol : roles) {
            cbRol.addItem(rol.getNombre());
        }
    }

    public Equipo searchEquipoOnList(int id) {
        for (Equipo equipo : equipos) {
            if (equipo.getId() == id) {
                return equipo;
            }
        }
        return null;
    }
}
