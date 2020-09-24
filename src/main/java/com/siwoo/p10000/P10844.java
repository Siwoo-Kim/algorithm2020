package com.siwoo.p10000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.Scanner;

/**
 * D[n] 을 자리수가 N 인 총 계단 수의 갯수라 했을때
 * D[n] = sum(0 -> 9 = D[i-1][n-1] + D[i+1][n+1]),
 *  단 i-1 >= 0 && i+1 <= 9 && D[i][1] = 1
 */
@Used(algorithm = Algorithm.DP)
public class P10844 {
    private static long MOD = 1000000000L;
    private static int N = 100;
    private static long[][] D = new long[10][N+1];

    public static void main(String[] args) {
        for (int i=1; i<=9; i++)
            D[i][1] = 1;
        dp(2);
        int n = new Scanner(System.in).nextInt();
        int sum = 0;
        for (int i=0; i<=9; i++) {
            sum += D[i][n];
            sum %= MOD;
        }
        System.out.println(sum);
    }

    private static void dp(int n) {
        if (n > N) return;
        for (int i=0; i<10; i++) {
            if (i-1 >= 0)
                D[i][n] += D[i-1][n-1];
            if (i+1 <= 9)
                D[i][n] += D[i+1][n-1];
            D[i][n] %= MOD;
        }
        dp(n+1);
    }
}
