package com.imaleex.esportapp.Views;

import com.imaleex.esportapp.Controllers.UserController;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VerClasificacion extends JFrame {

    // versión
    private static final long serialVersionUID = 1L;

    // constructor del frame que contruye toda la ventana...
    public VerClasificacion() {
        //título
        setTitle("Jornada");
        // cuando cerramos la ventana se cierra la aplicación por completo
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // dimensiones y posición
        setBounds(100, 100, 596, 331);
        // establece una capa absoluta para posicionar los elementos donde queramos
        getContentPane().setLayout(null);

        // el panel con barras de scroll automáticas
        JScrollPane scrollPane = new JScrollPane();
        // dimensiones y posición del panel de scroll
        scrollPane.setBounds(10, 11, 560, 227);

        // se añade el panel de scroll a la ventana
        getContentPane().add(scrollPane);

        // nombre de las columnas
        String[] columnNames = {"Posicion", "Equipo", "Puntos", "Partidos Jugados", "Partidos Ganados", "Partidos Perdidos"};

        // creo un modelo de datos, sin datos por eso 'null' y establezco los
        // nombres de columna
        // el modelo de tabla, aquí van a estar los datos.
        DefaultTableModel model = new DefaultTableModel(null, columnNames);
        // creo la tabla con el modelo de datos creado
        // la tabla
        JTable table = new JTable(model){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(20);
        table.getColumnModel().getColumn(3).setPreferredWidth(20);
        table.getColumnModel().getColumn(4).setPreferredWidth(20);
        table.getColumnModel().getColumn(5).setPreferredWidth(20);


        // se pone la tabla en el scroll
        scrollPane.setViewportView(table);

        // código del botón
        JButton bMostrar = new JButton("Meter contenido");
        JButton bAtras = new JButton("Volver");

        Object[][] partidos;
        try {
            partidos = UserController.generateClasificacion();
        } catch (DbException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < partidos.length; i++) {
            Object[] aux = new Object[]{i + 1, partidos[i][0], partidos[i][4], partidos[i][1], partidos[i][2], partidos[i][3]};
            model.addRow(aux);

        }

        bAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dispose();
                if (Main.user.checkAdmin()) {
                    AdminView.main();
                } else {
                    UserView.main();
                }
            }
        });

        // dimensiones y posición del botón
        bAtras.setBounds(300, 249, 267, 23);
        // pongo el botón en la ventana
        getContentPane().add(bAtras);
    }

    // función principal
    public static void main() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VerClasificacion frame = new VerClasificacion();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}