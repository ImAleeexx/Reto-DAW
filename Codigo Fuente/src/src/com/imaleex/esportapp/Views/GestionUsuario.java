package com.imaleex.esportapp.Views;

import com.imaleex.esportapp.Controllers.AdminController;
import com.imaleex.esportapp.Db.Dao.UserDAO;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Exceptions.UserNotFoundException;
import com.imaleex.esportapp.Main;
import com.imaleex.esportapp.Models.Users.Usuario;
import com.imaleex.esportapp.Utils.CryptoUtils;
import com.imaleex.esportapp.Utils.WindowUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class GestionUsuario{
    private JTextField tfUserName;
    private JPanel jpAdmin;
    private JMenuItem jmUltimaJornada;
    private JMenuItem jmClasificacion;
    private JMenuItem jmSalir;
    private JLabel lNombre;
    private JTextField tfUsuario;
    private JPasswordField tfContraseña;
    private JLabel lContrasena;
    private JLabel lTipoUsuario;
    private JComboBox<String> cbTipoUsuario;
    private JMenu jmGestion;
    private JMenuItem jmiJugador;
    private JMenuItem jmiEquipo;
    private JMenuItem jmiDueno;
    private JMenuItem jmiUsuario;
    private JButton bEliminar;
    private JButton bModificar;
    private JButton bAnadir;
    private JButton bBuscar;
    private JPanel jpEscondido;
    private JMenuItem jmiEntrenador;


    public GestionUsuario() {
        tfUserName.setText(Main.user.getNombre());
        cbTipoUsuario.addItem("");
        cbTipoUsuario.addItem("Normal");
        cbTipoUsuario.addItem("Administrador");
        jpEscondido.setVisible(false);

        jmiDueno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame)SwingUtilities.getRoot(jpAdmin);
                frame.dispose();
                GestionDueno.main();
            }
        });
        jmiEquipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame)SwingUtilities.getRoot(jpAdmin);
                frame.dispose();
                GestionEquipo.main();
            }
        });
        jmiEntrenador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame)SwingUtilities.getRoot(jpAdmin);
                frame.dispose();
                GestionEntrenador.main();
            }
        });

        jmiJugador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame)SwingUtilities.getRoot(jpAdmin);
                frame.dispose();
                GestionJugador.main();
            }
        });
        jmSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        bBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               jpEscondido.setVisible(true);
               bAnadir.setVisible(false);
                try {
                    Usuario usuario = AdminController.buscarUsuario(tfUsuario.getText());

                    assert usuario != null;
                   //Set tfUsuario with green background
                    tfUsuario.setBackground(Color.GREEN);
                    cbTipoUsuario.setSelectedIndex(usuario.getType()+1);

                } catch (UserNotFoundException ex) {
                    WindowUtils.showErrorMessage(ex.getMessage());
                    tfUsuario.setText("");
                    cbTipoUsuario.setSelectedIndex(0);
                    jpEscondido.setVisible(false);
                    bAnadir.setVisible(true);
                    tfUsuario.setBackground(Color.RED);
                }
            }
        });
        bEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(WindowUtils.inputBoolean("¿Está seguro de que desea eliminar el usuario?")) {
                        AdminController.deleteUserByName(tfUsuario.getText());
                        WindowUtils.showInfoMessage("Usuario eliminado correctamente");
                    }
                } catch ( DbException ex) {
                    WindowUtils.showErrorMessage(ex.getMessage());
                }
            }
        });

        bModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (WindowUtils.inputBoolean("¿Está seguro de que desea modificar el usuario?")) {
                        Usuario usuario = AdminController.buscarUsuario(tfUsuario.getText());
                        assert usuario != null;
                        usuario.setNombre(tfUsuario.getText());
                        usuario.setClave(CryptoUtils.hashFunc(tfContraseña.getText()));
                        usuario.setType(cbTipoUsuario.getSelectedIndex() - 1);
                        AdminController.editUser(usuario);
                        WindowUtils.showInfoMessage("Usuario modificado correctamente");
                    }
                } catch (UserNotFoundException | DbException ex) {
                    WindowUtils.showErrorMessage(ex.getMessage());
                }
            }
        });
        bAnadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Usuario usuario = new Usuario();
                    usuario.setNombre(tfUsuario.getText());
                    usuario.setClave(CryptoUtils.hashFunc(tfContraseña.getText()));
                    usuario.setType(cbTipoUsuario.getSelectedIndex()-1);
                    AdminController.createUser(usuario);
                    WindowUtils.showInfoMessage("Usuario creado correctamente");
                } catch (DbException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public static void main() {
        JFrame frame = new JFrame("GestionUsuario");
        frame.setContentPane(new GestionUsuario().jpAdmin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        Main.closeLogin();
        frame.setVisible(true);

    }
}
