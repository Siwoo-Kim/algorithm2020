package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * D[i][j] 을 i 까지의 숫자 중 j 의 숫자를 만드는 경우의 수라면
 * D[i][j] = D[i-1][j+A[i]] + D[i-1][j-A[i]] (단, 0 <= j+A[i], j-A[i] <= 20)
 */
@Used(algorithm = Algorithm.DP)
public class P1890 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] BOARD;
    private static Integer[][] DP;
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        BOARD = new int[N][N];
        DP = new Integer[N][N];
        for (int i=0; i<N; i++)
            BOARD[i] = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        dp(N-1, N-1);
        System.out.println(DP[N-1][N-1]);
    }

    private static int dp(int x, int y) {
        if (x == 0 && y == 0) return 1;
        if (DP[x][y] != null)
            return DP[x][y];
        int cnt = 0;
        for (int i=0; i<x; i++)
            if (i + BOARD[i][y] == x)
                cnt += dp(i, y);
        for (int i=0; i<y; i++)
            if (i + BOARD[x][i] == y)
                cnt += dp(x, i);
        return DP[x][y] = cnt;
    }
}
