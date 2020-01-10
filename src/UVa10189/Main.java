package UVa10189;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = 1;
        while (scanner.hasNextInt()) {
            int r = scanner.nextInt(), c = scanner.nextInt();
            if (r == 0 && c == 0) {
                break;
            }
            String[] stock = new String[r];
            for (int i = 0; i < r; ) {
                String line = scanner.nextLine();
                if (line.length() > 0) {
                    stock[i] = line;
                    i++;
                }
            }
            if (x > 1) {
                System.out.println();
            }
            System.out.println("Field #" + (x++) + ":");
            cal(stock, r, c);
        }
    }

    public static void cal(String[] stock, int row, int column) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (stock[i].charAt(j) == '*') {
                    System.out.print('*');
                    continue;
                }
                int count = 0;
                for (int r = (i - 1 > 0 ? i - 1 : 0); r < (i + 2 > row ? row : i + 2); r++) {
                    for (int c = (j - 1 > 0 ? j - 1 : 0); c < (j + 2 > column ? column : j + 2); c++) {
                        if (stock[r].charAt(c) == '*') {
                            count++;
                        }
                    }
                }
                System.out.print(count);
            }
            System.out.println();
        }
    }
}