package com.imaleex.esportapp.Models;

import com.imaleex.esportapp.Controllers.AdminController;
import com.imaleex.esportapp.Exceptions.DataNotFoundException;
import com.imaleex.esportapp.Exceptions.DbException;
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
        try {
            Integer id = this.entrenador.getId();
            if (entrenador.getNombre() == null && !id.equals(0)) {
                setEntrenador(AdminController.buscarEntrenadorId(id));
            }
        } catch (DbException | DataNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            return null;
        }
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public Entrenador getEntrenadorAsistente() {
        try {
            Integer id = this.entrenadorAsistente.getId();
            if (entrenadorAsistente.getNombre() == null && !id.equals(0)) {
                setEntrenadorAsistente(AdminController.buscarEntrenadorId(id));
            }
        } catch (DbException | DataNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            return null;
        }
        return entrenadorAsistente;
    }

    public void setEntrenadorAsistente(Entrenador entrenadorAsistente) {
        this.entrenadorAsistente = entrenadorAsistente;
    }

    public Dueno getDueno() {
        if (dueno.getNombre() == null) {
            try {
                setDueno(AdminController.buscarDuenoId(dueno.getId()));
            } catch (DataNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
        return dueno;
    }

    public void setDueno(Dueno dueno) {
        this.dueno = dueno;
    }

    public ArrayList<Jugador> getJugadores() {
        if (jugadores == null) {
            AdminController.loadJugadoresToEquipo(this);
        }
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
