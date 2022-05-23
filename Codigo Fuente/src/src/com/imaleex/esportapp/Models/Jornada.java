package com.imaleex.esportapp.Models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * <h1>Clase Jornada</h1>
 *
 * @author Alex Cortes
 * @author Aritz Castillo
 * @version 1.0
 */
public class Jornada {
    private int id;
    private LocalDate fecha;
    private ArrayList<Partido> listaPartidos;

    /**
     * Constructor de la clase Jornada
     * @param id id de la jornada
     * @param fecha fecha de la jornada
     */
    public Jornada(int id, LocalDate fecha) {
        this.id = id;
        this.fecha = fecha;
    }

    /**
     * Constructor vacio
     */
    public Jornada() {

    }


    /**
     * Metodo para obterner la id de la jornada
     * @return id de la jornada
     */
    public int getId() {
        return id;
    }

    /**
     * Metodo para obtener la fecha de la jornada
     * @return fecha de la jornada
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Metodo para setear la id de la jornada
     * @param id id de la jornada
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Metodo para setear la fecha de la jornada
     * @param fecha fecha de la jornada
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.fecha.format(formatter);
    }

}
