package com.imaleex.esportapp.Views;

import com.imaleex.esportapp.Controllers.AdminController;
import com.imaleex.esportapp.Db.Dao.EquipoDAO;
import com.imaleex.esportapp.Exceptions.DataNotFoundException;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Main;
import com.imaleex.esportapp.Models.Equipo;
import com.imaleex.esportapp.Models.Personas.Dueno;
import com.imaleex.esportapp.Models.Personas.Entrenador;
import com.imaleex.esportapp.Utils.Validator;
import com.imaleex.esportapp.Utils.WindowUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    private JLabel lDueño;
    private JButton bBuscar;
    private JButton bAnadir;
    private JPanel jpEscondido;
    private JButton bEliminar;
    private JButton bModificar;
    private JPanel jpEquipo;
    private JComboBox<Entrenador> cbEntrenador;
    private JComboBox<Entrenador> cbEntrenadorAsistente;
    private JComboBox<Dueno> cbDueno;

    private Equipo equipo;
    private ArrayList<Entrenador> entrenadores;
    private ArrayList<Dueno> duenos;

    public GestionEquipo() {
        tfUsuario.setText(Main.user.getNombre());
        try {
            llenarCB();
        } catch (DataNotFoundException | DbException e) {
            WindowUtils.showErrorMessage(e.getMessage());
        }


        bBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jpEscondido.setVisible(true);
                bAnadir.setVisible(false);

                if (!tfEquipo.getText().isEmpty()) {
                    try {
                        Equipo equipo = AdminController.buscarEquipo(tfEquipo.getText());
                        tfEquipo.setText(equipo.getNombre());
                        cbEntrenador.setSelectedIndex(0);
                        cbEntrenadorAsistente.setSelectedIndex(0);
                        cbDueno.setSelectedIndex(0);
                        if (equipo.getEntrenador() != null) {
                            cbEntrenador.setSelectedItem(searchEntrenadorOnList(equipo.getEntrenador().getId()));
                        }

                        if (equipo.getEntrenadorAsistente() != null) {
                            cbEntrenadorAsistente.setSelectedItem(searchEntrenadorOnList(equipo.getEntrenadorAsistente().getId()));
                        }
                        if (equipo.getDueno() != null) {
                            cbDueno.setSelectedItem(searchDuenoOnList(equipo.getDueno().getId()));
                        }


                    } catch (DataNotFoundException ex) {
                        WindowUtils.showErrorMessage(ex.getMessage());
                    }
                }
            }
        });
        bEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tfEquipo.getText().isEmpty()) {
                    try {

                        Equipo equipo = AdminController.buscarEquipo(tfEquipo.getText());
                        EquipoDAO.deleteEquipo(equipo);
                        WindowUtils.showInfoMessage("Equipo eliminado");
                        tfEquipo.setText("");
                        cbEntrenador.setSelectedIndex(0);
                        cbEntrenadorAsistente.setSelectedIndex(0);
                        cbDueno.setSelectedIndex(0);
                    } catch (DataNotFoundException | DbException ex) {
                        WindowUtils.showErrorMessage(ex.getMessage());
                    }
                }
            }
        });
        bModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tfEquipo.getText().isEmpty()) {
                    try {
                        Equipo equipo = AdminController.buscarEquipo(tfEquipo.getText());

                        if (Validator.checkName(tfEquipo.getText())) {
                            equipo.setNombre(tfEquipo.getText());
                        }

                        if (cbEntrenador.getSelectedIndex() != 0) {
                            equipo.setEntrenador((Entrenador) cbEntrenador.getSelectedItem());
                        } else equipo.setEntrenador(null);


                        if (cbEntrenadorAsistente.getSelectedIndex() != 0) {
                            equipo.setEntrenadorAsistente((Entrenador) cbEntrenadorAsistente.getSelectedItem());
                        } else equipo.setEntrenadorAsistente(null);

                        if (!(cbEntrenador.getSelectedIndex() == 0 && cbEntrenadorAsistente.getSelectedIndex() == 0) && cbEntrenadorAsistente.getSelectedIndex() == cbEntrenador.getSelectedIndex()) {
                            throw new DataNotFoundException("No puede ser el mismo entrenador");
                        }

                        if (cbDueno.getSelectedIndex() != 0) {
                            equipo.setDueno((Dueno) cbDueno.getSelectedItem());
                        } else equipo.setDueno(null);

                        EquipoDAO.updateEquipo(equipo);
                        WindowUtils.showInfoMessage("Equipo modificado");

                    } catch (DataNotFoundException | DbException ex) {
                        WindowUtils.showErrorMessage(ex.getMessage());
                    }
                }
            }
        });

        bAnadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!tfEquipo.getText().isEmpty()) {
                    try {
                        Equipo equipo = new Equipo();
                        if (Validator.checkName(tfEquipo.getText())) {
                            equipo.setNombre(tfEquipo.getText());
                        }
                        if (cbEntrenador.getSelectedIndex() != 0) {
                            equipo.setEntrenador((Entrenador) cbEntrenador.getSelectedItem());
                        }
                        if (!(cbEntrenador.getSelectedIndex() == 0 && cbEntrenadorAsistente.getSelectedIndex() == 0) && cbEntrenadorAsistente.getSelectedIndex() == cbEntrenador.getSelectedIndex()) {
                            throw new DataNotFoundException("No puede ser el mismo entrenador");
                        }
                        if (cbEntrenadorAsistente.getSelectedIndex() != 0) {
                            equipo.setEntrenadorAsistente((Entrenador) cbEntrenadorAsistente.getSelectedItem());
                        }
                        if (cbDueno.getSelectedIndex() != 0) {
                            equipo.setDueno((Dueno) cbDueno.getSelectedItem());
                        }
                        EquipoDAO.insertEquipo(equipo);
                        WindowUtils.showInfoMessage("Equipo añadido");
                        tfEquipo.setText("");
                        cbEntrenador.setSelectedIndex(0);
                        cbEntrenadorAsistente.setSelectedIndex(0);
                        cbDueno.setSelectedIndex(0);
                    } catch (DbException | DataNotFoundException ex) {
                        WindowUtils.showErrorMessage(ex.getMessage());
                    }
                }
            }
        });
    }

    public static void main() {
        JFrame frame = new JFrame("GestionEquipo");
        frame.setContentPane(new GestionEquipo().jpEquipo);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void llenarCB() throws DataNotFoundException, DbException {

        //Llenar comboBox de entrenadores y asistentes
        cbEntrenador.removeAllItems();
        cbEntrenadorAsistente.removeAllItems();
        entrenadores = AdminController.listaEntrenadores();
        cbEntrenador.addItem(new Entrenador());
        cbEntrenadorAsistente.addItem(new Entrenador());
        for (Entrenador entrenador : entrenadores) {
            cbEntrenador.addItem(entrenador);
            cbEntrenadorAsistente.addItem(entrenador);
        }

        //LLenar comboBox de duenos
        cbDueno.removeAllItems();
        duenos = AdminController.listDuenos();
        cbDueno.addItem(new Dueno());
        for (Dueno dueno : duenos) {
            cbDueno.addItem(dueno);
        }


    }

    private Entrenador searchEntrenadorOnList(int id) {
        for (Entrenador entrenador : entrenadores) {
            if (entrenador.getId() == id) {
                return entrenador;
            }
        }
        return null;
    }

    private Dueno searchDuenoOnList(int id) {
        for (Dueno dueno : duenos) {
            if (dueno.getId() == id) {
                return dueno;
            }
        }
        return null;
    }
}
