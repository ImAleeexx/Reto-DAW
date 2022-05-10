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

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public Entrenador getEntrenadorAsistente() {
        return entrenadorAsistente;
    }

    public void setEntrenadorAsistente(Entrenador entrenadorAsistente) {
        this.entrenadorAsistente = entrenadorAsistente;
    }

    public Dueno getDueno() {
        return dueno;
    }

    public void setDueno(Dueno dueno) {
        this.dueno = dueno;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    //add jugador to arraylist
    public void addJugador(Jugador jugador) {
        this.jugadores.add(jugador);
    }
}
