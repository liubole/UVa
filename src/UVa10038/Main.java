package UVa10038;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        JollyJumper();
    }

    public static void JollyJumper() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        while (line != null && !line.isEmpty()) {
            String[] arr = line.split("[ ]+");
            int c = Integer.parseInt(arr[0]);
            int[] input = new int[c];
            for (int i = 0; i < c; i++) {
                input[i] = Integer.parseInt(arr[i + 1]);
            }
            if (check(input)) {
                System.out.println("Jolly");
            } else {
                System.out.println("Not jolly");
            }
            line = br.readLine();
        }
    }

    public static boolean check(int[] arr) {
        boolean[] g = new boolean[arr.length - 1];
        Arrays.fill(g, false);
        for (int i = 0; i < arr.length - 1; i++) {
            int idx = Math.abs(arr[i] - arr[i + 1]) - 1;
            if (idx >= 0 && idx < g.length) {
                g[idx] =true;
            }
        }
        for (boolean i : g) {
            if (!i) {
                return false;
            }
        }
        return true;
    }

}
