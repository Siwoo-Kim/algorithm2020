package com.siwoo.p11000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * D[n] 을 n 개를 카드를 구매하기 위한 최대값이라 했을 때,
 * D[n] = MAX(i -> n => S[i] + D[N-i])    (i 은 구매한 카드 갯수)
 */
@Used(algorithm = Algorithm.DP)
public class P11052 {
    private static final Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static int N;
    private static int D[], S[];

    public static void main(String[] args) {
        N = scanner.nextInt();
        D = new int[N+1];
        S = new int[N+1];
        for (int i=1; i<=N; i++)
            S[i] = scanner.nextInt();
        dp(N);
        System.out.println(D[N]);
    }

    private static int dp(int n) {
        if (n == 0) return 0;
        if (D[n] != 0) return D[n];
        int max = Integer.MIN_VALUE;
        for (int i=1; i<=n; i++)
            max = Math.max(S[i] + dp(n-i), max);
        return D[n] = max;
    }
}
