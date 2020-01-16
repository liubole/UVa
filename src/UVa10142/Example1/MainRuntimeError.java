package UVa10142.Example1;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//Runtime error
class MainRuntimeError {
    static Scanner scanner;
    static ArrayList<ArrayList<Integer>> tickets;
    static String[] candidates;
    static int[] counting;
    static boolean[] out;
    static int maxCount;

    public static void main(String[] args) {
        AustralianVoting();
    }

    public static void AustralianVoting() {
        scanner = new Scanner(new InputStreamReader(System.in));
        int c = scanner.nextInt();
        while (c-- > 0) {
            read();
            vote();
            echo();
        }
    }

    public static void read() {
        String s;
        int cc = scanner.nextInt(), ci = 0;
        candidates = new String[cc];
        while (ci < cc) {
            s = scanner.nextLine();
            if (s != null && !s.isEmpty()) {
                candidates[ci++] = s;
            }
        }
        tickets = new ArrayList<>();
        do {
            s = scanner.nextLine();
            if (s != null && !s.isEmpty()) {
                String[] vs = s.split("[ ]+");
                ArrayList<Integer> vi = new ArrayList<>();
                for (int i = 0; i < vs.length; i++) {
                    vi.add(Integer.parseInt(vs[i]));
                }
                tickets.add(vi);
            } else {
                break;
            }
        } while (true);
    }

    public static void vote() {
        out = new boolean[candidates.length];
        Arrays.fill(out, false);
        do {
            counting = new int[candidates.length];
            Arrays.fill(counting, 0);
            for (ArrayList<Integer> ticket : tickets) {
                for (int value : ticket) {
                    if (!out[value - 1]) {
                        if (value - 1 < counting.length && !out[value - 1]) {
                            counting[value - 1]++;
                        }
                        break;
                    }
                }
            }
        } while (!elected());
    }

    public static void echo() {
        for (int i = 0; i < counting.length; i++) {
            if (counting[i] == maxCount) {
                System.out.println(candidates[i]);
            }
        }
        System.out.println();
    }

    public static boolean elected() {
        int maxVal = -1, minVal = -1, total = 0;
        for (int i = 0; i < counting.length; i++) {
            int c = counting[i];
            if (!out[i]) {
                maxVal = maxVal == -1 ? c : Math.max(maxVal, c);
                minVal = minVal == -1 ? c : Math.min(minVal, c);
                total += c;
            }
        }
        if (maxVal == minVal || maxVal > total / 2) {
            maxCount = maxVal;
            return true;
        }
        for (int i = 0; i < counting.length; i++) {
            if (counting[i] <= minVal) {
                out[i] = true;
            }
        }
        return false;
    }
}
