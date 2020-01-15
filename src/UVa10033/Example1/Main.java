package UVa10033.Example1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Arrays;

class Main {

    public static DecimalFormat formatter=new DecimalFormat("000");

    public static int doOperation (String [] ram, int index, int [] register) {
        String s=ram[index];
        int opcode=s.charAt(0)-'0';
        int r1=s.charAt(1)-'0';
        int r2=s.charAt(2)-'0';
        if (opcode==0 && register[r2]!=0) return register[r1];
        else if (opcode==1) return -1;
        else if (opcode==2) register[r1]=r2;
        else if (opcode==3) register[r1]=(register[r1]+r2)%1000;
        else if (opcode==4) register[r1]=(register[r1]*r2)%1000;
        else if (opcode==5) register[r1]=register[r2];
        else if (opcode==6) register[r1]=(register[r1]+register[r2])%1000;
        else if (opcode==7) register[r1]=(register[r1]*register[r2])%1000;
        else if (opcode==8) register[r1]=Integer.parseInt(ram[register[r2]]);
        else if (opcode==9) ram[register[r2]]=formatter.format(register[r1]);
        return index+1;
    }

    public static void main (String[]args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount=Integer.parseInt(br.readLine());
        br.readLine();

        for (int testCase=0;testCase<testCaseCount;testCase++) {
            String [] ram=new String [1000];
            Arrays.fill(ram,"000");
            int ramIndex=0;
            String s;
            while (true) {
                s=br.readLine();
                if (s!=null && !s.isEmpty()) ram[ramIndex++]=s;
                else break;
            }

            int [] register=new int [10];
            int currPC=0;
            int count=0;
            while (currPC!=-1 && currPC<ram.length) {
                currPC=doOperation(ram,currPC,register);
                count++;
            }

            System.out.println(count);
            if (testCase<testCaseCount-1) System.out.println();
        }
    }
}