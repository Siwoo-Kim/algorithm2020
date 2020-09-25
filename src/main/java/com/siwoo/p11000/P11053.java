package com.siwoo.p11000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 * D[n] 을 수열 S 에서 n 자리까지의 원소 중 가장 긴 부분수열의 길이라 했을 때
 * D[n] = max(0..i -> D[i] + 1)
 *      if S[n] > S[i] & D[n] >= 1
 */
@Used(algorithm = Algorithm.DP)
public class P11053 {
    private static final Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static int[] S, D;
    private static int N;

    public static void main(String[] args) {
        N = scanner.nextInt();
        D = new int[N];
        S = new int[N];
        for (int i=0; i<N; i++)
            S[i] = scanner.nextInt();
        for (int i=0; i<N; i++)
            dp(i);
        System.out.println(Arrays.stream(D).max().getAsInt());
    }

    private static int dp(int n) {
        if (n < 0) return 0;
        if (D[n] != 0) return D[n];
        D[n] = 1;
        for (int i=0; i<n; i++)
            if (S[n] > S[i])
                D[n] = Math.max(D[n], dp(i) + 1);
        return D[n];
    }

}
