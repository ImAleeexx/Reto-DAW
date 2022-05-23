package com.imaleex.esportapp.Models.Personas;

/**
 * <h1>Clase Dueños</h1></h1>
 *
 * @author Alex Cortes
 * @author Aritz Castillo
 * @version 1.0
 */
public class Entrenador extends Persona {
    private double sueldo;

    /**
     * Constructor con parametros
     * @param id  id de persona
     * @param dni dni del entrenador
     * @param nombre nombre del entrenador
     * @param telefono telefono del entrenador
     * @param sueldo sueldo del entrenador
     */
    public Entrenador(int id, String dni, String nombre, String telefono, double sueldo) {
        super(id, dni, nombre, telefono);
        this.sueldo = sueldo;
    }

    /**
     * Constructor vacio
     */
    public Entrenador() {
        super();
    }

    /**
     * Constructor con id
     * @param id id de persona
     */
    public Entrenador(int id) {
        super(id);
    }

    /**
     * Metodo para obtener el sueldo del entrenador
     * @return sueldo del entrenador
     */
    public double getSueldo() {
        return sueldo;
    }

    /**
     * Metodo para establecer el sueldo del entrenador
     * @param sueldo sueldo del entrenador
     */
    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

}
