package UVa10038.Example1;

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
            boolean[] g = new boolean[c - 1];
            Arrays.fill(g, false);

            Integer last = null;
            for (int i = 0; i < c; i++) {
                int v = Integer.parseInt(arr[i + 1]);
                if (last != null) {
                    int idx = Math.abs(v - last) - 1;
                    if (idx >= 0 && idx < g.length) {
                        g[idx] = true;
                    }
                }
                last = v;
            }

            boolean jolly = true;
            for (boolean i : g) {
                if (!i) {
                    jolly = false;
                    break;
                }
            }
            if (jolly) {
                System.out.println("Jolly");
            } else {
                System.out.println("Not jolly");
            }
            line = br.readLine();
        }
    }
}
