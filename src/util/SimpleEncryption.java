package util;

public class SimpleEncryption {
    private static final char[] KEY = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
    
    public static String encrypt(String value) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : value.toCharArray()) {
            encrypted.append((char) (c + 3));  // 简单地将每个字符的 ASCII 值增加 3
        }
        return encrypted.toString();
    }

    public static String decrypt(String encrypted) {
        StringBuilder decrypted = new StringBuilder();
        for (char c : encrypted.toCharArray()) {
            decrypted.append((char) (c - 3));  // 将每个字符的 ASCII 值减少 3
        }
        return decrypted.toString();
    }
}
