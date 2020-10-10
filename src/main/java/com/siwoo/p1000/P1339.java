package com.siwoo.p1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class P1339 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static String[] S;


    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        S = new String[N];
        Set<Character> sets = new HashSet<>();
        for (int i=0; i<N; i++) {
            S[i] = reader.readLine();
            for (char c: S[i].toCharArray())
                sets.add(c);
        }

        int[] indexes = new int['Z'+1];
        int i = 0;
        for (char c: sets)
            indexes[c] = i++;

        int[] p = IntStream.rangeClosed(0, sets.size()).toArray();

        int max = 0;
        do {
            int sum = 0;
            for (String s: S) {
                int x = 0;
                for (i=0; i<s.length(); i++)
                    x = x * 10 + (9 - p[indexes[s.charAt(i)]]);
                sum += x;
            }
            max = Math.max(sum, max);
        } while (nextPermutation(p));
        System.out.println(max);
    }

    public static boolean nextPermutation(int[] S) {
        int i = S.length - 1;
        while (i > 0 && S[i-1] >= S[i])
            i--;
        if (i == 0) return false;
        int j = S.length - 1;
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
