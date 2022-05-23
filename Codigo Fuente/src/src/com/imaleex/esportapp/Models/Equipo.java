package com.imaleex.esportapp.Models;

import com.imaleex.esportapp.Controllers.AdminController;
import com.imaleex.esportapp.Exceptions.DataNotFoundException;
import com.imaleex.esportapp.Exceptions.DbException;
import com.imaleex.esportapp.Models.Personas.Dueno;
import com.imaleex.esportapp.Models.Personas.Entrenador;
import com.imaleex.esportapp.Models.Personas.Jugador;

import java.util.ArrayList;

/**
 * <h1>Clase Equipo</h1>
 *
 * @author Alex Cortes
 * @author Aritz Castillo
 * @version 1.0
 */
public class Equipo {
    private int id;
    private String nombre;
    private Entrenador entrenador;
    private Entrenador entrenadorAsistente;
    private Dueno dueno;
    private ArrayList<Jugador> jugadores;

    /**
     * Constructor vacio
     */
    public Equipo() {
    }

    /**
     * Constructor con todos los parametros
     * @param id id del equipo
     * @param nombre nombre del equipo
     */
    public Equipo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * Devuelve el id del equipo
     * @return id del equipo
     */
    public int getId() {
        return id;
    }

    /**
     * Devuelve el id del equipo
     * @param id id del equipo
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre del equipo
     * @return nombre del equipo
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setea el nombre del equipo
     * @param nombre nombre del equipo
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el entrenador del equipo
     * @return el entrenador del equipo o null si no tiene
     */
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

    /**
     * Setea el entrenador del equipo
     * @param entrenador el entrenador del equipo
     */
    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    /**
     * Devuelve el entrenador asistente del equipo
     * @return entrenador asistente del equipo o null si no tiene
     */
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

    /**
     * Setea el entrenador asistente del equipo
     * @param entrenadorAsistente entrenador asistente del equipo
     */
    public void setEntrenadorAsistente(Entrenador entrenadorAsistente) {
        this.entrenadorAsistente = entrenadorAsistente;
    }

    /**
     * Devuelve el dueno del equipo
     * @return el dueno del equipo o null si no tiene
     */
    public Dueno getDueno() {
        try {
            Integer id = this.dueno.getId();
            if (dueno.getNombre() == null && !id.equals(0)) {
                setDueno(AdminController.buscarDuenoId(id));
            }
        } catch (DataNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            return null;
        }
        return dueno;
    }

    /**
     * Setea el dueno del equipo
     * @param dueno el dueno del equipo
     */
    public void setDueno(Dueno dueno) {
        this.dueno = dueno;
    }

    /**
     * Devuelve lista de jugadores del equipo
     * @return lista de jugadores del equipo
     */
    public ArrayList<Jugador> getJugadores() {
        if (jugadores == null) {
            AdminController.loadJugadoresToEquipo(this);
        }
        return jugadores;
    }

    /**
     * Setea la lista de jugadores del equipo
     * @param jugadores lista de jugadores del equipo
     */
    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }



    @Override
    public String toString() {
        return this.nombre;
    }
}
