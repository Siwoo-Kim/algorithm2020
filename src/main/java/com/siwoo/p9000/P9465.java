package com.siwoo.p9000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 * D[r][n] 을 r 행인 n 번째 열 스티커을 땠을 경우의 최대 값이라 한다면,
 *       D[0][n] = max(D[1][n-1], D[2][n-1])    // 아무것도 떼지 않은 경우
 *       D[1][n] = max(D[0][n-1], D[2][n-1]) + S[n]
 *       D[2][n] = max(D[0][n-1], D[1][n-1]) + S[n]
 */
@Used(algorithm = Algorithm.DP)
public class P9465 {
    private static final Scanner scanner = new Scanner(new InputStreamReader(System.in));
    private static int[][] D, S;
    private static int T, N, ANSWER;

    public static void main(String[] args) {
        T = scanner.nextInt();
        while (T != 0) {
            N = scanner.nextInt();
            scanner.nextLine();
            D = new int[3][N];
            S = new int[3][N];
            for (int i=1; i<3; i++)
                S[i] = Arrays.stream(scanner.nextLine().split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            dp(0);
            System.out.println(ANSWER);
            T--;
        }
    }

    private static void dp(int n) {
        if (n == N) return;
        if (n == 0) {
            D[1][n] = S[1][n];
            D[2][n] = S[2][n];
        } else {
            D[0][n] = Math.max(D[1][n - 1], D[2][n - 1]);
            D[1][n] = Math.max(D[0][n - 1], D[2][n - 1]) + S[1][n];
            D[2][n] = Math.max(D[0][n - 1], D[1][n - 1]) + S[2][n];
            ANSWER = Math.max(Math.max(D[0][n], D[1][n]), D[2][n]);
        }
        dp(n + 1);
    }
}
