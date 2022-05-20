package com.imaleex.esportapp.Models.Personas;

/**
 * @author Alex Cortes
 */
public abstract class Persona {
    private int id;
    private String dni;
    private String nombre;
    private String telefono;


    public Persona(int id,String dni, String nombre, String telefono) {
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

    public Persona(){}

    public Persona(int id) {
        this.id = id;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString(){
        if(getNombre() == null){
            return "";
        } else{
            return getNombre() + " " + getDni();
        }
    }

}
