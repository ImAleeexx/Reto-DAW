package com.imaleex.esportapp.Models.Personas;

/**
 * @author Alex Cortes
 */
public class Entrenador extends Persona {
    private double sueldo;

    public Entrenador(int id, String dni, String nombre, String telefono, double sueldo) {
        super(id, dni, nombre, telefono);
        this.sueldo = sueldo;
    }

    public Entrenador() {
        super();
    }

    public Entrenador(int id) {
        super(id);
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

}
