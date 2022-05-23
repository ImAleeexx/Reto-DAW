package com.imaleex.esportapp.Views;

import com.imaleex.esportapp.Controllers.AdminController;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Main;
import com.imaleex.esportapp.Utils.WindowUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminView {
    private JTextField tfUserName;
    private JPanel jpAdmin;
    private JMenuItem jmUltimaJornada;
    private JMenuItem jmClasificacion;
    private JMenuItem jmSalir;
    private JMenu jmGestion;
    private JMenuItem jmiJugador;
    private JMenuItem jmiEquipo;
    private JMenuItem jmiDueno;
    private JMenuItem jmiUsuario;
    private JMenuItem jmiEntrenador;
    private JButton bGenerarCalendario;
    private JButton bGenerarJugadores;
    private JButton bGenerarEntrenadores;
    private JButton bGenerarDuenos;
    private JButton bGenerarEquipos;


    public AdminView() {
        tfUserName.setText(Main.user.getNombre());
        phaseTwoDisables();

        jmSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jmiUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getRoot(jpAdmin);
                frame.dispose();
                GestionUsuario.main();
            }
        });
        jmiDueno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getRoot(jpAdmin);
                frame.dispose();
                GestionDueno.main();
            }
        });
        jmiEquipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getRoot(jpAdmin);
                frame.dispose();
                GestionEquipo.main();
            }
        });
        jmiEntrenador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getRoot(jpAdmin);
                frame.dispose();
                GestionEntrenador.main();
            }
        });

        jmiJugador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getRoot(jpAdmin);
                frame.dispose();
                GestionJugador.main();
            }
        });

        jmUltimaJornada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getRoot(jpAdmin);
                frame.dispose();
                VerJornada.main();
            }
        });
        jmClasificacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getRoot(jpAdmin);
                frame.dispose();
                VerClasificacion.main();
            }
        });
        bGenerarCalendario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (WindowUtils.inputBoolean("¿Está seguro de que desea generar el calendario?")) {
                        AdminController.generateMatchCalendar();
                    }
                } catch (DbException ex) {
                    WindowUtils.showErrorMessage(ex.getMessage());
                }
            }
        });
        bGenerarJugadores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: Generar jugadores Random
            }
        });
        bGenerarEntrenadores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: Generar entrenadores Random
            }
        });
        bGenerarDuenos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: Generar duenos Random
            }
        });
        bGenerarEquipos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: Generar equipos Random
            }
        });
    }


    /**
     * Metodo que comprueba si la fase 1 esta cerrada para desactivar los botones
     */
    private void phaseTwoDisables() {
        if (AdminController.checkLeagueStarted()) {
            bGenerarCalendario.setVisible(false);
            bGenerarJugadores.setVisible(false);
            bGenerarEntrenadores.setVisible(false);
            bGenerarDuenos.setVisible(false);
            bGenerarEquipos.setVisible(false);
        }
    }

    public static void main() {
        JFrame frame = new JFrame("AdminView");
        frame.setContentPane(new AdminView().jpAdmin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        Main.closeLogin();
        frame.setVisible(true);


    }

}
