package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.Scanner;

/**
 * D[R][N] 을 N 번째 두 개의 칸에 사자를 배치하는 경우의 수라 한다면,
 *      D[R0][N] = D[R1][N-1] + D[Empty][N-1]
 *      D[R1][N] = D[R0][N-1] + D[Empty][N-1]
 *      D[Empty][N] = D[R1][N-1] + D[R2][N-1] + D[Empty][N-1]
 *
 *      따라서, N 번째의 칸의 경우의 수는 D[R0][N] + D[R1][N] + D[Empty][N]
 */
@Used(algorithm = Algorithm.DP)
public class P1309 {
    private static int N, MAX = 100000, MOD = 9901;
    private static int[][] D = new int[3][MAX+1];

    public static void main(String[] args) {
        D[0][0] = 1;
        D[1][0] = 1;
        D[2][0] = 1;
        for (int n=1; n<MAX; n++) {
            D[0][n] = D[0][n-1] + D[1][n-1] + D[2][n-1];
            D[1][n] = D[0][n-1] + D[1][n-1];
            D[2][n] = D[0][n-1] + D[2][n-1];
            D[0][n] %= MOD;
            D[1][n] %= MOD;
            D[2][n] %= MOD;
        }
        N = new Scanner(System.in).nextInt();
        System.out.println(((D[0][N-1] + D[1][N-1]) % MOD + D[2][N-1]) % MOD);
    }

}
