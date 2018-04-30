package com.lavreniuk.gymcounter.utils;

import org.springframework.security.crypto.codec.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author taras
 * @date 22.04.18.
 */
public class PasswordHashing {

    private static final String SALT = "kssakmdsfbhsbdh4323423dhs";

    public static String hashPassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] result = messageDigest.digest((SALT + password + SALT).getBytes());
            return new String(Hex.encode(result));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
