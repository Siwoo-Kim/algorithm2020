package com.siwoo.p1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P1780 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] BOARD;
    private static int[] answer = new int[3];
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        BOARD = new int[N][N];
        for (int i=0; i<N; i++)
            BOARD[i] = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        calculate(0, 0, BOARD, N);
        for (int i=0; i<3; i++)
            System.out.println(answer[i]);
    }

    private static void calculate(int x, int y, int[][] board, int N) {
        if (N == 1) {
            visit(x, y, board);
            return;
        }
        if (check(x, y, N, board))
            visit(x, y, board);
        else {
            int unit = N / 3;
            for (int i=0; i<3; i++)
                for (int j=0; j<3; j++)
                    calculate(x + (unit * i), y + (unit * j), board, unit);
        }
    }

    private static void visit(int x, int y, int[][] board) {
        if (board[x][y] == -1)
            answer[0]++;
        else if (board[x][y] == 0)
            answer[1]++;
        else
            answer[2]++;
    }

    private static boolean check(int x, int y, int N, int[][] board) {
        for (int i=x;i<x+N; i++)
            for (int j=y;j<y+N; j++)
                if (board[i][j] != board[x][y])
                    return false;
        return true;
    }
}