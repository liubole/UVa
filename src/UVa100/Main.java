package UVa100;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextInt()) {
            int a = scan.nextInt(), b = scan.nextInt(), max = 0, tmp;
            for (int i = (a > b ? b : a); i <= a || i <= b; i++) {
                if ((tmp = cal(i)) > max) {
                    max = tmp;
                }
            }
            System.out.printf("%d %d %d\n", a, b, max);
        }
    }

    protected static int cal(Integer x) {
        int times = 0;
        while (x >= 1) {
            times++;
            if (x == 1) {
                break;
            }
            if ((x & 1) == 0) {//even
                x = x >> 1;
            } else {
                x = x * 3 + 1;
            }
        }
        return times;
    }
}
