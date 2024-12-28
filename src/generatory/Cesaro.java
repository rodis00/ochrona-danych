package generatory;

import java.util.Random;

public class Cesaro {
    public static void main(String[] args) {
        int numIterations = 100_000;
        double estimatedPi = estimatePi(numIterations);
        System.out.println("Estimated value of PI: " + estimatedPi);
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static double estimatePi(int numIterations) {
        Random random = new Random();
        int count = 0;

        for (int i = 0; i < numIterations; i++) {
            int x = Math.abs(random.nextInt());
            int y = Math.abs(random.nextInt());

            if (gcd(x, y) == 1) {
                count++;
            }
        }

        double probability = (double) count / numIterations;
        return Math.sqrt(6 / probability);
    }
}
