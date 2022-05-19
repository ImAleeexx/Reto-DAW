package com.imaleex.esportapp.Views;

import com.imaleex.esportapp.Controllers.AdminController;
import com.imaleex.esportapp.Db.Dao.Personas.DuenoDAO;
import com.imaleex.esportapp.Db.Dao.Personas.PersonaDAO;
import com.imaleex.esportapp.Exceptions.DataNotFoundException;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Main;
import com.imaleex.esportapp.Models.Personas.Dueno;
import com.imaleex.esportapp.Utils.Faker;
import com.imaleex.esportapp.Utils.WindowUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static com.imaleex.esportapp.Utils.Validator.checkDni;

public class GestionDueno {


    private JTextField tfUsuario;
    private JMenuItem jmUltimaJornada;
    private JMenuItem jmClasificacion;
    private JMenuItem jmSalir;
    private JMenu jmGestion;
    private JMenuItem jmiJugador;
    private JMenuItem jmiEquipo;
    private JMenuItem jmiDueno;
    private JMenuItem jmiUsuario;
    private JLabel lNombre;
    private JTextField tfDueno;
    private JLabel lEmail;
    private JTextField tfEmail;
    private JButton bAnadir;
    private JButton bBuscar;
    private JPanel jpEscondido;
    private JButton bEliminar;
    private JButton bModificar;
    private JPanel jpDueno;
    private JTextField tfDNI;
    private JLabel lDNI;
    private JLabel lTelefono;
    private JTextField tfTelefono;
    private JMenuItem jmiEntrenador;


    public GestionDueno() {
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
                JFrame frame = (JFrame)SwingUtilities.getRoot(jpDueno);
                frame.dispose();
                GestionUsuario.main();
            }
        });
        jmiEquipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame)SwingUtilities.getRoot(jpDueno);
                frame.dispose();
                GestionEquipo.main();
            }
        });
        jmiEntrenador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame)SwingUtilities.getRoot(jpDueno);
                frame.dispose();
                GestionEntrenador.main();
            }
        });

        jmiJugador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame)SwingUtilities.getRoot(jpDueno);
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
                    Dueno dueno = AdminController.buscarDueno(tfDNI.getText());

                    tfDNI.setBackground(Color.GREEN);
                    tfDueno.setText(dueno.getNombre());
                    tfEmail.setText(dueno.getEmail());
                    tfTelefono.setText(dueno.getTelefono());
                }else{
                        throw new DataNotFoundException("DNI no valido");
                    }
                } catch (DataNotFoundException ex) {
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
                    Dueno dueno = AdminController.buscarDueno(tfDNI.getText());
                    DuenoDAO.deleteDueno(dueno);
                    WindowUtils.showInfoMessage("Dueno eliminado");
                    tfDNI.setText("");
                    tfDueno.setText("");
                    tfEmail.setText("");
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
                        Dueno dueno = AdminController.buscarDueno(tfDNI.getText());
                        dueno.setNombre(tfDueno.getText());
                        dueno.setEmail(tfEmail.getText());
                        dueno.setTelefono(tfTelefono.getText());
                        System.out.println(dueno);
                        DuenoDAO.updateDueno(dueno);
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
                        Dueno dueno = new Dueno();
                        dueno.setDni(tfDNI.getText());
                        dueno.setNombre(tfDueno.getText());
                        dueno.setEmail(tfEmail.getText());
                        dueno.setTelefono(tfTelefono.getText());
                        dueno = (Dueno) PersonaDAO.createPersona(dueno);
                        System.out.println(dueno.getId());
                        DuenoDAO.insertDueno(dueno);
                        WindowUtils.showInfoMessage("Dueno añadido");
                        tfDNI.setText("");
                        tfDueno.setText("");
                        tfEmail.setText("");
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
        tfDNI.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (tfDNI.getText().equals("generar")) {
                    tfDNI.setText(Faker.getDni());
                    tfDueno.setText(Faker.firstName());
                    tfEmail.setText(Faker.firstName() + "@" + Faker.lastName() + ".com");
                    tfTelefono.setText(Faker.getPhoneNumber());
                }



            }
        });
    }


    public static void main() {
        JFrame frame = new JFrame("GestionDueno");
        frame.setContentPane(new GestionDueno().jpDueno);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }
}
