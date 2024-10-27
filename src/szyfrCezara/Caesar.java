package szyfrCezara;

import java.util.List;

public class Caesar {

    private static final List<Character> alphabetLower = List.of(
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
            'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
            's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    );

    private static final List<Character> alphabetUpper = List.of(
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
            'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
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
            List<Character> alphabet = c >= 'a' && c <= 'z' ? alphabetLower : alphabetUpper;
            int index = alphabet.indexOf(c);
            int newIndex = index + key;
            if (newIndex >= alphabet.size()) {
                newIndex = newIndex - alphabet.size();
            }
            char newChar = alphabet.get(newIndex);
            encryptedText.append(newChar);
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
            List<Character> alphabet = c >= 'a' && c <= 'z' ? alphabetLower : alphabetUpper;
            int index = alphabet.indexOf(c);
            int newIndex = index - key;
            if (newIndex < 0) {
                newIndex = alphabet.size() + newIndex;
            }
            char newChar = alphabet.get(newIndex);
            decryptedText.append(newChar);
        }
        return decryptedText.toString();
    }
}
