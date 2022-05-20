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

    public static void showErrorMessage(String msgBox) {
        JOptionPane.showMessageDialog(null, msgBox, "Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
    }

    public static void showInfoMessage(String msgBox) {
        JOptionPane.showMessageDialog(null, msgBox);
    }

    /*
        METODOS GENERALES PARA TRATAMIENTO DE VARIABLES
     */


    public static String inputAccNumber(String msgBox) {
        String var = String.valueOf(inputBigInteger("Introduce el numero de cuenta"));
        if (var.length() != 20) {
            showErrorMessage("El numero de cuenta debe de ser un numero de 20 digitos");
            var = inputString(msgBox, true,  null);
        }

        return var;
    }

    public static String inputString(String msgBox, boolean forceLoop, String defaultVal) {
        String var = (String) JOptionPane.showInputDialog(null, msgBox, "Introduce un valor", JOptionPane.QUESTION_MESSAGE, null, null, defaultVal);

        if (var == null || var.isEmpty()) {
            if (forceLoop){
                var = inputString(msgBox, forceLoop, defaultVal);
                showErrorMessage("El dato introducido no es valido");
            } else
                throw new NullPointerException();
        }

        return var;
    }

    public static int inputInt(String msgBox) {
        int var = -1;

        try {
            var = Integer.parseInt(JOptionPane.showInputDialog(msgBox));
        } catch (Exception e) {
            showErrorMessage("El dato introducido no es valido");
            var = inputInt(msgBox);
        }

        return var;
    }

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

    public static BigInteger inputBigInteger(String msgBox) {
        BigInteger var;

        try {
            var = new BigInteger(JOptionPane.showInputDialog(msgBox));
        } catch (Exception e) {
            showErrorMessage("El dato introducido no es valido");
            var = inputBigInteger(msgBox);
        }

        return var;
    }


    public static double inputDouble(String msgBox) {
        double var = -1;

        try {
            var = Double.parseDouble(JOptionPane.showInputDialog(msgBox));
        } catch (Exception e) {
            showErrorMessage("El dato introducido no es valido");
            var = inputDouble(msgBox);
        }

        return var;
    }

    public static LocalDate parseDateString(String dateString){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateString, dateTimeFormatter);
    }

    public static LocalDate inputDate(String msgBox) {
        LocalDate var;
        try {
            var = parseDateString(inputString(msgBox, true, null));
        } catch (Exception e) {
            showErrorMessage("El dato introducido no es valido");
            var = inputDate(msgBox);
        }
        return var;
    }

}
