package com.imaleex.esportapp.Views;

import com.imaleex.esportapp.Db.Dao.Personas.DuenoDAO;
import com.imaleex.esportapp.Exceptions.DataNotFoundException;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Main;
import com.imaleex.esportapp.Models.Personas.Dueno;
import com.imaleex.esportapp.Utils.WindowUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


    public GestionDueno() {
        tfUsuario.setText(Main.user.getNombre());
        jpEscondido.setVisible(false);
        bBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jpEscondido.setVisible(true);
                bAnadir.setVisible(false);
                try {
                    if (checkDni(tfDNI.getText())) {
                    Dueno dueno = Main.buscarDueno(tfDNI.getText());

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
                    Dueno dueno = Main.buscarDueno(tfDNI.getText());
                    DuenoDAO.deleteDueno(dueno);
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
                        Dueno dueno = Main.buscarDueno(tfDNI.getText());
                        dueno.setNombre(tfUsuario.getText());
                        dueno.setEmail(tfEmail.getText());
                        dueno.setTelefono(tfTelefono.getText());
                        System.out.println(dueno);
                        DuenoDAO.updateDueno(dueno);
                    } catch (DataNotFoundException | DbException ex) {
                        throw new RuntimeException(ex);
                    }
                }
        }});
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
