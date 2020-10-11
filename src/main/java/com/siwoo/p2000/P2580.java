package com.siwoo.p2000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *  크기가 N인 정방 행렬 S 로 나누기. (N % S == 0)
 *      - ((row / (N/S)) * (N/S)) + (col / (N/S))
 *  O(9^(N^2))
 */
@Used(algorithm = Algorithm.BACK_TRACKING)
public class P2580 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int MAX = 9;
    private static int[][] board = new int[MAX][MAX];
    private static boolean cols[][] = new boolean[MAX][MAX+1];
    private static boolean rows[][] = new boolean[MAX][MAX+1];
    private static boolean groups[][] = new boolean[MAX][MAX+1];

    public static void main(String[] args) throws IOException {
        for (int i=0; i<MAX; i++) {
            board[i] = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int j=0; j<MAX; j++) {
                if (board[i][j] != 0) {
                    cols[j][board[i][j]] = true;
                    rows[i][board[i][j]] = true;
                    int groupSize = MAX / 3;
                    int group = ((i / groupSize) * groupSize) + (j / groupSize);
                    groups[group][board[i][j]] = true;
                }
            }
        }
        select(0);
    }

    private static boolean select(int index) {
        if (index == MAX * MAX) { //found
            for (int[] ints : board) {
                for (int j = 0; j < board.length; j++)
                    System.out.print(ints[j] + " ");
                System.out.println();
            }
            return true;
        }
        int row = index / MAX,
                col = index % MAX;
        if (board[row][col] != 0) return select(index+1);
        for (int i=1; i<=MAX; i++) {
            if (check(row, col, i)) {   //backtracking
                cols[col][i] = true;
                rows[row][i] = true;
                int groupSize = MAX / 3;
                int group = ((row / groupSize) * groupSize) + (col / groupSize);
                groups[group][i] = true;
                board[row][col] = i;
                if (select(index+1))
                    return true;
                cols[col][i] = false;
                rows[row][i] = false;
                groups[group][i] = false;
                board[row][col] = 0;
            }
        }
        return false;
    }

    private static boolean check(int row, int col, int value) {
        if (cols[col][value]) return false;
        if (rows[row][value]) return false;
        int groupSize = MAX / 3;
        int group = ((row / groupSize) * groupSize) + (col / groupSize);
        return !groups[group][value];
    }

}
