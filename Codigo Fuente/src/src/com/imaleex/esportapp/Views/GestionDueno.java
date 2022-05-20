package com.imaleex.esportapp.Views;

import com.imaleex.esportapp.Controllers.AdminController;
import com.imaleex.esportapp.Exceptions.DataNotFoundException;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Main;
import com.imaleex.esportapp.Models.Personas.Dueno;
import com.imaleex.esportapp.Utils.Faker;
import com.imaleex.esportapp.Utils.Validator;
import com.imaleex.esportapp.Utils.WindowUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
    private JLabel lBuscar;
    private JComboBox<Dueno> cbBuscar;


    public GestionDueno() {
        tfUsuario.setText(Main.user.getNombre());
        jpEscondido.setVisible(false);
        loadSearchCb();
        phaseTwoDisables();

        jmSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        jmiUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getRoot(jpDueno);
                frame.dispose();
                GestionUsuario.main();
            }
        });
        jmiEquipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getRoot(jpDueno);
                frame.dispose();
                GestionEquipo.main();
            }
        });
        jmiEntrenador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getRoot(jpDueno);
                frame.dispose();
                GestionEntrenador.main();
            }
        });

        jmiJugador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getRoot(jpDueno);
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
                    } else {
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

                        if (WindowUtils.inputBoolean("¿Estas seguro de que quieres eliminar el dueno?")) {
                            Dueno dueno = AdminController.buscarDueno(tfDNI.getText());
                            AdminController.eliminarDueno(dueno);
                            WindowUtils.showInfoMessage("Dueno eliminado");
                            tfDNI.setText("");
                            tfDueno.setText("");
                            tfEmail.setText("");
                            tfTelefono.setText("");
                            jpEscondido.setVisible(false);
                            bAnadir.setVisible(true);
                            tfDNI.setBackground(Color.white);
                        }
                        loadSearchCb();
                    } catch (DbException | DataNotFoundException ex) {
                        WindowUtils.showErrorMessage(ex.getMessage());
                    }
                } else {
                    WindowUtils.showErrorMessage("DNI no valido");
                }
            }
        });
        bModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkDni(tfDNI.getText())) {
                    try {
                        if (WindowUtils.inputBoolean("¿Esta seguro de modificar el dueno?")) {
                            Dueno dueno = AdminController.buscarDueno(tfDNI.getText());
                            if (Validator.checkName(tfDueno.getText())) {
                                dueno.setNombre(tfDueno.getText());
                            } else {
                                throw new DbException("Nombre no valido");
                            }
                            if (Validator.checkEmail(tfEmail.getText())) {
                                dueno.setEmail(tfEmail.getText());
                            } else {
                                throw new DbException("Email no valido");
                            }
                            if (Validator.checkTel(tfTelefono.getText())) {
                                dueno.setTelefono(tfTelefono.getText());
                            } else {
                                throw new DbException("Telefono no valido");
                            }
                            System.out.println(dueno);
                            AdminController.updateDueno(dueno);
                            WindowUtils.showInfoMessage("Dueno modificado");
                        }
                        loadSearchCb();
                    } catch (DataNotFoundException | DbException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        bAnadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Dueno dueno = new Dueno();
                    if (checkDni(tfDNI.getText())) {
                        dueno.setDni(tfDNI.getText());
                    } else {
                        throw new DbException("DNI no valido");
                    }
                    if (Validator.checkName(tfDueno.getText())) {
                        dueno.setNombre(tfDueno.getText());
                    } else {
                        throw new DbException("Nombre no valido");
                    }
                    if (Validator.checkEmail(tfEmail.getText())) {
                        dueno.setEmail(tfEmail.getText());
                    } else {
                        throw new DbException("Email no valido");
                    }
                    if (Validator.checkTel(tfTelefono.getText())) {
                        dueno.setTelefono(tfTelefono.getText());
                    } else {
                        throw new DbException("Telefono no valido");
                    }
                    dueno = (Dueno) AdminController.insertPersona(dueno);
                    System.out.println(dueno.getId());
                    AdminController.insertarDueno(dueno);
                    WindowUtils.showInfoMessage("Dueno añadido");
                    tfDNI.setText("");
                    tfDueno.setText("");
                    tfEmail.setText("");
                    tfTelefono.setText("");
                    jpEscondido.setVisible(false);
                    bAnadir.setVisible(true);
                    tfDNI.setBackground(Color.white);
                    loadSearchCb();
                } catch (DbException ex) {
                    WindowUtils.showErrorMessage(ex.getMessage());
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
        cbBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dueno dueno = (Dueno) cbBuscar.getSelectedItem();
                if ((dueno != null ? dueno.getNombre() : null) != null) {
                    tfDNI.setText(dueno.getDni());
                    bBuscar.doClick();
                }
            }
        });
    }

    private void loadSearchCb() {
        try {
            cbBuscar.removeAllItems();
            cbBuscar.addItem(new Dueno());
            AdminController.listDuenos().forEach(dueno -> {
                cbBuscar.addItem(dueno);
            });
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }


    private void phaseTwoDisables(){
        if (AdminController.checkLeagueStarted()){
            bAnadir.setVisible(false);
            bModificar.setVisible(false);
            bEliminar.setVisible(false);

            tfEmail.setEditable(false);
            tfTelefono.setEditable(false);
            tfDueno.setEditable(false);
        }
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
