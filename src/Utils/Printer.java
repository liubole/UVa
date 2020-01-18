package Utils;

import java.util.*;

public class Printer {
    public static <E> void printArray(E[] var) {
        String s = Arrays.deepToString(var);
        System.out.println(s);
    }

    public static <E> void printArray(E[] var, String name) {
        System.out.print(name + ": ");
        printArray(var);
    }

    public static void printArray(int[] var) {
        Integer[] objVar = Arrays.stream(var).boxed().toArray(Integer[]::new);
        printArray(objVar);
    }

    public static void printArray(int[] var, String name) {
        System.out.print(name + ": ");
        printArray(var);
    }

    public static void printArray(long[] var) {
        Long[] objVar = Arrays.stream(var).boxed().toArray(Long[]::new);
        printArray(objVar);
    }

    public static void printArray(long[] var, String name) {
        System.out.print(name + ": ");
        printArray(var);
    }

    public static void printArray(double[] var) {
        Double[] objVar = Arrays.stream(var).boxed().toArray(Double[]::new);
        printArray(objVar);
    }

    public static void printArray(double[] var, String name) {
        System.out.print(name + ": ");
        printArray(var);
    }

    public static void printArray(boolean[] var) {
        Boolean[] objVar = new Boolean[var.length];
        for (int i = 0; i < var.length; i++) {
            objVar[i] = var[i];
        }
        printArray(objVar);
    }

    public static void printArray(boolean[] var, String name) {
        System.out.print(name + ": ");
        printArray(var);
    }

    public static <E> void printArray(ArrayList<E> var) {
        String s = Arrays.toString(var.toArray());
        System.out.println(s);
    }

    public static <E> void printArray(ArrayList<E> var, String name) {
        System.out.print(name + ": ");
        printArray(var);
    }
}