package UVa10142;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static BufferedReader br;
    static ArrayList<ArrayList<Integer>> tickets;
    static String[] candidates;
    static int[] counting;
    static boolean[] out;
    static int maxCount;

    public static void main(String[] args) throws Exception {
        AustralianVoting();
    }

    public static void AustralianVoting() throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());
        br.readLine();
        for (int i = 0; i < c; i++) {
            read();
            vote();
            echo();
            if (i < c - 1) {
                System.out.println();
            }
        }
    }

    public static void read() throws Exception {
        int n = Integer.parseInt(br.readLine());
        candidates = new String[n];
        for (int i = 0; i < n; i++) {
            candidates[i] = br.readLine();
        }

        String s;
        StringTokenizer st;
        tickets = new ArrayList<>();
        while (true) {
            s = br.readLine();
            if (s == null || s.isEmpty()) {
                break;
            }
            st = new StringTokenizer(s);
            ArrayList<Integer> list = new ArrayList<>();
            tickets.add(list);
            for (int i = 0; i < n; i++) {
                list.add(Integer.parseInt(st.nextToken()) - 1);
            }
        }
    }

    public static void vote() {
        out = new boolean[candidates.length];
        Arrays.fill(out, false);
        do {
            counting = new int[candidates.length];
            Arrays.fill(counting, 0);
            for (ArrayList<Integer> ticket : tickets) {
                for (int value : ticket) {
                    if (!out[value]) {
                        if (value < counting.length && !out[value]) {
                            counting[value]++;
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
