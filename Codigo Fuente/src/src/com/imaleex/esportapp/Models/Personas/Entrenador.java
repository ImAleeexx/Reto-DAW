package com.imaleex.esportapp.Models.Personas;

/**
 * @author Alex Cortes
 */
public class Entrenador extends Persona {
    private double sueldo;

    public Entrenador(String dni, String nombre, String telefono, double sueldo) {
        super(dni, nombre, telefono);
        this.sueldo = sueldo;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }
}
