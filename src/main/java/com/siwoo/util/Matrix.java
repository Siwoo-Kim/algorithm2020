package com.siwoo.util;

import java.util.Arrays;
import java.util.List;

/**
 * / 방향 대각선 그룹화 = row + col
 * \ 방향 대각선 그룹화 = row - col + (N-1)
 * 매트릭스 그룹화 = (row/groupSize) * groupSize + (col/groupSize)
 */
public class Matrix {
    private int N;
    private int[][] index;
    private int[][] H;
    private int[][] V;
    private int[][] DIAG1;
    private int[][] DIAG2;
    private int[][] GROUP;
    private List<int[][]> DB;

    public Matrix(int N) {
        this.N = N;
        index = new int[N][N];
        H = new int[N][N];
        V = new int[N][N];
        DIAG1 = new int[N][N];
        DIAG2 = new int[N][N];
        GROUP = new int[N][N];
        DB = Arrays.asList(index, H, V, DIAG1, DIAG2, GROUP);
        init();
    }

    private void init() {
        for (int row=0; row<N; row++) {
            for (int col=0; col<N; col++) {
                DIAG1[row][col] = row+col;      // / 방향
                DIAG2[row][col] = row-col+(N-1);    // \ 방향
                H[row][col] = row;
                V[row][col] = col;
                index[row][col] = row * N + col;
                int group = N/3;
                GROUP[row][col] = (row/group) * group + (col/group);
            }
        }
    }

    public void print() {
        for (int[][] m: DB) {
            Print.print(m);
            System.out.println("=================");
        }
    }

    public static void main(String[] args) {
        Matrix matrix = new Matrix(9);
        matrix.print();
    }
}
