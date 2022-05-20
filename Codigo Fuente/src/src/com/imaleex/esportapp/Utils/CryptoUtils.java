package com.imaleex.esportapp.Utils;


import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Random;

import static com.imaleex.esportapp.Utils.WindowUtils.showErrorMessage;

/**
 * @author Alex Cortes
 */
public class CryptoUtils {

    public static String hashFunc(String input){
        try {
            String  salt = saltGenerator();
            int iterations = 1000;
            KeySpec spec = new PBEKeySpec(
                    input.toCharArray(),
                    Base64.getDecoder().decode(salt),
                    iterations,
                    64 * 8
            );

            SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            return iterations+":"+salt+":"+Base64.getEncoder().withoutPadding().encodeToString(f.generateSecret(spec).getEncoded());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public static String saltGenerator(){
        Random rd = new Random();
        byte[] arr = new byte[7];
        rd.nextBytes(arr);

        return Base64.getEncoder().withoutPadding().encodeToString(arr);
    }

    public static boolean checkHash(String input, String storedData){

        try {
            String[] parts = storedData.split(":");
            int iterations = Integer.parseInt(parts[0]);
            byte[] salt = Base64.getDecoder().decode(parts[1]);
            byte[] hash = Base64.getDecoder().decode(parts[2]);

            PBEKeySpec spec = new PBEKeySpec(input.toCharArray(), salt, iterations, hash.length * 8);

            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            byte[] testHash = skf.generateSecret(spec).getEncoded();

            int diff = hash.length ^ testHash.length;
            for(int i = 0; i < hash.length && i < testHash.length; i++) {
                diff |= hash[i] ^ testHash[i];
            }

            return diff == 0;

        } catch (NumberFormatException e) {
            showErrorMessage("Error validando contraseña");
            return false;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return false;
    }

}