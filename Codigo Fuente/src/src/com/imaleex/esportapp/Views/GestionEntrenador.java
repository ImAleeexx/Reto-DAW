package com.imaleex.esportapp.Views;

import com.imaleex.esportapp.Controllers.AdminController;
import com.imaleex.esportapp.Exceptions.DataNotFoundException;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Main;
import com.imaleex.esportapp.Models.Personas.Entrenador;
import com.imaleex.esportapp.Utils.Validator;
import com.imaleex.esportapp.Utils.WindowUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JLabel lBuscar;
    private JComboBox<Entrenador> cbBuscar;


    public GestionEntrenador() {
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
                JFrame frame = (JFrame) SwingUtilities.getRoot(jpEntrenador);
                frame.dispose();
                GestionUsuario.main();
            }
        });
        jmiDueno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getRoot(jpEntrenador);
                frame.dispose();
                GestionDueno.main();
            }
        });
        jmiEquipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getRoot(jpEntrenador);
                frame.dispose();
                GestionEquipo.main();
            }
        });

        jmiJugador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getRoot(jpEntrenador);
                frame.dispose();
                GestionJugador.main();
            }
        });
        jmUltimaJornada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getRoot(jpEntrenador);
                frame.dispose();
                VerJornada.main();
            }
        });
        jmClasificacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getRoot(jpEntrenador);
                frame.dispose();
                VerClasificacion.main();
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

                    } else {
                        throw new DataNotFoundException("DNI no valido");
                    }
                } catch (DataNotFoundException | DbException ex) {
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
                        if (WindowUtils.inputBoolean("??Esta seguro de eliminar el entrenador?")) {
                            Entrenador entrenador = AdminController.buscarEntrenadorDni(tfDNI.getText());
                            AdminController.deleteEntrenador(entrenador);
                            WindowUtils.showInfoMessage("Entrenador eliminado");
                            tfDNI.setText("");
                            tfEntrenador.setText("");
                            tfSueldo.setText("");
                            tfTelefono.setText("");
                            jpEscondido.setVisible(false);
                            bAnadir.setVisible(true);
                            tfDNI.setBackground(Color.white);
                            loadSearchCb();
                        }
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
                        if (WindowUtils.inputBoolean("??Esta seguro de modificar el entrenador?")) {
                            Entrenador entrenador = AdminController.buscarEntrenadorDni(tfDNI.getText());
                            entrenador.setDni(tfDNI.getText());
                            if (Validator.checkName(tfEntrenador.getText())) {
                                entrenador.setNombre(tfEntrenador.getText());
                            } else {
                                throw new DataNotFoundException("Nombre no valido");
                            }
                            if (Double.parseDouble(tfSueldo.getText()) > 14000) {
                                entrenador.setSueldo(Double.parseDouble(tfSueldo.getText()));
                            } else {
                                throw new DataNotFoundException("Sueldo no valido. Debe ser mayor de 14000");
                            }
                            if (Validator.checkTel(tfTelefono.getText())) {
                                entrenador.setTelefono(tfTelefono.getText());
                            } else {
                                throw new DataNotFoundException("Telefono no valido");
                            }
                            AdminController.updateEntrenador(entrenador);
                            WindowUtils.showInfoMessage("Entrenador modificado");
                            loadSearchCb();
                        }
                    } catch (DataNotFoundException | DbException ex) {
                        WindowUtils.showErrorMessage(ex.getMessage());
                    }
                }
            }
        });
        bAnadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkDni(tfDNI.getText())) {
                    try {
                        Entrenador entrenador = new Entrenador();
                        entrenador.setDni(tfDNI.getText());
                        if (Validator.checkName(tfEntrenador.getText())) {
                            entrenador.setNombre(tfEntrenador.getText());
                        } else {
                            throw new DbException("Nombre no valido");
                        }
                        if (Double.parseDouble(tfSueldo.getText()) > 14000) {
                            entrenador.setSueldo(Double.parseDouble(tfSueldo.getText()));
                        } else {
                            throw new DbException("Sueldo no valido. Debe ser mayor de 14000");
                        }
                        if (Validator.checkTel(tfTelefono.getText())) {
                            entrenador.setTelefono(tfTelefono.getText());
                        } else {
                            throw new DbException("Telefono no valido");
                        }
                        entrenador = (Entrenador) AdminController.insertarPersona(entrenador);
                        AdminController.insertEntrenador(entrenador);
                        WindowUtils.showInfoMessage("Entrenador a??adido");
                        tfDNI.setText("");
                        tfEntrenador.setText("");
                        tfSueldo.setText("");
                        tfTelefono.setText("");
                        jpEscondido.setVisible(false);
                        bAnadir.setVisible(true);
                        tfDNI.setBackground(Color.white);
                        loadSearchCb();
                    } catch (DbException ex) {
                        WindowUtils.showErrorMessage(ex.getMessage());
                    }
                } else {
                    WindowUtils.showErrorMessage("DNI no valido");
                }
            }
        });
        cbBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Entrenador entrenador = (Entrenador) cbBuscar.getSelectedItem();
                if ((entrenador != null ? entrenador.getNombre() : null) != null) {
                    tfDNI.setText(entrenador.getDni());
                    bBuscar.doClick();
                }
            }
        });
    }
    /**
     * Metodo que carga los combobox de busqueda
     */
    private void loadSearchCb() {
        try {
            cbBuscar.removeAllItems();
            cbBuscar.addItem(new Entrenador());
            AdminController.listaEntrenadores().forEach(entrenador -> {
                cbBuscar.addItem(entrenador);
            });
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Metodo que comprueba si la fase 1 esta cerrada para desactivar los botones
     */
    private void phaseTwoDisables() {
        if (AdminController.checkLeagueStarted()) {
            bAnadir.setVisible(false);
            bModificar.setVisible(false);
            bEliminar.setVisible(false);

            tfTelefono.setEditable(false);
            tfEntrenador.setEditable(false);
            tfSueldo.setEditable(false);
        }
    }

    public static void main() {
        JFrame frame = new JFrame("GestionEntrenador");
        frame.setContentPane(new GestionEntrenador().jpEntrenador);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }
}
