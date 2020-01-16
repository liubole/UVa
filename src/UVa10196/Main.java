package UVa10196;

import java.io.InputStreamReader;
import java.util.Scanner;

class Main {
    static Scanner scanner;
    static char[][] chessboard;

    public static void main(String[] args) {
        checkTheCheck();
    }

    public static void checkTheCheck() {
        chessboard = new char[8][8];
        scanner = new Scanner(new InputStreamReader(System.in));
        int game = 1;
        while (true) {
            int r = 0;
            while (true) {
                String s = scanner.nextLine();
                if (s != null && !s.isEmpty() && r < chessboard.length) {
                    chessboard[r++] = s.toCharArray();
                } else {
                    break;
                }
            }
            if (r > 0) {
                if (isHalt()) {
                    break;
                }
                check(game++);
            }
        }
    }

    public static void check(int game) {
        boolean hasResult = false;
        for (int i = 0; i < chessboard.length && !hasResult; i++) {
            for (int j = 0; j < chessboard[0].length && !hasResult; j++) {
                boolean checkmate;
                char chess = chessboard[i][j];
                char aim = (chess >= 'a' && chess <= 'z') ? 'K' : 'k';
                switch (chess) {
                    case 'b':
                    case 'B':
                        checkmate = Bishop(i, j, aim);
                        break;
                    case 'n':
                    case 'N':
                        checkmate = Knight(i, j, aim);
                        break;
                    case 'k':
                    case 'K':
                        checkmate = King(i, j, aim);
                        break;
                    case 'p':
                    case 'P':
                        checkmate = Pawn(i, j, aim);
                        break;
                    case 'q':
                    case 'Q':
                        checkmate = Queen(i, j, aim);
                        break;
                    case 'r':
                    case 'R':
                        checkmate = Rook(i, j, aim);
                        break;
                    default:
                        continue;
                }
                if (checkmate) {
                    if (aim == 'K') {
                        System.out.println("Game #" + game + ": white king is in check.");
                    } else {
                        System.out.println("Game #" + game + ": black king is in check.");
                    }
                    hasResult = true;
                }
            }
        }
        if (!hasResult) {
            System.out.println("Game #" + game + ": no king is in check.");
        }
    }

    public static boolean Knight(int rdx, int cdx, char aim) {
        if (hit(rdx - 1, cdx - 2, aim) > 0) {
            return true;
        }
        if (hit(rdx - 2, cdx - 1, aim) > 0) {
            return true;
        }
        if (hit(rdx - 2, cdx + 1, aim) > 0) {
            return true;
        }
        if (hit(rdx - 1, cdx + 2, aim) > 0) {
            return true;
        }
        if (hit(rdx + 1, cdx - 2, aim) > 0) {
            return true;
        }
        if (hit(rdx + 2, cdx - 1, aim) > 0) {
            return true;
        }
        if (hit(rdx + 2, cdx + 1, aim) > 0) {
            return true;
        }
        if (hit(rdx + 1, cdx + 2, aim) > 0) {
            return true;
        }
        return false;
    }

    public static boolean King(int rdx, int cdx, char aim) {
        for (int i = Math.max(rdx - 1, 0); i <= Math.min(rdx + 1, chessboard.length - 1); i++) {
            for (int j = Math.max(cdx - 1, 0); j <= Math.min(cdx + 1, chessboard[i].length - 1); j++) {
                if ((i != rdx || j != cdx) && hit(i, j, aim) > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean Queen(int rdx, int cdx, char aim) {
        if (Bishop(rdx, cdx, aim)) {
            return true;
        }
        if (Rook(rdx, cdx, aim)) {
            return true;
        }
        return false;
    }

    public static boolean Bishop(int rdx, int cdx, char aim) {
        int hit;
        for (int i = rdx - 1, j = cdx - 1; i >= 0 && j >= 0; i--, j--) {
            hit = hit(i, j, aim);
            if (hit > 0) {
                return true;
            }
            if (hit == 0) {
                break;
            }
        }

        for (int i = rdx + 1, j = cdx + 1; i < chessboard.length && j < chessboard[i].length; i++, j++) {
            hit = hit(i, j, aim);
            if (hit > 0) {
                return true;
            }
            if (hit == 0) {
                break;
            }
        }

        for (int i = rdx + 1, j = cdx - 1; i < chessboard.length && j >= 0; i++, j--) {
            hit = hit(i, j, aim);
            if (hit > 0) {
                return true;
            }
            if (hit == 0) {
                break;
            }
        }

        for (int i = rdx - 1, j = cdx + 1; i >= 0 && j < chessboard[i].length; i--, j++) {
            hit = hit(i, j, aim);
            if (hit > 0) {
                return true;
            }
            if (hit == 0) {
                break;
            }
        }

        return false;
    }

    public static boolean Rook(int rdx, int cdx, char aim) {
        int hit;
        for (int i = rdx - 1, j = cdx; i >= 0; i--) {
            hit = hit(i, j, aim);
            if (hit > 0) {
                return true;
            }
            if (hit == 0) {
                break;
            }
        }

        for (int i = rdx + 1, j = cdx; i < chessboard.length; i++) {
            hit = hit(i, j, aim);
            if (hit > 0) {
                return true;
            }
            if (hit == 0) {
                break;
            }
        }

        for (int i = rdx, j = cdx - 1; j >= 0; j--) {
            hit = hit(i, j, aim);
            if (hit > 0) {
                return true;
            }
            if (hit == 0) {
                break;
            }
        }

        for (int i = rdx, j = cdx + 1; j < chessboard[rdx].length; j++) {
            hit = hit(i, j, aim);
            if (hit > 0) {
                return true;
            }
            if (hit == 0) {
                break;
            }
        }

        return false;
    }

    public static boolean Pawn(int rdx, int cdx, char aim) {
        char chess = chessboard[rdx][cdx];
        if (chess >= 'a' && chess <= 'z') {
            return hit(rdx + 1, cdx - 1, aim) > 0 || hit(rdx + 1, cdx + 1, aim) > 0;
        }
        return hit(rdx - 1, cdx - 1, aim) > 0 || hit(rdx - 1, cdx + 1, aim) > 0;
    }

    public static boolean isHalt() {
        for (char[] c : chessboard) {
            for (char v : c) {
                if (v != '.') {
                    return false;
                }
            }
        }
        return true;
    }

    public static int hit(int rdx, int cdx, char aim) {
        int hit = -1;
        if (rdx >= 0 && rdx < chessboard.length && cdx >= 0 && cdx < chessboard[rdx].length) {
            char chess = chessboard[rdx][cdx];
            hit = (aim == chess ? 1 : (chess == '.' ? -1 : 0));
        }
        return hit;
    }
}