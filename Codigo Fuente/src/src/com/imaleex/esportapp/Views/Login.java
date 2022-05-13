package com.imaleex.esportapp.Views;

import com.imaleex.esportapp.Utils.Validator;
import sun.misc.JavaxCryptoSealedObjectAccess;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    private JLabel lUsername;
    private JTextField tfUsername;
    private JLabel lPassword;
    private JPasswordField pfPassword;
    private JButton bLogin;
    private  JPanel jpPrincipal;
    private JButton bExit;

    public Login() {
        bLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        bExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public JPanel getJPanel(){
        return jpPrincipal;
    }






}
