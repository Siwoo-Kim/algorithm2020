package com.siwoo.p10000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * O(nextPermutation(N)) * N
 * O(N!*2N) + Nlog(N)
 * N <= 8
 *  = 645120
 */
@Used(algorithm = Algorithm.BRUTE_FORCE)
public class P10819 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[] S;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        S = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        Arrays.sort(S);
        int answer = 0;
        do {
            int sum = 0;
            for (int i=1; i<N; i++)
                sum += Math.abs(S[i-1] - S[i]);
            answer = Math.max(answer, sum);
        } while (nextPermutation(S));
        System.out.println(answer);
    }

    private static boolean nextPermutation(int[] S) {
        int i = S.length-1;
        while (i > 0 && S[i-1] >= S[i])
            i--;
        if (i == 0) return false;
        int j = S.length-1;
        while (S[i-1]>=S[j])
            j--;
        swap(i-1, j, S);
        j = S.length-1;
        while (i<j)
            swap(i++, j--, S);
        return true;
    }

    private static void swap(int i, int j, int[] S) {
        int t = S[i];
        S[i] = S[j];
        S[j] = t;
    }
}
