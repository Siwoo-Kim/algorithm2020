package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 * D[n] 을 수열 S 의 n 자리 원소에 대한 가장 큰 연속합이라 한다면
 *      D[n] = Math.max(D[n-1] + S[n], S[n])
 * 이때 수열 S 에 대한 가장 큰 연속합은
 *      sum = max((i .. n) -> D[i])
 */
@Used(algorithm = Algorithm.DP)
public class P1912 {
    private static Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static int[] S, D;
    private static int N;

    public static void main(String[] args) {
        N = scanner.nextInt();
        S = new int[N];
        D = new int[N];
        for (int i=0; i<N; i++)
            S[i] = scanner.nextInt();
        for (int i=0; i<N; i++)
            dp(i);
        System.out.println(Arrays.stream(D).max().getAsInt());
    }

    private static int dp(int n) {
        if (n < 0) return 0;
        if (D[n] != 0) return D[n];
        D[n] = Math.max(S[n], dp(n-1) + S[n]);
        return D[n];
    }

}
