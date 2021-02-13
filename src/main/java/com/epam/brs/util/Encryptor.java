package com.epam.brs.util;

import org.mindrot.jbcrypt.BCrypt;

public class Encryptor {

    public static String encrypt(String data) {
        String salt = BCrypt.gensalt();
        String hashedData = BCrypt.hashpw(data, salt);
        return hashedData;
    }

    public static boolean coincide(String data, String hashedData) {
        return BCrypt.checkpw(data, hashedData);
    }
}
