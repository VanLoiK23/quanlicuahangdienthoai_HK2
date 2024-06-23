package Security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Test_scurity {
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static void main(String[] args) {
        String str = "doan321";
        String hashedPassword = Password(str);
        System.out.println(hashedPassword);
    }

    public static String Password(String pass) {
        final MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA3-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        final byte[] hashed = digest.digest(pass.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(hashed);
    }
}
