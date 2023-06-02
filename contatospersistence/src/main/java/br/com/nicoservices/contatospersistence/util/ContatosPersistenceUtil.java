package br.com.nicoservices.contatospersistence.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ContatosPersistenceUtil {

    public static String calcularMD5(String str) {
        try {
            MessageDigest md5Digest = MessageDigest.getInstance("MD5");

            md5Digest.update(str.getBytes());

            byte[] digestBytes = md5Digest.digest();

            StringBuilder hexBuilder = new StringBuilder();
            for (byte b : digestBytes) {
                String hex = String.format("%02x", b);
                hexBuilder.append(hex);
            }

            return hexBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
