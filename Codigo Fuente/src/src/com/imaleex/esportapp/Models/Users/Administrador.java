package com.imaleex.esportapp.Models.Users;

/**
 * @author Alex Cortes
 */
public class Administrador extends Usuario {

    public Administrador(int id, String nombre, String clave, int type) {
        super(id, nombre, clave, type);
    }

    private boolean checkPermission(){
        if (this.type == 1){
            return true;
        } else {
            return false;
        }
    }

}
