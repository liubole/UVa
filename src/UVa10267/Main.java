package UVa10267;

import java.util.Scanner;

public class Main {
    static char[][] pic;
    static boolean[][] cached;
    static StringBuilder output;
    static Scanner scanner;

    public static void main(String[] args) {
        GraphicalEditor();
    }

    public static void GraphicalEditor() {
        scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.length() < 1) {
                continue;
            }
            String[] p = readParam(line.substring(1));
            switch (line.charAt(0)) {
                case 'I':
                    cmdI(Integer.parseInt(p[0]), Integer.parseInt(p[1]));
                    break;
                case 'C':
                    cmdC();
                    break;
                case 'L':
                    cmdL(Integer.parseInt(p[0]) - 1, Integer.parseInt(p[1]) - 1, p[2].charAt(0));
                    break;
                case 'V':
                    cmdV(Integer.parseInt(p[0]) - 1, Integer.parseInt(p[1]) - 1, Integer.parseInt(p[2]) - 1, p[3].charAt(0));
                    break;
                case 'H':
                    cmdH(Integer.parseInt(p[0]) - 1, Integer.parseInt(p[1]) - 1, Integer.parseInt(p[2]) - 1, p[3].charAt(0));
                    break;
                case 'K':
                    cmdK(Integer.parseInt(p[0]) - 1, Integer.parseInt(p[1]) - 1, Integer.parseInt(p[2]) - 1, Integer.parseInt(p[3]) - 1, p[4].charAt(0));
                    break;
                case 'F':
                    preCmdF();
                    cmdF(Integer.parseInt(p[0]) - 1, Integer.parseInt(p[1]) - 1, p[2].charAt(0));
                    break;
                case 'S':
                    cmdS(p[0]);
                    break;
                case 'X':
                default:
                    break;
            }
            if (line.charAt(0) == 'X') {
                break;
            }
        }
    }

    public static String[] readParam(String line) {
        return line.trim().split("[ ]+");
    }

    public static void cmdI(int m, int n) {
        pic = new char[m][n];
        cmdC();
    }

    public static void cmdC() {
        for (int i = 0; i < pic.length; i++) {
            for (int j = 0; j < pic[i].length; j++) {
                pic[i][j] = 'O';
            }
        }
    }

    public static void cmdL(int x, int y, char c) {
        if (x < pic.length) {
            if (y < pic[x].length && y >= 0) {
                pic[x][y] = c;
            }
        }
    }

    public static void cmdV(int x, int y1, int y2, char c) {
        if (x < pic.length && x >= 0) {
            for (int i = Math.min(y1, y2); i >= 0 && i <= Math.max(y1, y2) && i < pic[x].length; i++) {
                pic[x][i] = c;
            }
        }
    }

    public static void cmdH(int x1, int x2, int y, char c) {
        for (int i = Math.min(x1, x2); i >= 0 && i <= Math.max(x1, x2) && i < pic.length; i++) {
            if (y < pic[i].length && y >= 0) {
                pic[i][y] = c;
            }
        }
    }

    public static void cmdK(int x1, int y1, int x2, int y2, char c) {
        for (int i = Math.min(x1, x2); i >= 0 && i <= Math.max(x1, x2) && i < pic.length; i++) {
            for (int j = Math.min(y1, y2); j >= 0 && j <= Math.max(y1, y2) && j < pic[i].length; j++) {
                pic[i][j] = c;
            }
        }
    }

    public static void preCmdF() {
        cached = new boolean[pic.length][pic[0].length];
        for (int i = 0; i < pic.length; i++) {
            for (int j = 0; j < pic[i].length; j++) {
                cached[i][j] = false;
            }
        }
    }

    public static void cmdF(int x, int y, char c) {
        if (x < pic.length && x >= 0 && y < pic[x].length && y >= 0) {
            if (cached[x][y]) {
                return;
            }
            char old = pic[x][y];
            pic[x][y] = c;
            cached[x][y] = true;
            if (x - 1 >= 0 && pic[x - 1][y] == old) {
                cmdF(x - 1, y, c);
            }
            if (x + 1 < pic.length && pic[x + 1][y] == old) {
                cmdF(x + 1, y, c);
            }
            if (y - 1 >= 0 && pic[x][y - 1] == old) {
                cmdF(x, y - 1, c);
            }
            if (y + 1 < pic[x].length && pic[x][y + 1] == old) {
                cmdF(x, y + 1, c);
            }
        }
    }

    public static void cmdS(String name) {
        int rl = pic[0].length;
        output = new StringBuilder();
        for (int i = 0; i < rl; i++) {
            for (char[] chars : pic) {
                output.append(chars[i]);
            }
            output.append("\n");
        }
        System.out.println(name);
        System.out.print(output);
    }
}