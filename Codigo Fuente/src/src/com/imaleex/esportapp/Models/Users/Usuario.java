package com.imaleex.esportapp.Models.Users;

/**
 * @author Alex Cortes
 */
public class Usuario {
    protected int id;
    protected String nombre;
    protected String clave;
    protected int type;


    /**
     * Constructor vacio
     */
    public Usuario() {
    }

    /**
     * Constructor con parametros
     * @param id  id del usuario
     * @param nombre nombre del usuario
     * @param clave clave del usuario hasheada
     * @param type tipo de usuario
     */
    public Usuario(int id, String nombre, String clave, int type) {
        this.id = id;
        this.nombre = nombre;
        this.clave = clave;
        this.type = type;
    }


    /**
     * Retorna el id del usuario
     * @return id del usuario
     */
    public int getId() {
        return id;
    }

    /**
     * Retorna el nombre del usuario
     * @return nomnbre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setea el nombre del usuario
     * @param nombre nombre del usuario
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna la clave del usuario hasheada
     * @return la clave hasheada
     */
    public String getClave() {
        return clave;
    }

    /**
     * Setea la clave del usuario hasheada
     * @param clave  clave hasheada
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * Retorna el tipo de usuario
     * @return tipo de usuario 0: usuario normal, 1: admin
     */
    public int getType() {
        return type;
    }

    /**
     * Setea el tipo de usuario
     * @param type tipo de usuario
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Retorna true si el usuario es administrador
     * @return true si el usuario es administrador
     */
    public boolean checkAdmin() {
        return this.type == 1;
    }

    /**
     * Setea el id del usuario
     * @param id id del usuario
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return String con el nombre del usuario
     */
    @Override
    public String toString() {
        return getNombre();
    }
}
