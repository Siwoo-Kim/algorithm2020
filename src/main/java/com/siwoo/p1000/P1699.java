package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.Scanner;

/**
 * D[N] 을 정수 N 에 대한 제곱수의 항의 최소 개수라 했을 때,
 *      D[N] = min((1..sqrt(N)) -> D[N-i^2] + 1)
 */
@Used(algorithm = Algorithm.DP)
public class P1699 {
    private static int[] D;
    private static int N;

    public static void main(String[] args) {
        N = new Scanner(System.in).nextInt();
        D = new int[N+1];
        System.out.println(dp(N));
    }

    private static int dp(int n) {
        if (n == 0) return 0;
        if (D[n] != 0) return D[n];
        int min = Integer.MAX_VALUE;
        for (int i=1; i<=Math.sqrt(n); i++) {
            if (n-i*i >= 0)
                min = Math.min(dp(n - i * i) + 1, min);
        }
        return D[n] = min;
    }
}
