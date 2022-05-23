package com.imaleex.esportapp.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Alex Cortes
 * @author Aritz Castillo
 */

public class Validator {

    /**
     * Metodo para validar una cadena de texto con una expresion regular
     * @param regex regex a validar
     * @param validateString string a validar
     * @return true si el string cumple con el regex
     */
    public static boolean checkRegex(String regex, String validateString) {
        Pattern p = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher m = p.matcher(validateString);
        return m.find();
    }

    /**
     * Metodo para validar una cadena de texto si es un double
     * @param doubleString string a validar
     * @return true si el string es un double
     */
    public static boolean checkDouble(String doubleString) {
        try {
            Double.parseDouble(doubleString);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * Metodo para validar una cadena de texto si es un email
     * @param email email a validar
     * @return  true si el email es valido
     */
    public static boolean checkEmail(String email) {
        String regex = "(^[a-zA-Z0-9_.]+[@]{1}[a-z0-9]+[\\.][a-z]+$)";
        //TODO: fallo al validar el email
        if (!email.isEmpty()) {
            return true;
        }
        return checkRegex(regex, email);
    }

    /**
     * Metodo para validar una cadena de texto si es una telefono
     * @param tel tel a validar
     * @return true si el tel es valido
     */
    public static boolean checkTel(String tel) {
        String regex = "^((6|7)[ -]*([0-9][ -]*){8})$";
        return checkRegex(regex, tel);
    }

    /**
     * Metodo para validar una cadena de texto si es una nomnbre valido
     * @param name nombre a validar
     * @return true si el nombre es valido
     */
    public static boolean checkName(String name) {
        String regex = "^[\\p{L}'][ \\p{L}'-]*[\\p{L}]$";
        return checkRegex(regex, name);
    }

    /**
     * Metodo para validar una cadena de texto si es un dni valido
     * @param dni dni a validar
     * @return true si el dni es valido
     */
    public static boolean checkDni(String dni) {
        try {
            int dniInt = Integer.parseInt(dni.substring(0, dni.length() - 1));
            String dniLetter = dni.substring(dni.length() - 1);
            char[] dniChars = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
            int moduleDni = dniInt % 23;
            return dniLetter.equalsIgnoreCase(String.valueOf(dniChars[moduleDni]));
        } catch (Exception e) {
            return false;
        }
    }




}