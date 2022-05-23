package com.imaleex.esportapp.Models.Personas;

import com.imaleex.esportapp.Controllers.AdminController;
import com.imaleex.esportapp.Exceptions.DataNotFoundException;
import com.imaleex.esportapp.Models.Equipo;

/**
 * <h1>Clase Jornada</h1>
 *
 * @author Alex Cortes
 * @author Aritz Castillo
 * @version 1.0
 */
public class Jugador extends Persona {
    private String nickname;
    private Rol rol;
    private double sueldo;
    private Equipo equipo;


    /**
     * Constructor de la clase Jugador
     * @param id id de persona
     * @param dni dni de jugador
     * @param nombre nombre de jugador
     * @param telefono telefono de jugador
     * @param nickname nickname de jugador
     * @param rol rol de jugador
     * @param sueldo sueldo de jugador
     * @param equipo equipo de jugador
     */
    public Jugador(int id, String dni, String nombre, String telefono, String nickname, Rol rol, double sueldo, Equipo equipo) {
        super(id, dni, nombre, telefono);
        this.nickname = nickname;
        this.rol = rol;
        this.sueldo = sueldo;
        this.equipo = equipo;
    }

    /**
     * Constructor vacio
     */
    public Jugador() {
        super();
    }

    /**
     * Constructor con parametro id
     * @param id  id de persona
     */
    public Jugador(int id) {
        super(id);
    }

    /**
     * Metodo para obtener el nickname de jugador
     * @return  nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Metodo para setear el nickname de jugador
     * @param nickname  nickname de jugador
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Metodo para obtener el rol de jugador
     * @return rol de jugador
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * Metodo para setear el rol de jugador
     * @param rol rol de jugador
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    /**
     * Metodo para obtener el sueldo de jugador
     * @return sueldo de jugador
     */
    public double getSueldo() {
        return sueldo;
    }

    /**
     * Metodo para setear el sueldo de jugador
     * @param sueldo sueldo de jugador
     */
    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    /**
     * Metodo para obtener el equipo de jugador
     * @return equipo de jugador o null si no tiene equipo
     */
    public Equipo getEquipo() {
        try {
            Integer id = this.equipo.getId();
            if (equipo.getNombre() == null && !id.equals(0)) {
                setEquipo(AdminController.buscarEquipoId(id));
            }
        } catch (DataNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            return null;
        }

        return equipo;
    }

    /**
     * Metodo para setear el equipo de jugador
     * @param equipo equipo de jugador
     */
    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
}
