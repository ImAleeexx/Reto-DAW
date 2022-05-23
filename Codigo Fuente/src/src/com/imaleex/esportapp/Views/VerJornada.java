package com.imaleex.esportapp.Views;

import com.imaleex.esportapp.Controllers.AdminController;
import com.imaleex.esportapp.Controllers.UserController;
import com.imaleex.esportapp.Db.Dao.PartidoDAO;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Main;
import com.imaleex.esportapp.Models.Equipo;
import com.imaleex.esportapp.Models.Jornada;
import com.imaleex.esportapp.Models.Partido;
import com.imaleex.esportapp.Utils.WindowUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class VerJornada extends JFrame {

    // versión
    private static final long serialVersionUID = 1L;

    // la tabla
    private final JTable table;
    // el modelo de tabla, aquí van a estar los datos.
    private final DefaultTableModel model;
    private final VerJornada verJornada;
    private final JComboBox<Jornada> cbJornada;
    private int[] idPartidos;
    private Jornada jornada;
    private ArrayList<Jornada> jornadas;

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
        table = new JTable(model) {
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
        cbJornada = new JComboBox<>();
        llenarCBJornadas();
        cbJornada.setBounds(150, 249, 150, 23);
        buscar.setBounds(10, 249, 150, 23);
        getContentPane().add(cbJornada);
        getContentPane().add(buscar);
        buscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                jornada = (Jornada) cbJornada.getSelectedItem();
                llenarTabla(jornada);
            }
        });

        JButton boton_Modificar = new JButton("Modificar");
        boton_Modificar.setBounds(330, 249, 100, 23);

        getContentPane().add(boton_Modificar);
        if (!Main.user.checkAdmin()) {
            boton_Modificar.setVisible(false);
        }
        boton_Modificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                IntroduccionResultados.main(idPartidos[table.getSelectedRow()], verJornada);
            }
        });

        JButton boton_volver = new JButton("Volver");
        boton_volver.setBounds(430, 249, 100, 23);

        getContentPane().add(boton_volver);
        boton_volver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dispose();
                if (Main.user.checkAdmin()) {
                    AdminView.main();
                } else {
                    UserView.main();
                }
            }
        });


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

    private void llenarTabla(Jornada jornada) {
        //Search in the database jornada 1
        model.setRowCount(0);

        ArrayList<Partido> partidos = new ArrayList<>();
        try {
            assert this.jornada != null;
            partidos = UserController.listaPartidosByJornada(jornada);
        } catch (DbException e) {
            WindowUtils.showErrorMessage(e.getMessage());
        } catch (NullPointerException e) {
            //WindowUtils.showErrorMessage("No hay jornadas creadas");
        }
        loadValues(partidos);
    }

    private void loadValues(ArrayList<Partido> partidos) {
        idPartidos = new int[partidos.size()];
        for (int i = 0; i < partidos.size(); i++) {

            Partido partido = partidos.get(i);
            Equipo equipoLocal = partido.getLocal();
            Equipo equipoVisitante = partido.getVisitante();
            String marcadorLocal = String.valueOf(partido.getMarcadorLocal()).equals("null") ? "-" : String.valueOf(partido.getMarcadorLocal());
            String marcadorVisitante = String.valueOf(partido.getMarcadorVisitante()).equals("null") ? "-" : String.valueOf(partido.getMarcadorVisitante());
            Object[] aux = new Object[]{equipoLocal.getNombre(), marcadorLocal, marcadorVisitante, equipoVisitante.getNombre()};
            idPartidos[i] = partido.getId();
            model.addRow(aux);

        }
    }

    public void cargarJornada() {
        model.setRowCount(0);

        ArrayList<Partido> partidos;
        try {
            partidos = PartidoDAO.listaPartidosByJornada(jornada);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
        loadValues(partidos);
    }


    private Jornada getNearestJornada() {
        if (jornadas.size() < 1) {
            WindowUtils.showErrorMessage("No hay jornadas creadas");
            return null;
        }
        final long now = System.currentTimeMillis() / 1000;
        ZoneId zoneId = ZoneId.systemDefault();
        return Collections.min(jornadas, new Comparator<Jornada>() {
            public int compare(Jornada j1, Jornada j2) {
                long diff1 = Math.abs(j1.getFecha().atStartOfDay(zoneId).toEpochSecond() - now);
                long diff2 = Math.abs(j2.getFecha().atStartOfDay(zoneId).toEpochSecond() - now);
                return Long.compare(diff1, diff2);
            }
        });
    }

    private void llenarCBJornadas() {
        try {
            jornadas = AdminController.getJornadas();
            cbJornada.removeAllItems();

            for (Jornada jornada : jornadas) {
                cbJornada.addItem(jornada);
            }
            Jornada nearestJornada = getNearestJornada();
            cbJornada.setSelectedItem(nearestJornada);
            llenarTabla(nearestJornada);
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
    }
}