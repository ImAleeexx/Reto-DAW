package com.imaleex.esportapp.Models.Personas;

/**
 * @author Alex Cortes
 */
public abstract class Persona {
    private String dni;
    private String nombre;
    private String telfono;


    public Persona(String dni, String nombre, String telfono) {
        this.dni = dni;
        this.nombre = nombre;
        this.telfono = telfono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelfono() {
        return telfono;
    }

    public void setTelfono(String telfono) {
        this.telfono = telfono;
    }
}
