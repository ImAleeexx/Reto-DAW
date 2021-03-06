package com.imaleex.esportapp.Views;

import com.imaleex.esportapp.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserView {
    private JTextField tfUserName;
    private JMenuItem jmUltimaJornada;
    private JPanel jpUsuario;
    private JMenuItem jmClasificacion;
    private JMenuItem jmSalir;


    public UserView() {
        tfUserName.setText(Main.user.getNombre());

        jmSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        jmUltimaJornada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getRoot(jpUsuario);
                frame.dispose();
                VerJornada.main();
            }
        });
        jmClasificacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getRoot(jpUsuario);
                frame.dispose();
                VerClasificacion.main();
            }
        });
    }


    public static void main() {
        JFrame frame = new JFrame("UserView");
        frame.setContentPane(new UserView().jpUsuario);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        Main.closeLogin();
        frame.setVisible(true);
    }
}
