package com.imaleex.esportapp.Models.Personas;

/**
 * @author Alex Cortes
 */
public class Dueno extends Persona{
    private String email;

    public Dueno(int id, String dni, String nombre, String telefono, String email) {
        super(id, dni, nombre, telefono);
        this.email = email;
    }

    public Dueno() {
        super();
    }

    public Dueno(int id){
        super(id);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
