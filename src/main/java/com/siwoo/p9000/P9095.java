package com.siwoo.p9000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.Scanner;

/**
 * D[N] 을 1, 2, 3 의 합으로 나타내는 방법의 수라 했을 때,
 * D[N] = D[N-1] + D[N-2] + D[N-3]
 */
@Used(algorithm = Algorithm.DP)
public class P9095 {
    private static Scanner scanner = new Scanner(System.in);
    private static int MAX = 11;
    private static int[] D = new int[MAX+1];

    public static void main(String[] args) {
        D[0] = 1;
        dp(MAX);
        int t = scanner.nextInt();
        for (int i=0; i<t; i++)
            System.out.println(D[scanner.nextInt()]);
    }

    private static int dp(int n) {
        if (n < 0) return 0;
        if (D[n] != 0) return D[n];
        return D[n] = dp(n-3) + dp(n-2) + dp(n-1);
    }
}