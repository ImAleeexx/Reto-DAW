package com.imaleex.esportapp.Views;

import com.imaleex.esportapp.Controllers.UserController;
import com.imaleex.esportapp.Exceptions.DbException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;

public class VerClasificacion extends JFrame {

    // versión
    private static final long serialVersionUID = 1L;

    // la tabla
    private final JTable table;

    // el modelo de tabla, aquí van a estar los datos.
    private final DefaultTableModel model;

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
        model = new DefaultTableModel(null, columnNames);
        // creo la tabla con el modelo de datos creado
        table = new JTable(model);

        // se pone la tabla en el scroll
        scrollPane.setViewportView(table);

        // código del botón
        JButton btnAadirLnea = new JButton("Meter contenido");

        btnAadirLnea.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Object[][] partidos;
                try {
                    partidos = UserController.generateClasificacion();
                } catch (DbException e) {
                    throw new RuntimeException(e);
                }

                for (int i = 0; i < partidos.length; i++) {
                    Object[] aux = new Object[]{i+1, partidos[i][0], partidos[i][4], partidos[i][1],partidos[i][2],partidos[i][3]};
                    model.addRow(aux);

                }
            }
        });


        // dimensiones y posición del botón
        btnAadirLnea.setBounds(10, 249, 267, 23);
        // pongo el botón en la ventana
        getContentPane().add(btnAadirLnea);

    }

    // función principal
    public static void main() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VerClasificacion frame = new VerClasificacion();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}