package com.imaleex.esportapp.Models.Personas;

/**
 * @author Alex Cortes
 */
public class Jugador extends Persona{
    private String nickname;
    private Rol rol;
    private double sueldo;


    public Jugador(String dni, String nombre, String telfono, String direccion, String nickname, Rol rol, double sueldo) {
        super(dni, nombre, telfono);
        this.nickname = nickname;
        this.rol = rol;
        this.sueldo = sueldo;
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

}
