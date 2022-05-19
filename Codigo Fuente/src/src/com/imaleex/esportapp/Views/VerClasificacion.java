package com.imaleex.esportapp.Views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class verClasificacion extends JFrame {

    // versión
    private static final long serialVersionUID = 1L;

    // la tabla
    private JTable table;

    // el modelo de tabla, aquí van a estar los datos.
    private DefaultTableModel model;

    // función principal
    public static void main() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    verClasificacion frame = new verClasificacion();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // constructor del frame que contruye toda la ventana...
    public verClasificacion() {
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
        String[] columnNames = { "Posicion", "Equipo", "Puntos"};

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


            }
        });
        // dimensiones y posición del botón
        btnAadirLnea.setBounds(10, 249, 267, 23);
        // pongo el botón en la ventana
        getContentPane().add(btnAadirLnea);

    }
}