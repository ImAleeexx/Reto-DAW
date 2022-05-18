package com.imaleex.esportapp.Views;

import com.imaleex.esportapp.Controllers.AdminController;
import com.imaleex.esportapp.Db.Dao.EquipoDAO;
import com.imaleex.esportapp.Exceptions.DataNotFoundException;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Main;
import com.imaleex.esportapp.Models.Equipo;
import com.imaleex.esportapp.Models.Personas.Entrenador;
import com.imaleex.esportapp.Utils.WindowUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JComboBox cbEntrenador;
    private JComboBox cbEntrenadorAsistente;
    private JComboBox cbDueno;

    public GestionEquipo() {
        tfUsuario.setText(Main.user.getNombre());


        bBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jpEscondido.setVisible(true);
                bAnadir.setVisible(false);

                if (!tfEquipo.getText().isEmpty()) {
                    try {
                        Equipo equipo = AdminController.buscarEquipo(tfEquipo.getText());
                        tfEquipo.setText(equipo.getNombre());
                        llenarCB();
                        Entrenador entrendor = (Entrenador) cbEntrenador.getSelectedItem();
                        System.out.println(entrendor.getNombre());
                        //tfAsistente.setText(equipo.getEntrenadorAsistente().getNombre());
                        //tfDueno.setText(equipo.getDueno().getNombre());

                    } catch (DataNotFoundException ex) {
                        WindowUtils.showErrorMessage(ex.getMessage());
                    } catch (DbException ex) {
                        throw new RuntimeException(ex);
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
                        equipo.setNombre(tfEquipo.getText());
                        //equipo.setEntrenador(AdminController.buscarEntrenador(tfEntrenador.getText()));

            } catch (DataNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }}});
    }

    public static void main() {
        JFrame frame = new JFrame("GestionEquipo");
        frame.setContentPane(new GestionEquipo().jpEquipo);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private  void  llenarCB() throws DataNotFoundException, DbException {
        try {
            Entrenador entrenador = AdminController.buscarEntrenadorId(6);
            cbEntrenador.addItem(entrenador);
        }catch (DataNotFoundException ex){
            throw new DataNotFoundException("No se encontro el entrenador");
        }
    }
}
