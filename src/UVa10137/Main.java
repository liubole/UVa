package UVa10137;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder output = new StringBuilder();
        while (scanner.hasNextInt()) {
            int r = scanner.nextInt();
            if (r == 0) {
                break;
            }
            double[] stock = new double[r];
            double total = 0;
            for (int i = 0; i < r; i++) {
                stock[i] = scanner.nextDouble();
                total += stock[i];
            }
            double fAvg = Math.floor(total / r * 100) / 100;
            double cAvg = Math.ceil(total / r * 100) / 100;

            double fMin = 0, cMin = 0;
            for (double x : stock) {
                if (x < fAvg) {
                    fMin += (fAvg - x);
                }
                if (x > cAvg) {
                    cMin += (x - cAvg);
                }
            }
            double min = Math.max(fMin, cMin);
            output.append(String.format("$%.2f", min)).append("\n");
        }
        System.out.print(output);
    }

}