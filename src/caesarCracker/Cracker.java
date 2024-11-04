package caesarCracker;

import szyfrCezara.Caesar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cracker {
    private static final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private static final String frequencyLetters = "etaoinshrdlcumwfgypbvkjxqz";

    public static List<String> crack(
            String text,
            int amountOfFrequencyLetters
    ) {
        List<String> crackedTexts = new ArrayList<>();
        for (int i = 0; i < amountOfFrequencyLetters; i++) {
            crackedTexts.add(Caesar.decrypt(text, getExpectedKey(text, i)));
        }
        return crackedTexts;
    }

    public static int getExpectedKey(
            String text,
            int indexOfFrequencyLetter
    ) {
        int letterIndex = alphabet.indexOf(getMostFrequentLetter(text));
        return (letterIndex - alphabet.indexOf(frequencyLetters.charAt(indexOfFrequencyLetter)) + 26) % 26;
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
        Set<Character> textSet = new HashSet<>();
        for (char letter : text.toCharArray()) {
            if (letter == ' ')
                continue;
            textSet.add(letter);
        }
        return textSet;
    }
}
