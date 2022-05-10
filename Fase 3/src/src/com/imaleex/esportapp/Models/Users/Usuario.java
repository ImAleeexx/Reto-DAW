package com.imaleex.esportapp.Models.Users;

/**
 * @author Alex Cortes
 */
public class Usuario {
    private int id;
    private String nombre;
    private String clave;


    public Usuario() {
    }

    public Usuario(int id, String nombre, String clave) {
        this.id = id;
        this.nombre = nombre;
        this.clave = clave;
    }


    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }
}
