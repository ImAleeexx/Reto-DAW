package com.imaleex.esportapp.Views;

import com.imaleex.esportapp.Controllers.AdminController;
import com.imaleex.esportapp.Db.Dao.Personas.DuenoDAO;
import com.imaleex.esportapp.Db.Dao.Personas.EntrenadorDAO;
import com.imaleex.esportapp.Db.Dao.Personas.PersonaDAO;
import com.imaleex.esportapp.Exceptions.DataNotFoundException;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Main;
import com.imaleex.esportapp.Models.Personas.Dueno;
import com.imaleex.esportapp.Models.Personas.Entrenador;
import com.imaleex.esportapp.Utils.Faker;
import com.imaleex.esportapp.Utils.WindowUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static com.imaleex.esportapp.Utils.Validator.checkDni;

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
    private JMenuItem jmiEntrenador;


    public GestionEntrenador() {
        tfUsuario.setText(Main.user.getNombre());
        jpEscondido.setVisible(false);

        jmSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });

        jmiUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame)SwingUtilities.getRoot(jpEntrenador);
                frame.dispose();
                GestionUsuario.main();
            }
        });
        jmiDueno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame)SwingUtilities.getRoot(jpEntrenador);
                frame.dispose();
                GestionDueno.main();
            }
        });
        jmiEquipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame)SwingUtilities.getRoot(jpEntrenador);
                frame.dispose();
                GestionEquipo.main();
            }
        });

        jmiJugador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame)SwingUtilities.getRoot(jpEntrenador);
                frame.dispose();
                GestionJugador.main();
            }
        });

        bBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jpEscondido.setVisible(true);
                bAnadir.setVisible(false);
                try {
                    if (checkDni(tfDNI.getText())) {
                        Entrenador entrenador = AdminController.buscarEntrenadorDni(tfDNI.getText());
                        tfDNI.setBackground(Color.GREEN);
                        tfEntrenador.setText(entrenador.getNombre());
                        tfTelefono.setText(entrenador.getTelefono());
                        tfSueldo.setText(String.valueOf(entrenador.getSueldo()));

                    }else{
                        throw new DataNotFoundException("DNI no valido");
                    }
                } catch (DataNotFoundException |DbException ex) {
                    WindowUtils.showErrorMessage(ex.getMessage());
                    tfDNI.setText("");
                    jpEscondido.setVisible(false);
                    bAnadir.setVisible(true);
                    tfDNI.setBackground(Color.RED);
                }
            }
        });
        bEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkDni(tfDNI.getText())) {
                    try {
                        Entrenador entrenador = AdminController.buscarEntrenadorDni(tfDNI.getText());
                        EntrenadorDAO.deleteEntrenador(entrenador);
                        WindowUtils.showInfoMessage("Entrenador eliminado");
                        tfDNI.setText("");
                        tfEntrenador.setText("");
                        tfSueldo.setText("");
                        tfTelefono.setText("");
                        jpEscondido.setVisible(false);
                        bAnadir.setVisible(true);
                        tfDNI.setBackground(Color.white);
                    } catch (DbException | DataNotFoundException ex) {
                        WindowUtils.showErrorMessage(ex.getMessage());
                    }
                }else{
                    WindowUtils.showErrorMessage("DNI no valido");
                }
            }});
        bModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkDni(tfDNI.getText())) {
                    try {
                        Entrenador entrenador = AdminController.buscarEntrenadorDni(tfDNI.getText());
                        entrenador.setDni(tfDNI.getText());
                        entrenador.setNombre(tfEntrenador.getText());
                        entrenador.setSueldo(Double.parseDouble(tfSueldo.getText()));
                        entrenador.setTelefono(tfTelefono.getText());
                        System.out.println(entrenador);
                        EntrenadorDAO.updateEntrenador(entrenador);
                        WindowUtils.showInfoMessage("Entrenador modificado");
                    } catch (DataNotFoundException | DbException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }});
        bAnadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkDni(tfDNI.getText())) {
                    try {
                        Entrenador entrenador = new Entrenador();
                        entrenador.setDni(tfDNI.getText());
                        entrenador.setNombre(tfEntrenador.getText());
                        entrenador.setSueldo(Double.parseDouble(tfSueldo.getText()));
                        entrenador.setTelefono(tfTelefono.getText());
                        entrenador = (Entrenador) PersonaDAO.createPersona(entrenador);
                        System.out.println(entrenador.getId());
                        EntrenadorDAO.insertEntrenador(entrenador);
                        WindowUtils.showInfoMessage("Entrenador añadido");
                        tfDNI.setText("");
                        tfEntrenador.setText("");
                        tfSueldo.setText("");
                        tfTelefono.setText("");
                        jpEscondido.setVisible(false);
                        bAnadir.setVisible(true);
                        tfDNI.setBackground(Color.white);
                    } catch (DbException ex) {
                        WindowUtils.showErrorMessage(ex.getMessage());
                    }
                }else{
                    WindowUtils.showErrorMessage("DNI no valido");
                }
            }
        });
    }
    public static void main() {
        JFrame frame = new JFrame("GestionEntrenador");
        frame.setContentPane(new GestionEntrenador().jpEntrenador);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
