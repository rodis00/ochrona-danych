package szyfrViegenerea;

import szyfrCezara.Caesar;

import java.util.ArrayList;
import java.util.List;

public class Viegenerea {

    private static final String alphabet = "abcdefghijklmnopqrstuvwxyz";

    private static final List<List<Character>> alphabetLower = generateAlphabet(alphabet);

    private static final List<Character> whiteList = List.of(
            ',', '.', '!', '?', ' ', ':', ';', '-', '_', '*',
            '(', ')', '[', ']', '{', '}', '@', '#', '$', '%', '^',
            '&', '<', '>', '=', '+', '/', '\\', '|', '`', '~'
    );

    private static List<List<Character>> generateAlphabet(String alphabet) {
        List<List<Character>> newAlphabet = new ArrayList<>();
        for (int i = 0; i < alphabet.length(); i++) {
            String letters = Caesar.encrypt(alphabet, i);
            List<Character> tmp = new ArrayList<>();
            for (String letter : letters.split("")) {
                tmp.add(letter.charAt(0));
            }
            newAlphabet.add(tmp);
        }
        return newAlphabet;
    }

    public static String encrypt(
            String text,
            String key
    ) {
        String reducedKey = reduceKey(text, key);
        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char letter = text.charAt(i);
            boolean isUpper = letter >= 'A' && letter <= 'Z';
            char encryptedLetter = ' ';
            if (whiteList.contains(letter)) {
                encryptedText.append(letter);
                continue;
            }
            if (isUpper) {
                encryptedLetter = getEncryptedLetter(i, Character.toLowerCase(letter), reducedKey);
                encryptedText.append(Character.toUpperCase(encryptedLetter));
            } else {
                encryptedLetter = getEncryptedLetter(i, letter, reducedKey);
                encryptedText.append(encryptedLetter);
            }
        }
        return encryptedText.toString();
    }

    public static String decrypt(
            String text,
            String key
    ) {
        key = key.toLowerCase();
        StringBuilder encryptedKey = new StringBuilder();
        for (String letter: key.split("")) {
            int index = (26 - alphabet.indexOf(letter)) % 26;
            encryptedKey.append(alphabet.charAt(index));
        }
        return encrypt(text, encryptedKey.toString());
    }

    private static char getEncryptedLetter(
            int letterIndex,
            char letter,
            String reducedKey
    ) {
        int index = alphabet.indexOf(letter);
        char newLetter = ' ';
        for (List<Character> row : alphabetLower) {
            if (row.getFirst().equals(reducedKey.charAt(letterIndex))) {
                newLetter = row.get(index);
                break;
            }
        }
        return newLetter;
    }

    private static String reduceKey(
            String text,
            String key
    ) {
        key = key.toLowerCase();
        if (key.length() < text.length()) {
            StringBuilder reducedKey = new StringBuilder();
            int index = 0;
            for (Character c : text.toCharArray()) {
                if (c.equals(' ')) {
                    reducedKey.append(' ');
                    continue;
                }
                reducedKey.append(key.charAt(index));
                index++;
                if (index >= key.length()) {
                    index = 0;
                }
            }
            return reducedKey.toString();
        }
        return key;
    }
}
