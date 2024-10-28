package szyfrViegenerea;

public class ViegenereaTest {
    public static void main(String[] args) {

        String text = "TO JEST BARDZO TAJNY TEKST";
        String key = "TAJNE";

        String encryptedText = Viegenerea.encrypt(text, key);

        System.out.println("\nOriginal text: " + text);
        System.out.println("Key: " + key);
        System.out.println("Encrypted text: " + encryptedText);

    }
}
