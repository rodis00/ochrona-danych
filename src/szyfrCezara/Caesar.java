package szyfrCezara;

import java.util.List;

public class Caesar {

    private static final List<Character> alphabet = List.of(
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
            'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
            's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    );

    private static final List<Character> whiteList = List.of(
            ',', '.', '!', '?', ' ', ':', ';', '-', '_', '*',
            '(', ')', '[', ']', '{', '}', '@', '#', '$', '%', '^',
            '&', '<', '>', '=', '+', '/', '\\', '|', '`', '~'
    );

    public static String encrypt(String text, int key) {
        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (whiteList.contains(c)) {
                encryptedText.append(c);
                continue;
            }
            encryptedText.append(shiftChar(c, key));
        }
        return encryptedText.toString();
    }

    public static String decrypt(String text, int key) {
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (whiteList.contains(c)) {
                decryptedText.append(c);
                continue;
            }
            decryptedText.append(shiftChar(c, -key));
        }
        return decryptedText.toString();
    }

    private static char shiftChar(char c, int shift) {
        boolean isUpper = c >= 'A' && c <= 'Z';
        c = Character.toLowerCase(c);
        int index = alphabet.indexOf(c);
        int newIndex = index + shift;
        if (newIndex >= alphabet.size()) {
            newIndex = newIndex - alphabet.size();
        }
        if (newIndex < 0) {
            newIndex = newIndex + alphabet.size();
        }
        char newChar = alphabet.get(newIndex);
        if (isUpper) {
            newChar = Character.toUpperCase(newChar);
        }
        return newChar;
    }
}
