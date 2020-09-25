package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * D[C][N] 을 N 번째 집에 대하여 색깔 C 로 칠햇을 경우 최솟값이라 한다면,
 *      D[C][N] = Math.min(D[c1][N-1], D[c2][N-1]) + C[c]
 */
@Used(algorithm = Algorithm.DP)
public class P1149 {
    private static final Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static int N;
    private static int[][] C;
    private static int[][] D;

    public static void main(String[] args) {
        N = scanner.nextInt();
        scanner.nextLine();
        C = new int[3][N];
        D = new int[3][N];
        for (int i=0; i<N; i++) {
            String[] line = scanner.nextLine().split("\\s+");
            C[0][i] = Integer.parseInt(line[0]);
            C[1][i] = Integer.parseInt(line[1]);
            C[2][i] = Integer.parseInt(line[2]);
        }
        int min = dp(N-1, 0);
        min = Math.min(min, dp(N-1, 1));
        min = Math.min(min, dp(N-1, 2));
        System.out.println(min);
    }

    private static int dp(int n, int color) {
        if (n < 0) return 0;
        if (D[color][n] != 0) return D[color][n];
        int[] colors = getColor(color);
        int dp1 = dp(n-1, colors[0]);
        int dp2 = dp(n-1, colors[1]);
        return D[color][n] = Math.min(dp1, dp2) + C[color][n];
    }

    private static int[] getColor(int color) {
        return Stream.of(0, 1, 2)
                .mapToInt(i -> i)
                .filter(c -> c != color).toArray();
    }
}
