package util;

public class SimpleEncryption {
    private static final char[] KEY = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
    
    public static String encrypt(String value) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : value.toCharArray()) {
            encrypted.append((char) (c + 3));  // �򵥵ؽ�ÿ���ַ��� ASCII ֵ���� 3
        }
        return encrypted.toString();
    }

    public static String decrypt(String encrypted) {
        StringBuilder decrypted = new StringBuilder();
        for (char c : encrypted.toCharArray()) {
            decrypted.append((char) (c - 3));  // ��ÿ���ַ��� ASCII ֵ���� 3
        }
        return decrypted.toString();
    }
}
