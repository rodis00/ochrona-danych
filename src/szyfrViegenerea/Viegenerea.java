package szyfrViegenerea;

import szyfrCezara.Caesar;

import java.util.ArrayList;
import java.util.List;

public class Viegenerea {

    private static final String alphabet = "abcdefghijklmnopqrstuvwxyz";

    private static final List<List<Character>> alphabetLower = generateAlphabet(alphabet);
    private static final List<List<Character>> alphabetUpper = generateAlphabet(alphabet.toUpperCase());

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

    public static String encrypt(String text, String key) {
        String reducedKey = reduceKey(text, key);
        StringBuilder encryptedText = new StringBuilder();
        List<List<Character>> textChars = text.charAt(0) >= 'a' && text.charAt(0) <= 'z' ? alphabetLower : alphabetUpper;
        for (int i = 0; i < text.length(); i++) {
            if (whiteList.contains(text.charAt(i))) {
                encryptedText.append(text.charAt(i));
                continue;
            }
            int index_i = textChars.getFirst().indexOf(text.charAt(i));
            for (List<Character> row : textChars) {
                if (row.getFirst().equals(reducedKey.charAt(i))) {
                    Character newLetter = row.get(index_i);
                    encryptedText.append(newLetter);
                    break;
                }
            }
        }
        return encryptedText.toString();
    }

    private static String reduceKey(String text, String key) {
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
