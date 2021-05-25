package it.iol.ws.util;

import java.util.Random;

public interface StringUtil {

    static String randomString(int length, String candidateChars) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
        }
        return sb.toString();
    }

    static String randomString(int length) {
        return randomString(length, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
    }

    static String randomInt(int length) {
        return randomString(length, "1234567890");
    }

    static boolean isBlank(String s) {
        if (s == null) return true;
        if (s.length() == 0) return true;
        for (int i = 0; i < s.length(); ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}