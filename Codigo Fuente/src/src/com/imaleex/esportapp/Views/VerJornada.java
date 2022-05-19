package com.imaleex.esportapp.Views;

import com.imaleex.esportapp.Db.Dao.PartidoDAO;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Models.Equipo;
import com.imaleex.esportapp.Models.Jornada;
import com.imaleex.esportapp.Models.Partido;
import com.imaleex.esportapp.Utils.WindowUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VerJornada extends JFrame {

    // versión
    private static final long serialVersionUID = 1L;

    // la tabla
    private final JTable table;

    private VerJornada verJornada;
    // el modelo de tabla, aquí van a estar los datos.
    private final DefaultTableModel model;

    private int[] idPartidos;
    private  TextField tfJornada;
    // constructor del frame que contruye toda la ventana...
    public VerJornada() {
        //título
        setTitle("Jornada");
        // cuando cerramos la ventana se cierra la aplicación por completo
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // dimensiones y posición
        setBounds(100, 100, 596, 331);
        // establece una capa absoluta para posicionar los elementos donde queramos
        getContentPane().setLayout(null);
        verJornada = this;
        // el panel con barras de scroll automáticas
        JScrollPane scrollPane = new JScrollPane();
        // dimensiones y posición del panel de scroll
        scrollPane.setBounds(10, 11, 560, 227);

        // se añade el panel de scroll a la ventana
        getContentPane().add(scrollPane);

        // nombre de las columnas
        String[] columnNames = {"Equipo Local", "Marcador Local", "Marcador Visitante", "Equipo Visitante"};

        // creo un modelo de datos, sin datos por eso 'null' y establezco los
        // nombres de columna
        model = new DefaultTableModel(null, columnNames);
        // creo la tabla con el modelo de datos creado
        table = new JTable(model){
            // para que no se pueda editar la tabla
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        //modelo de la selección
        ListSelectionModel listModel = table.getSelectionModel();
        listModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(2);
        table.getColumnModel().getColumn(2).setPreferredWidth(50);
        table.getColumnModel().getColumn(3).setPreferredWidth(2);
        // se pone la tabla en el scroll
        scrollPane.setViewportView(table);

        // código del botón
        JButton buscar = new JButton("Buscar Jornada");
        tfJornada = new TextField();
        tfJornada.setBounds(200, 249, 50, 23);
        buscar.setBounds(10, 249, 150, 23);
        getContentPane().add(tfJornada);
        getContentPane().add(buscar);

        buscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Search in the database jornada 1
                model.setRowCount(0);

                ArrayList<Partido> partidos = new ArrayList<Partido>();
                Jornada jornada = new Jornada();
                try {
                    jornada.setId(Integer.parseInt(tfJornada.getText()));
                } catch (NumberFormatException e) {
                    WindowUtils.showErrorMessage("Introduzca un número");
                }
                try {
                    partidos = PartidoDAO.listaPartidosByJornada(jornada);
                } catch (DbException e) {
                    throw new RuntimeException(e);
                }
                idPartidos = new int[partidos.size()];
                for (int i = 0; i < partidos.size(); i++) {

                    Partido partido = partidos.get(i);
                    Equipo equipoLocal = partido.getLocal();
                    Equipo equipoVisitante = partido.getVisitante();
                    String marcadorLocal = String.valueOf(partido.getMarcadorLocal()).equals("null") ? "-" : String.valueOf(partido.getMarcadorLocal());
                    String marcadorVisitante = String.valueOf(partido.getMarcadorVisitante()).equals("null") ? "-" : String.valueOf(partido.getMarcadorVisitante());
                    Object[] aux = new Object[]{equipoLocal.getNombre(),marcadorLocal, marcadorVisitante, equipoVisitante.getNombre()};
                    idPartidos[i] = partido.getId();
                    model.addRow(aux);

                }
            }
        });

        JButton boton_Modificar = new JButton("Modificar");
        boton_Modificar.setBounds(270, 249, 100, 23);

        getContentPane().add(boton_Modificar);
        JButton boton_Guardar = new JButton("Guardar");
        boton_Guardar.setBounds(370, 249, 100, 23);
        getContentPane().add(boton_Guardar);
        boton_Guardar.setVisible(false);
        boton_Modificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                IntroduccionResultados.main(idPartidos[table.getSelectedRow()],  verJornada);
            }
        });
    }
    public void  cargarJornada( ){
        model.setRowCount(0);

        ArrayList<Partido> partidos = new ArrayList<Partido>();
        Jornada jornada = new Jornada();
        try {
            jornada.setId(Integer.parseInt(tfJornada.getText()));
        } catch (NumberFormatException e) {
            WindowUtils.showErrorMessage("Introduzca un número");
        }
        try {
            partidos = PartidoDAO.listaPartidosByJornada(jornada);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
        idPartidos = new int[partidos.size()];
        for (int i = 0; i < partidos.size(); i++) {

            Partido partido = partidos.get(i);
            Equipo equipoLocal = partido.getLocal();
            Equipo equipoVisitante = partido.getVisitante();
            String marcadorLocal = String.valueOf(partido.getMarcadorLocal()).equals("null") ? "-" : String.valueOf(partido.getMarcadorLocal());
            String marcadorVisitante = String.valueOf(partido.getMarcadorVisitante()).equals("null") ? "-" : String.valueOf(partido.getMarcadorVisitante());
            Object[] aux = new Object[]{equipoLocal.getNombre(),marcadorLocal, marcadorVisitante, equipoVisitante.getNombre()};
            idPartidos[i] = partido.getId();
            model.addRow(aux);

        }
    }

    // función principal
    public static void main() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VerJornada frame = new VerJornada();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}