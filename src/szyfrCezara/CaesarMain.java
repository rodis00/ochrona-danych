package szyfrCezara;

public class CaesarMain {
    public static void main(String[] args) {

        String text = "TOt@l! h@rD teXt to encrYpT & decrypt$";
        int key = 3;

        String encryptedText = Caesar.encrypt(text, key);
        String decryptedText = Caesar.decrypt(encryptedText, key);

        System.out.println("Key: " + key);
        System.out.println("Original text: " + text);
        System.out.println("Encrypted text: " + encryptedText);
        System.out.println("Decrypted text: " + decryptedText);
    }
}
