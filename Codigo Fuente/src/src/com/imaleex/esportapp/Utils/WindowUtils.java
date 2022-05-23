package com.imaleex.esportapp.Utils;

import javax.swing.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Alex Cortes
 */
public class WindowUtils {
         /*
        METODOS GENERALES PARA UTILIDADES DE MENSAJES
     */

    /**
     * muestra un mensaje de error
     * @param msgBox Mensaje que se mostrara en la ventana
     */
    public static void showErrorMessage(String msgBox) {
        JOptionPane.showMessageDialog(null, msgBox, "Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * muestra un mensaje de informacion
     * @param msgBox mensaje que se mostrara en la ventana
     */
    public static void showInfoMessage(String msgBox) {
        JOptionPane.showMessageDialog(null, msgBox);
    }

    /*
        METODOS GENERALES PARA TRATAMIENTO DE VARIABLES
     */

    /**
     * Muestra un mensaje y da opcion de aceptar o cancelar
     * @param msgBox mensaje que se mostrara en la ventana
     * @return true si se presiona aceptar, false si se presiona cancelar
     */
    public static boolean inputBoolean(String msgBox) {
        boolean var = false;

        try {
            int input = JOptionPane.showConfirmDialog(null, msgBox, "Selecciona una opcion", JOptionPane.YES_NO_OPTION);
            if (input == JOptionPane.YES_OPTION)
                var = true;
            else if (input == JOptionPane.NO_OPTION)
                var = false;
            else
                throw new Exception();
        } catch (Exception e) {
            showErrorMessage("El dato introducido no es valido");
        }
        return var;
    }


}
