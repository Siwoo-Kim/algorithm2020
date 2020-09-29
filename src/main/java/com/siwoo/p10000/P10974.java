package com.siwoo.p10000;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class P10974 {
    private static Scanner scanner = new Scanner(System.in);
    private static int S[];
    private static int N;

    public static void main(String[] args) {
        N = scanner.nextInt();
        S = IntStream.rangeClosed(1, N).toArray();
        StringBuilder sb = new StringBuilder();
        do {
            Arrays.stream(S).forEach(i -> sb.append(i).append(" "));
            sb.append("\n");
        } while (nextPermutation(S));
        System.out.println(sb.toString());
    }

    private static boolean nextPermutation(int[] S) {
        int i = S.length-1;
        while (i>0 && S[i-1] >= S[i])
            i--;
        if (i == 0) return false;
        int j = S.length-1;
        while (S[i-1] >= S[j])
            j--;
        swap(i-1, j, S);
        j = S.length - 1;
        while (i < j)
            swap(i++, j--, S);
        return true;
    }

    private static void swap(int i, int j, int[] S) {
        int t = S[i];
        S[i] = S[j];
        S[j] = t;
    }
}
