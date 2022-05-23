package com.imaleex.esportapp.Models.Personas;

/**
 * <h1>Clase padre Pesrona</h1></h1>
 *
 * @author Alex Cortes
 * @author Aritz Castillo
 * @version 1.0
 */
public abstract class Persona {
    private int id;
    private String dni;
    private String nombre;
    private String telefono;


    /**
     * Constructor de la clase Persona
     * @param id id de la persona
     * @param dni dni de la persona
     * @param nombre nombre de la persona
     * @param telefono telefono de la persona
     */
    public Persona(int id, String dni, String nombre, String telefono) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Persona(String dni, String nombre, String telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    /**
     * Constructor vacio
     */
    public Persona() {
    }

    /**
     * Constructor con id
     * @param id id de la persona
     */
    public Persona(int id) {
        this.id = id;
    }

    /**
     * Metodo para obtener el dni de la persona
     * @return dni de la persona
     */
    public String getDni() {
        return dni;
    }

    /**
     * Metodo para setear el dni de la persona
     * @param dni dni de la persona
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Metodo para obtener el nombre de la persona
     * @return nombre de la persona
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo para setear el nombre de la persona
     * @param nombre nombre de la persona
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo para obtener el telefono de la persona
     * @return telefono de la persona
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Metodo para setear el telefono de la persona
     * @param telefono telefono de la persona
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Metodo para obtener el id de la persona
     * @return id de la persona
     */
    public int getId() {
        return id;
    }

    /**
     * Metodo para setear el id de la persona
     * @param id id de la persona
     */
    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        if (getNombre() == null) {
            return "";
        } else {
            return getNombre() + " " + getDni();
        }
    }

}
