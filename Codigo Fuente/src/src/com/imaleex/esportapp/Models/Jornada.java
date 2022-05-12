package com.imaleex.esportapp.Models;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author Alex Cortes
 */
public class Jornada {
    private int id;
    private LocalDate fecha;
    private ArrayList<Partido> listaPartidos;

    public Jornada(int id, LocalDate fecha) {
        this.id = id;
        this.fecha = fecha;
    }


    public int getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

}
