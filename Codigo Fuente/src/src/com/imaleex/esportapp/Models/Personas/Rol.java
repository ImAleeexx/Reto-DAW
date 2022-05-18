package com.imaleex.esportapp.Models.Personas;

/**
 * @author Alex Cortes
 */
public enum Rol {
    IGL ("In Game Leader"),
    AWPER ("AWP Player"),
    ENTRY_FRAGGER ("Entry Fragger"),
    SUPPORT ("Support"),
    LURKER ("Lurker");

    private String nombre;

    Rol(String nombre) {
        this.nombre = nombre;
    }


    public String getNombre() {
        return nombre;
    }
}
