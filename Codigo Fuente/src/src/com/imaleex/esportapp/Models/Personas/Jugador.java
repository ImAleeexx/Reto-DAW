package com.imaleex.esportapp.Models.Personas;

import com.imaleex.esportapp.Models.Equipo;

/**
 * @author Alex Cortes
 */
public class Jugador extends Persona{
    private String nickname;
    private Rol rol;
    private double sueldo;
    private Equipo equipo;


    public Jugador(int id, String dni, String nombre, String telefono, String nickname, Rol rol, double sueldo, Equipo equipo) {
        super(id, dni, nombre, telefono);
        this.nickname = nickname;
        this.rol = rol;
        this.sueldo = sueldo;
        this.equipo = equipo;
    }

    public Jugador() {
        super();
    }

    public Jugador(int id){
        super(id);
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
}
