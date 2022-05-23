package com.imaleex.esportapp.Models.Users;

/**
 * @author Alex Cortes
 */
public class Usuario {
    protected int id;
    protected String nombre;
    protected String clave;
    protected int type;


    public Usuario() {
    }

    public Usuario(int id, String nombre, String clave, int type) {
        this.id = id;
        this.nombre = nombre;
        this.clave = clave;
        this.type = type;
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

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean checkAdmin() {
        return this.type == 1;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
