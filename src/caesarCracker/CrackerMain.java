package caesarCracker;

import szyfrCezara.Caesar;

import java.util.List;

public class CrackerMain {
    public static void main(String[] args) {

        String textToCrack = Caesar.encrypt("TOt@l! h@rD teXt to encrYpT & decrypt$", 3);
        System.out.println("\nText to crack: " + textToCrack);

        int amountOfLetters = 8;
        List<String> crackedTexts = Cracker.crack(textToCrack, amountOfLetters);

        System.out.println("\nCracked texts:");
        crackedTexts.forEach(System.out::println);
    }
}
