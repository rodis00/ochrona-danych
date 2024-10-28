package szyfrViegenerea;

public class ViegenereaTest {
    public static void main(String[] args) {

        String text = "To jest Bardzo Tajny tekst.";
        String key = "Tajne";

        String encryptedText = Viegenerea.encrypt(text, key);
        String decryptedText = Viegenerea.decrypt(encryptedText, key);

        System.out.println("\nOriginal text: " + text);
        System.out.println("Key: " + key);
        System.out.println("Encrypted text: " + encryptedText);
        System.out.println("Decrypted text: " + decryptedText);
    }
}
