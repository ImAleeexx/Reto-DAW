package com.imaleex.esportapp.Models;

import com.imaleex.esportapp.Models.Personas.Dueno;
import com.imaleex.esportapp.Models.Personas.Entrenador;
import com.imaleex.esportapp.Models.Personas.Jugador;

import java.util.ArrayList;

/**
 * @author Alex Cortes
 */
public class Equipo {
    private int id;
    private String nombre;
    private Entrenador entrenador;
    private Entrenador entrenadorAsistente;
    private Dueno dueno;
    private ArrayList<Jugador> jugadores;

    public Equipo() {
    }

    public Equipo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
