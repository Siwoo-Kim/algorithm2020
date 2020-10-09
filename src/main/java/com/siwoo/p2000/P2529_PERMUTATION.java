package com.siwoo.p2000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public class P2529_PERMUTATION {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static String[] signs;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        signs = reader.readLine().split("\\s+");
        N++;

        int[] p = IntStream.rangeClosed(9-N+1, 9).toArray();
        for (int i=0; i<N/2; i++)
            swap(i, N-i-1, p);
        do {
            if (check(p))
                break;
        } while (prevPermutation(p));

        StringBuilder sb = new StringBuilder();
        for (int e: p)
            sb.append(e);
        System.out.println(sb.toString());
        p = IntStream.range(0, N).toArray();
        do {
            if (check(p))
                break;
        } while (nextPermutation(p));
        sb = new StringBuilder();
        for (int e: p)
            sb.append(e);
        System.out.println(sb.toString());
    }

    private static boolean check(int[] p) {
        for (int i=0; i<N-1; i++) {
            int v = p[i];
            int w = p[i+1];
            if ("<".equals(signs[i]) && !(v < w))
                return false;
            if (">".equals(signs[i]) && !(v > w))
                return false;
        }
        return true;
    }

    private static boolean prevPermutation(int[] p) {
        int i = p.length-1;
        while (i > 0 && p[i-1] <= p[i])
            i--;
        if (i == 0) return false;
        int j = p.length-1;
        while (p[i-1] <= p[j])
            j--;
        swap(i-1, j, p);
        j = p.length-1;
        while (j > i)
            swap(i++, j--, p);
        return true;
    }

    private static boolean nextPermutation(int[] p) {
        int i = p.length-1;
        while (i > 0 && p[i-1] >= p[i])
            i--;
        if (i == 0) return false;
        int j = p.length-1;
        while (p[i-1] >= p[j])
            j--;
        swap(i-1, j, p);
        j = p.length-1;
        while (j > i)
            swap(i++, j--, p);
        return true;
    }

    private static void swap(int i, int j, int[] p) {
        int t = p[i];
        p[i] = p[j];
        p[j] = t;
    }
}
