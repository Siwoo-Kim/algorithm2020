package com.siwoo.p11000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.Scanner;

/**
 * D[n] 을 2 x n 직사각형을 1 x 2, 2 x 1, 2 x 2 타일로 채우는 방법의 수라 했을 때,
 * D[n] = D[n-1] + (D[n-2] * 2)
 */
@Used(algorithm = Algorithm.DP)
public class P11727 {
    private static final int MAX = 1000, MOD = 10007;
    private static int[] D = new int[MAX+1];

    public static void main(String[] args) {
        D[1] = 1;
        D[2] = 3;
        dp(MAX);
        System.out.println(D[new Scanner(System.in).nextInt()]);
    }

    private static int dp(int n) {
        if (n <= 0) return 0;
        if (D[n] != 0) return D[n];
        int d1 = dp(n-1) % MOD;
        int d2 = (dp(n-2) + dp(n-2)) % MOD;
        return D[n] = (d1 + d2) % MOD;
    }

}
