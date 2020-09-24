package com.siwoo.p2000;

import java.util.Scanner;

/**
 * D[n] 이 n 자리로 된 이친수의 갯수라면
 *  D[n] = (D[0][n-1] + D[1][n-1]) + D[1][n-1]
 */
public class P2193 {
    private static int MAX = 90;
    private static long[][] D = new long[2][MAX+1];

    public static void main(String[] args) {
        D[1][1] = 1;
        dp(2);
        int N = new Scanner(System.in).nextInt();
        System.out.println(D[0][N] + D[1][N]);
    }

    private static void dp(int n) {
        if (n > MAX) return;
        D[0][n] += D[0][n-1] + D[1][n-1];
        D[1][n] += D[0][n-1];
        dp(n + 1);
    }
}
