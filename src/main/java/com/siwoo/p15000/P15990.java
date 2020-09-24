package com.siwoo.p15000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * D[n] 이 1, 2, 3 의 합으로 정수 n 을 만들 수 있는 경우의 수라면 (연속하지 않는)
 * D[n] = (D[2][n-1] + D[3][n-1]) + (D[1][n-2] + D[3][n-2]) + (D[1][n-3] + D[2][n-3])  
 */
@Used(algorithm = Algorithm.DP)
public class P15990 {
    private static Scanner scanner = new Scanner(new InputStreamReader(System.in));
    private static int MAX = 100000;
    private static long MOD = 1000000009;
    private static long[][] D = new long[4][MAX+1];

    public static void main(String[] args) {
        for (int i = 1; i <= MAX; i++) {
            if (i-1 >= 0) {
                D[1][i] = D[2][i - 1] + D[3][i - 1];
                if (i == 1) D[1][i] = 1;
            }
            if (i-2 >= 0) {
                D[2][i] = D[1][i - 2] + D[3][i - 2];
                if (i == 2) D[2][i] = 1;
            }
            if (i-3 >= 0) {
                D[3][i] = D[1][i - 3] + D[2][i - 3];
                if (i == 3) D[3][i] = 1;
            }
            for (int k=1; k<=3; k++)
                D[k][i] %= MOD;
        }
        int N = scanner.nextInt();
        for (int i=0; i<N; i++) {
            int n = scanner.nextInt();
            System.out.println((D[1][n] + D[2][n] + D[3][n]) % MOD);
        }
    }
}
