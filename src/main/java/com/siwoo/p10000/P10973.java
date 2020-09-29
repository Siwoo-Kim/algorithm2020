package com.siwoo.p10000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.*;
import java.util.Arrays;

@Used(algorithm = Algorithm.BRUTE_FORCE)
public class P10973 {
    private static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int S[];

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        S = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        StringBuilder sb = new StringBuilder();
        if (nextPermutation(S))
            Arrays.stream(S).forEach(i -> sb.append(i).append(" "));
        else
            sb.append(-1);
        writer.write(sb.toString());
        writer.flush();
    }

    private static boolean nextPermutation(int[] S) {
        int i = S.length - 1;
        while (i > 0 && S[i-1] <= S[i])
            i--;
        if (i == 0) return false;
        int j = S.length - 1;
        while (S[i-1] <= S[j])
            j--;
        swap(i-1, j, S);
        j = S.length-1;
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
