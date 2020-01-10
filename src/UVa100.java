import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class UVa100 {
    public static void main(String[] args) throws IOException {
        ArrayList<Integer> ipt;
        do {
            ipt = readInts();
            if (ipt.size() > 0) {
                int max = 0;
                int ceil = ipt.get(1) - ipt.get(0);
                for (int i = 0; i <= ceil; i++) {
                    int c = cal(ipt.get(0) + i);
                    max = c > max ? c : max;
                }
                System.out.printf("%d %d %d\n", ipt.get(0), ipt.get(1), max);
            }
        } while (ipt.size() > 0);
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

    protected static ArrayList<Integer> readInts() throws IOException {
        ArrayList<Integer> array = new ArrayList<>();
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String line = br.readLine();
        String[] tmp = line.split(" ");
        for (String s : tmp) {
            s = s.trim();
            if (s.length() > 0) {
                array.add(Integer.parseInt(s));
            }
        }
        return array;
    }
}
