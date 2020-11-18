package com.siwoo.p11000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * DP(i,j) 을 현재 위치에서 가져갈 수 있는 누적된 최대 사탕 갯수라고 할때,
 *  DP(i,j) = Math.max(DP[i-1, j], DP[i, j-1], DP[i-1, j-1]) + BOARD[i][j]
 *  
 *  DP 알고리즘 문제임을 판별하는 법 => 불필요한 연산 (사용한 연산을 재사용하자.)
 *  
 *  * 대각선은 이동은 의미가 없다. => (i, j) 의 경로 (i+1, j+1) 는 (i, j+1) => (i+1, j+1), (i+1, j) => (i+1, j+1) 경로보다 항상 작기때문.
 */
@Used(algorithm = Algorithm.DP)
public class P11048 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] BOARD, DP;
    private static int N, M;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        BOARD = new int[N][M];
        DP = new int[N][M];
        for (int i=0; i<N; i++) {
            token = new StringTokenizer(reader.readLine());
            for (int j=0; j<M; j++) {
                BOARD[i][j] = Integer.parseInt(token.nextToken());
                DP[i][j] = -1;
            }
        }
        dp(N-1, M-1);
        System.out.println(DP[N-1][M-1]);
    }

    private static int dp(int i, int j) {
        if (i < 0 || j < 0) return 0;
        if (DP[i][j] != -1) return DP[i][j];
        return DP[i][j] = max(dp(i-1, j), dp(i, j-1), dp(i-1, j-1)) + BOARD[i][j]; 
    }

    private static int max(int i, int j, int k) {
        return Math.max(Math.max(i, j), k);
    }
}
