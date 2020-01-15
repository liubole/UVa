package UVa10033;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Arrays;

class Main {
    static BufferedReader br;
    static int[] registers;
    static String[] rams;
    static DecimalFormat formatter = new DecimalFormat("000");

    public static void main(String[] args) throws Exception {
        Interpreter();
    }

    public static void Interpreter() throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(br.readLine());
        br.readLine();

        for (int testCase = 0; testCase < testCaseCount; testCase++) {
            rams = new String[1000];
            Arrays.fill(rams, "000");
            int ramIndex = 0;
            String s;
            while (true) {
                s = br.readLine();
                if (s != null && !s.isEmpty()) rams[ramIndex++] = s;
                else break;
            }

            registers = new int[10];
            int currPC = 0;
            int count = 0;
            while (currPC != -1 && currPC < rams.length) {
                currPC = operate(currPC);
                count++;
            }

            System.out.println(count);
            if (testCase < testCaseCount - 1) System.out.println();
        }
    }

    public static int operate(int i) {
        int opcode = rams[i].charAt(0) - '0';
        int x = rams[i].charAt(1) - '0';
        int y = rams[i].charAt(2) - '0';
        switch (opcode) {
            case 1:
                return -1;
            case 2:
                registers[x] = y;
                break;
            case 3:
                registers[x] = (registers[x] + y) % 1000;
                break;
            case 4:
                registers[x] = (registers[x] * y) % 1000;
                break;
            case 5:
                registers[x] = registers[y];
                break;
            case 6:
                registers[x] = (registers[x] + registers[y]) % 1000;
                break;
            case 7:
                registers[x] = (registers[x] * registers[y]) % 1000;
                break;
            case 8:
                registers[x] = Integer.parseInt(rams[registers[y]]);
                break;
            case 9:
                rams[registers[y]] = formatter.format(registers[x]);
                break;
            case 0:
                if (registers[y] != 0) {
                    return registers[x];
                }
                break;
            default:
                break;
        }
        return i + 1;
    }

}