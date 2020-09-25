package com.siwoo.p2000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.Scanner;

/**
 * D[k][n] 을 정수 k 개를 더해 n 을 만드는 경우의 수라 한다면,
 *      D[k][n] = sum((0..N) -> D[k-1][n-i])
 *
 */
@Used(algorithm = Algorithm.DP)
public class P2225 {
    private static Scanner scanner = new Scanner(System.in);
    private static int N, K;
    private static long MOD = 1000000000L;
    private static long D[][];

    public static void main(String[] args) {
        N = scanner.nextInt();
        K = scanner.nextInt();
        D = new long[K+1][N+1];
        D[0][0] = 1;
        for (int k=1; k<=K; k++)
            for (int n=0; n<=N; n++)
                for (int i=0; i<=n; i++) {
                    D[k][n] += D[k-1][n-i];
                    D[k][n] %= MOD;
                }
        System.out.println(D[K][N]);
    }
}
