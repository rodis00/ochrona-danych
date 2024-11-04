package caesarCracker;

import szyfrCezara.Caesar;

public class CrackerMain {
    public static void main(String[] args) {

        String textToCrack = Caesar.encrypt("simple text", 3);
        System.out.println("Text to crack: " + textToCrack);

        String crackedText = Cracker.crack(textToCrack);
        System.out.println("Cracked text: " + crackedText);
    }
}
