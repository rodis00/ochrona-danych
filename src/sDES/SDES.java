package sDES;

import java.util.Scanner;

public class SDES {

    int[] p10Array = {3, 5, 2, 7, 4, 10, 1, 9, 8, 6};
    int[] p8Array = {6, 3, 7, 4, 8, 5, 10, 9};
    int[] leftShiftArray1 = {2, 3, 4, 5, 1};
    int[] leftShiftArray2 = {3, 4, 5, 1, 2};
    int[] ip = {2, 6, 3, 1, 4, 8, 5, 7};
    int[] ep = {4, 1, 2, 3, 2, 3, 4, 1};
    int[][] sbox1 = {
            {1, 0, 3, 2},
            {3, 2, 1, 0},
            {0, 2, 1, 3},
            {3, 1, 3, 2}
    };
    int[][] sbox2 = {
            {0, 1, 2, 3},
            {2, 0, 1, 3},
            {3, 0, 1, 0},
            {2, 1, 0, 3}
    };
    int[] p4 = {2, 4, 3, 1};
    int[] ipInverse = {4, 1, 3, 5, 7, 2, 8, 6};

    String keyPermutation(String key, int[] array) {
        key = key.replace(" ", "");
        StringBuilder result = new StringBuilder();
        for (int i : array) {
            result.append(key.charAt(i - 1));
        }
        return result.toString();
    }

    String leftShift(String key, int[] shiftArray) {
        StringBuilder result = new StringBuilder();
        for (int i : shiftArray) {
            result.append(key.charAt(i - 1));
        }
        return result.toString();
    }

    String p10(String key) {
        return keyPermutation(key, p10Array);
    }

    String lsCombination(String key, int[] shiftArray) {
        StringBuilder result = new StringBuilder();
        String leftHalve = leftShift(key.substring(0, 5), shiftArray);
        String rightHalve = leftShift(key.substring(5, 10), shiftArray);
        result.append(leftHalve).append(rightHalve);
        return result.toString();
    }

    String generateKey1(String key) {
        key = p10(key);
        String ls1 = lsCombination(key, leftShiftArray1);
        return keyPermutation(ls1, p8Array);
    }

    String generateKey2(String key) {
        key = p10(key);
        String ls2 = lsCombination(key, leftShiftArray1);
        ls2 = lsCombination(ls2, leftShiftArray2);
        return keyPermutation(ls2, p8Array);
    }

    String ipPermutation(String text) {
        text = text.replace(" ", "");
        StringBuilder result = new StringBuilder();
        for (int i : ip) {
            result.append(text.charAt(i - 1));
        }
        return result.toString();
    }

    String epPermutation(String text) {
        String rightHalve = text.substring(4, 8);
        StringBuilder result = new StringBuilder();
        for (int i : ep) {
            result.append(rightHalve.charAt(i - 1));
        }
        return result.toString();
    }

    String xorOperation(String key1, String ep) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < key1.length(); i++) {
            result.append(key1.charAt(i) == ep.charAt(i) ? "0" : "1");
        }
        return result.toString();
    }

    String sBox(String key1, String ep) {
        String xor = xorOperation(key1, ep);
        StringBuilder result = new StringBuilder();

        // for left halve
        int row = toDecimal("" + xor.charAt(0) + xor.charAt(3));
        int col = toDecimal("" + xor.charAt(1) + xor.charAt(2));
        int sboxLeft = sbox1[row][col];
        result.append(toBinary(sboxLeft));

        // for right halve
        row = toDecimal("" + xor.charAt(4) + xor.charAt(7));
        col = toDecimal("" + xor.charAt(5) + xor.charAt(6));
        int sboxRight = sbox2[row][col];
        result.append(toBinary(sboxRight));

        return result.toString();
    }

    String p4Permutation(String text) {
        StringBuilder result = new StringBuilder();
        for (int i : p4) {
            result.append(text.charAt(i - 1));
        }
        return result.toString();
    }

    String swap(String ip, String p4) {
        String left = ip.substring(0, 4);
        String right = ip.substring(4, 8);
        String xor = xorOperation(left, p4);
        return right + xor;
    }

    String combineIp(String swap, String p4) {
        String xor = xorOperation(swap.substring(0, 4), p4);
        return xor + swap.substring(4, 8);
    }

    String IpInverse(String combinedIp) {
        StringBuilder result = new StringBuilder();
        for (int i : ipInverse) {
            result.append(combinedIp.charAt(i - 1));
        }
        return result.toString();
    }

    void encrypt(String ipInverse) {
        String left = ipInverse.substring(0, 4);
        String right = ipInverse.substring(4, 8);

        System.out.println(left + " : " + getLetter(left));
        System.out.println(right + " : " + getLetter(right));
    }

    String getLetter(String binary) {
        return switch (binary) {
            case "0000" -> "A";
            case "0001" -> "B";
            case "0010" -> "C";
            case "0011" -> "D";
            case "0100" -> "E";
            case "0101" -> "F";
            case "0110" -> "G";
            case "0111" -> "H";
            case "1000" -> "I";
            case "1001" -> "J";
            case "1010" -> "K";
            case "1011" -> "L";
            case "1100" -> "M";
            case "1101" -> "N";
            case "1110" -> "O";
            case "1111" -> "P";
            default -> "Not found";
        };
    }

    int toDecimal(String sequence) {
        return switch (sequence) {
            case "00" -> 0;
            case "01" -> 1;
            case "10" -> 2;
            case "11" -> 3;
            default -> -1;
        };
    }

    String toBinary(int decimal) {
        return switch (decimal) {
            case 0 -> "00";
            case 1 -> "01";
            case 2 -> "10";
            case 3 -> "11";
            default -> "";
        };
    }

    public static void main(String[] args) {
        SDES sDES = new SDES();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter key: ");
        String key = sc.nextLine();
        System.out.println("Enter plain text: ");
        String plainText = sc.nextLine();

        System.out.println();
        String key1 = sDES.generateKey1(key);
        String key2 = sDES.generateKey2(key);

        System.out.println("key-1: " + key1);
        System.out.println("key-2: " + key2);

        // for key 1
        System.out.println("\nFor key 1");
        String ip = sDES.ipPermutation(plainText);
        String ep = sDES.epPermutation(ip);
        String xor = sDES.xorOperation(key1, ep);
        System.out.println("IP: " + ip);
        System.out.println("EP: " + ep);
        System.out.println("XOR: " + xor);
        String sbox1 = sDES.sBox(key1, ep);
        System.out.println("SBOX: " + sbox1);
        String p4 = sDES.p4Permutation(sbox1);
        System.out.println("P4: " + p4);
        String swap = sDES.swap(ip, p4);
        System.out.println("SWAP: " + swap);


        // for key 2
        System.out.println("\nFor key 2");
        String ip2 = swap;
        System.out.println("IP2: " + ip2);
        String ep2 = sDES.epPermutation(ip2);
        System.out.println("EP2: " + ep2);
        String xor2 = sDES.xorOperation(key2, ep2);
        System.out.println("XOR2: " + xor2);
        String sbox2 = sDES.sBox(key2, ep2);
        System.out.println("SBOX2: " + sbox2);
        String p4_2 = sDES.p4Permutation(sbox2);
        System.out.println("P4_2: " + p4_2);
        String combinedIp = sDES.combineIp(ip2, p4_2);

        // encrypt
        System.out.println("\nCombined IP: " + combinedIp);
        String ipInverse = sDES.IpInverse(combinedIp);
        System.out.println("IP-1: " + ipInverse);
        sDES.encrypt(ipInverse);
    }
}
