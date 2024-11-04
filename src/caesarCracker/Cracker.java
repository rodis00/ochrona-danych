package caesarCracker;

import szyfrCezara.Caesar;

import java.util.Set;
import java.util.TreeSet;

public class Cracker {
    private static final String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public static String crack(String text) {
        return Caesar.decrypt(text, getExpectedKey(text));
    }

    public static int getExpectedKey(String text) {
        int letterIndex = alphabet.indexOf(getMostFrequentLetter(text));
        return (letterIndex - alphabet.indexOf('e') + 26) % 26;
    }

    private static char getMostFrequentLetter(String text) {
        Set<Character> textSet = createSet(text);
        char character = text.charAt(0);
        int count = 0;

        for (char letter : textSet) {
            int tmp = 0;
            for (char letter2 : text.toCharArray()) {
                if (letter == letter2) {
                    tmp++;
                }
            }
            if (tmp > count) {
                count = tmp;
                character = letter;
            }
        }
        return character;
    }

    private static Set<Character> createSet(String text) {
        Set<Character> textSet = new TreeSet<>();
        for (char letter : text.toCharArray()) {
            if (letter == ' ')
                continue;
            textSet.add(letter);
        }
        return textSet;
    }
}
