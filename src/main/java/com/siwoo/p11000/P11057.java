package com.siwoo.p11000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.Scanner;

/**
 * D[N] 을 자리수가 N 인 오르막 수의 갯수라 했을 때,
 *      D[i][N] = (0..9) -> D[i][N] = sum((0..i) -> D[j][N-1])
 */
@Used(algorithm = Algorithm.DP)
public class P11057 {
    private static int MAX = 1000, MOD = 10007;
    private static int[][] D = new int[10][MAX+1];

    public static void main(String[] args) {
        for (int i=0; i<10; i++)
            D[i][1] = 1;
        dp(2);
        int N = new Scanner(System.in).nextInt();
        int sum = 0;
        for (int i=0; i<10; i++) {
            sum += D[i][N];
            sum %= MOD;
        }
        System.out.println(sum);
    }

    private static void dp(int n) {
        if (n > MAX) return;
        for (int i=0; i<10; i++) {
            for (int j=0; j<=i; j++)
                D[i][n] += D[j][n-1];
            D[i][n] %= MOD;
        }
        dp(n+1);
    }
}
