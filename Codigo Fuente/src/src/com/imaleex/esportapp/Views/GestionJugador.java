package com.imaleex.esportapp.Views;

import com.imaleex.esportapp.Controllers.AdminController;
import com.imaleex.esportapp.Exceptions.DataNotFoundException;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Main;
import com.imaleex.esportapp.Models.Equipo;
import com.imaleex.esportapp.Models.Personas.Entrenador;
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
    private JLabel lBuscar;
    private JComboBox<Jugador> cbBuscar;

    private ArrayList<Equipo> equipos;


    public GestionJugador() {
        tfUsuario.setText(Main.user.getNombre());
        llenarCb();
        loadSearchCb();

        jmSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        jmiUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getRoot(jpJugador);
                frame.dispose();
                GestionUsuario.main();
            }
        });
        jmiDueno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getRoot(jpJugador);
                frame.dispose();
                GestionDueno.main();
            }
        });
        jmiEquipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getRoot(jpJugador);
                frame.dispose();
                GestionEquipo.main();
            }
        });
        jmiEntrenador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getRoot(jpJugador);
                frame.dispose();
                GestionEntrenador.main();
            }
        });

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

        bAnadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!Validator.checkDni(tfDNI.getText())) {
                        throw new DataNotFoundException("DNI incorrecto");
                    }
                    if (!Validator.checkName(tfJugador.getText())) {
                        throw new DataNotFoundException("Nombre incorrecto");
                    }
                    if (!Validator.checkTel(tfTelefono.getText())) {
                        throw new DataNotFoundException("Telefono incorrecto");
                    }
                    if (!Validator.checkRegex("^[A-Za-z0-9]+([A-Za-z0-9]*|[._-]?[A-Za-z0-9]+)*$", tfNickname.getText())) {
                        throw new DataNotFoundException("Nickname incorrecto");
                    }
                    if (!Validator.checkDouble(tfSueldo.getText())) {
                        throw new DataNotFoundException("Sueldo incorrecto");
                    }

                    Jugador jugador = new Jugador();
                    jugador.setDni(tfDNI.getText());
                    jugador.setNombre(tfJugador.getText());
                    jugador.setTelefono(tfTelefono.getText());
                    jugador.setNickname(tfNickname.getText());
                    jugador.setSueldo(Double.parseDouble(tfSueldo.getText()));
                    if (cbRol.getSelectedIndex() != 0) {
                        jugador.setRol(Rol.getRol(cbRol.getSelectedItem().toString()));
                    }
                    if (cbEquipo.getSelectedIndex() != 0) {
                        jugador.setEquipo((Equipo) cbEquipo.getSelectedItem());
                        System.out.println("Siz " + jugador.getEquipo().getJugadores().size());
                    }
                    jugador = (Jugador) AdminController.insertPersona(jugador);
                    if (jugador != null) {
                        AdminController.insertjugador(jugador);
                        WindowUtils.showInfoMessage("Jugador añadido correctamente");
                        loadSearchCb();
                    }
                } catch (DataNotFoundException | DbException ex) {
                    WindowUtils.showErrorMessage(ex.getMessage());
                }
            }
        });
        bEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (WindowUtils.inputBoolean("¿Está seguro de que desea eliminar el jugador?")) {

                        if (!Validator.checkDni(tfDNI.getText())) {
                            throw new DataNotFoundException("DNI incorrecto");
                        }
                        Jugador jugador = AdminController.buscarJugador(tfDNI.getText());
                        AdminController.deleteJugador(jugador);
                        WindowUtils.showInfoMessage("Jugador eliminado correctamente");
                        loadSearchCb();
                    }
                } catch (DataNotFoundException | DbException ex) {
                    WindowUtils.showErrorMessage(ex.getMessage());
                }
            }
        });

        bModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (WindowUtils.inputBoolean("¿Está seguro de que desea modificar el jugador?")) {
                        Jugador jugador = AdminController.buscarJugador(tfDNI.getText());
                        if (!Validator.checkDni(tfDNI.getText())) {
                            throw new DataNotFoundException("DNI incorrecto");
                        }
                        if (!Validator.checkName(tfJugador.getText())) {
                            throw new DataNotFoundException("Nombre incorrecto");
                        }
                        if (!Validator.checkTel(tfTelefono.getText())) {
                            throw new DataNotFoundException("Telefono incorrecto");
                        }
                        if (!Validator.checkRegex("^[A-Za-z0-9]+([A-Za-z0-9]*|[._-]?[A-Za-z0-9]+)*$", tfNickname.getText())) {
                            throw new DataNotFoundException("Nickname incorrecto");
                        }
                        if (!Validator.checkDouble(tfSueldo.getText())) {
                            throw new DataNotFoundException("Sueldo incorrecto");
                        }

                        jugador.setDni(tfDNI.getText());
                        jugador.setNombre(tfJugador.getText());
                        jugador.setTelefono(tfTelefono.getText());
                        jugador.setNickname(tfNickname.getText());
                        jugador.setSueldo(Double.parseDouble(tfSueldo.getText()));
                        if (cbRol.getSelectedIndex() != 0) {
                            jugador.setRol(Rol.getRol((String) cbRol.getSelectedItem()));
                        }
                        if (cbEquipo.getSelectedIndex() != 0) {
                            jugador.setEquipo((Equipo) cbEquipo.getSelectedItem());
                        }
                        AdminController.updatejugador(jugador);
                        WindowUtils.showInfoMessage("Jugador modificado correctamente");
                        loadSearchCb();

                    }
                } catch (DataNotFoundException | DbException ex) {
                    WindowUtils.showErrorMessage(ex.getMessage());
                }
            }
        });

        cbBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Jugador jugador = (Jugador) cbBuscar.getSelectedItem();
                if ((jugador != null ? jugador.getNombre() : null) != null) {
                    tfDNI.setText(jugador.getDni());
                    bBuscar.doClick();
                }
            }
        });
    }

    private void loadSearchCb() {
        try {
            cbBuscar.removeAllItems();
            cbBuscar.addItem(new Jugador());
            AdminController.listaJugadores().forEach(jugador -> {
                cbBuscar.addItem(jugador);
            });
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main() {
        JFrame frame = new JFrame("GestionJugador");
        frame.setContentPane(new GestionJugador().jpJugador);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    public void llenarCb() {
        try {
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
        }catch (DbException ex) {
            WindowUtils.showErrorMessage(ex.getMessage());
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
