package com.siwoo.p3000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * NxN 크기의 테이블에 인접한 두 칸을 골라 교환 후 가장 긴 연속 부분을 선택할때의 시간 복잡도.
 * 2(N^2) * N^2 -> O(N^4)
 * 1 <= N <= 50 -> 6,250,000
 */
@Used(algorithm = Algorithm.BRUTE_FORCE)
public class P3085 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, ANSWER;
    private static char[][] board;
    private static int[][] D  = { {0, 1}, {1, 0} };

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        board = new char[N][N];
        for (int i=0; i<N; i++)
            board[i] = reader.readLine().toCharArray();
        select(0);
        System.out.println(ANSWER);
    }

    private static void select(int index) {
        if (index == N*N) return;
        int row = index / N;
        int col = index % N;
        for (int[] d: D) {
            int dy = d[0] + row;
            int dx = d[1] + col;
            if (dy == N || dx == N) continue;
            swap(row, col, dy, dx);
            ANSWER = Math.max(ANSWER, check());
            swap(row, col, dy, dx);
        }
        select(index+1);
    }

    private static int check() {
        int max = 0;
        for (int row=0; row<N; row++) {
            int cnt = 1;
            for (int col=1; col<N; col++) {
                if (board[row][col-1] == board[row][col])
                    cnt++;
                else
                    cnt = 1;
                max = Math.max(cnt, max);
            }
        }
        for (int col=0; col<N; col++) {
            int cnt = 1;
            for (int row=1; row<N; row++) {
                if (board[row-1][col] == board[row][col])
                    cnt++;
                else
                    cnt = 1;
                max = Math.max(cnt, max);
            }
        }
        return max;
    }

    private static void swap(int r1, int c1, int r2, int c2) {
        char tmp = board[r1][c1];
        board[r1][c1] = board[r2][c2];
        board[r2][c2] = tmp;
    }
}
