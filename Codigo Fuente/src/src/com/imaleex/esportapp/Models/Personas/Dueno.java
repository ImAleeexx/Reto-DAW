package com.imaleex.esportapp.Models.Personas;

/**
 * @author Alex Cortes
 */
public class Dueno extends Persona{
    private String email;

    public Dueno(String dni, String nombre, String telfono, String email) {
        super(dni, nombre, telfono);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
