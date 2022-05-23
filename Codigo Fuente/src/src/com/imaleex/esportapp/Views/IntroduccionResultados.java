package com.imaleex.esportapp.Views;

import com.imaleex.esportapp.Controllers.AdminController;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Models.Partido;
import com.imaleex.esportapp.Utils.WindowUtils;

import javax.swing.*;
import java.awt.event.*;

public class IntroduccionResultados extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField tfMarcadorLocal;
    private JTextField tfMarcadorVisitante;
    private JLabel lLocal;
    private JLabel lVisitante;
    private Partido partido;
    private final VerJornada vj;
    public IntroduccionResultados(int idPartido, VerJornada vj) {
        try {
            partido = AdminController.buscarPartidoId(idPartido);
        } catch (DbException e) {
            WindowUtils.showErrorMessage(e.getMessage());
            dispose();
        }
        this.vj = vj;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        lLocal.setText(partido.getLocal().getNombre());
        lVisitante.setText(partido.getVisitante().getNombre());
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        try {
            partido.setMarcadorLocal(Integer.parseInt(tfMarcadorLocal.getText()));
            partido.setMarcadorVisitante(Integer.parseInt(tfMarcadorVisitante.getText()));
            AdminController.actualizarMarcador(partido);
        }catch (NumberFormatException e){
            WindowUtils.showErrorMessage("Los marcadores deben ser números");
        } catch (DbException e) {
           WindowUtils.showErrorMessage(e.getMessage());
        }
        vj.cargarJornada();
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(int idPartido, VerJornada vj) {
        IntroduccionResultados dialog = new IntroduccionResultados(idPartido, vj);
        dialog.pack();
        dialog.setVisible(true);
        dialog.setLocationRelativeTo(null);

    }
}
