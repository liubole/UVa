package UVa706;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder output = new StringBuilder();
        while (scanner.hasNextInt()) {
            int s = scanner.nextInt();
            String n = scanner.next("[0-9]*").trim();
            if (s == 0 && n.equals("0")) {
                break;
            }
            for (String row : rowsEcho(s, n)) {
                output.append(row).append("\n");
            }
            output.append("\n");
        }
        System.out.print(output);
    }

    public static String[] rowsEcho(int s, String n) {
        String[] rows = new String[2 * s + 3];
        for (int len = n.length(); len >= 1; len--) {
            int no = Integer.parseInt(n.substring(0, 1));
            for (int i = 0; i < 2 * s + 3; i++) {
                rows[i] = (rows[i] == null ? "" : rows[i] + " ") + rowSymbol(no, i, s);
            }
            n = n.substring(1);
        }
        return rows;
    }

    public static String rowSymbol(int no, int curRow, int s) {
        String bStr = " ";
        String vStr = "|";
        String hStr = "-";
        if (curRow == 0) {
            switch (no) {
                case 1:
                case 4:
                    return repeat(bStr, s + 2);
                default:
                    return bStr + repeat(hStr, s) + bStr;
            }
        } else if (curRow <= s) {
            switch (no) {
                case 1:
                case 2:
                case 3:
                case 7:
                    return repeat(bStr, s + 1) + vStr;
                case 5:
                case 6:
                    return vStr + repeat(bStr, s + 1);
                default:
                    return vStr + repeat(bStr, s) + vStr;
            }
        } else if (curRow == s + 1) {
            switch (no) {
                case 0:
                case 1:
                case 7:
                    return repeat(bStr, s + 2);
                default:
                    return bStr + repeat(hStr, s) + bStr;
            }
        } else if (curRow <= 2 * s + 1) {
            switch (no) {
                case 1:
                case 3:
                case 4:
                case 5:
                case 7:
                case 9:
                    return repeat(bStr, s + 1) + vStr;
                case 2:
                    return vStr + repeat(bStr, s + 1);
                default:
                    return vStr + repeat(bStr, s) + vStr;
            }
        } else if (curRow == 2 * s + 2) {
            switch (no) {
                case 1:
                case 4:
                case 7:
                    return repeat(bStr, s + 2);
                default:
                    return bStr + repeat(hStr, s) + bStr;
            }
        } else {
            return "";
        }
    }

    public static String repeat(String r, int t) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < t; i++) {
            s.append(r);
        }
        return s.toString();
    }
}
