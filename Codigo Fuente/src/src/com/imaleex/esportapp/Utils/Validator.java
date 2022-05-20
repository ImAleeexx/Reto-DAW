package com.imaleex.esportapp.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Alex Cortes
 */

public class Validator {
    public static boolean checkRegex(String regex, String validateString){
        Pattern p = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher m = p.matcher(validateString);
        return m.find();
    }

    public static boolean checkDouble(String doubleString){
        try{
            Double.parseDouble(doubleString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean checkEmail(String email){
        String regex = "(^[a-zA-Z0-9_.]+[@]{1}[a-z0-9]+[\\.][a-z]+$)";
        //TODO: fallo al validar el email
        if (!email.isEmpty()){
            return true;
        }
        return checkRegex(regex, email);
    }

    public static boolean checkTel(String tel){
        String regex = "^((6|7)[ -]*([0-9][ -]*){8})$";
        return checkRegex(regex, tel);
    }

    public static boolean checkCp(String cp){
        String regex = "^([0-9][ -]*){5}$";
        return checkRegex(regex, cp);
    }

    public static boolean checkName(String name){
        String regex = "^[\\p{L}'][ \\p{L}'-]*[\\p{L}]$";
        return checkRegex(regex, name);
    }

    public static boolean checkDni(String dni){
        try{
            int dniInt = Integer.parseInt(dni.substring(0, dni.length() - 1));
            String dniLetter = dni.substring(dni.length()-1);
            char dniChars[] = {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
            int moduleDni = dniInt%23;
            return dniLetter.equalsIgnoreCase(String.valueOf(dniChars[moduleDni]));
        } catch (Exception e) {
            return false;
        }
    }


    public static boolean checkSegSoc(String segSocial){
        try{
            if (segSocial.length() != 12){return false;}
            int countryPart = Integer.parseInt(segSocial.substring(0, 2));
            if (countryPart > 53){return false;}
            int numberWithCountry = Integer.parseInt(segSocial.substring(0, 10));
            int controlPart = Integer.parseInt(segSocial.substring(10, 12));
            int moduleSs = numberWithCountry%97;
            return moduleSs == controlPart;
        } catch (Exception e) {
            return false;
        }
    }



}