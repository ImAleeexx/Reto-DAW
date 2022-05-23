package com.imaleex.esportapp.Models.Personas;

/**
 * <h1>Clase Dueños</h1></h1>
 *
 * @author Alex Cortes
 * @author Aritz Castillo
 * @version 1.0
 */

public class Dueno extends Persona {
    private String email;

    /**
     * @param id id de la persona
     * @param dni dni del dueño
     * @param nombre nombre del dueño
     * @param telefono telefono del dueño
     * @param email email del dueño
     */
    public Dueno(int id, String dni, String nombre, String telefono, String email) {
        super(id, dni, nombre, telefono);
        this.email = email;
    }

    /**
     * Constructor vacio
     */
    public Dueno() {
        super();
    }

    /**
     * Contructor con id
     * @param id id de la persona
     */
    public Dueno(int id) {
        super(id);
    }

    /**
     * Retorna el email del dueño
     * @return email del dueño
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setea el email del dueño
     * @param email email del dueño
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
