package com.imaleex.esportapp.Models.Personas;

/**
 * <h1>Enum de los Roles</h1></h1>
 *
 * @author Alex Cortes
 * @author Aritz Castillo
 * @version 1.0
 */
public enum Rol {
    IGL("In Game Leader"),
    AWPER("AWP Player"),
    ENTRY_FRAGGER("Entry Fragger"),
    SUPPORT("Support"),
    LURKER("Lurker");

    private final String nombre;

    /**
     * Constructor de la clase Rol
     * @param nombre nombre del rol
     */
    Rol(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo que devuelve el rol
     * @param nombre nombre del rol
     * @return el rol correspondiente al nombre
     */
    public static Rol getRol(String nombre) {
        for (Rol rol : Rol.values()) {
            if (rol.getNombre().equals(nombre)) {
                return rol;
            }
        }
        return null;
    }


    /**
     * Metodo que devuelve el nombre del rol
     * @return nombre del rol
     */
    public String getNombre() {
        return nombre;
    }
}
