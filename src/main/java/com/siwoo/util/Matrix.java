package com.siwoo.util;

import java.util.Arrays;

/**
 * / 방향 대각선 그룹화 = row + col
 * \ 방향 대각선 그룹화 = row - col + (N-1)
 */
public class Matrix {
    private static int N;
    private static int[][] H;
    private static int[][] V;
    private static int[][] DIAG1;
    private static int[][] DIAG2;

    public Matrix(int N) {
        this.N = N;
        H = new int[N][N];
        V = new int[N][N];
        DIAG1 = new int[N][N];
        DIAG2 = new int[N][N];
        init();
    }

    private void init() {
        for (int row=0; row<N; row++) {
            for (int col=0; col<N; col++) {
                DIAG1[row][col] = row+col;      // / 방향
                DIAG2[row][col] = row-col+(N-1);    // \ 방향
                H[row][col] = row;
                V[row][col] = col;
            }
        }
    }

    public void print() {
        for (int[][] m: Arrays.asList(H, V, DIAG1, DIAG2)) {
            Print.print(m);
            System.out.println("=================");
        }
    }

    public static void main(String[] args) {
        Matrix matrix = new Matrix(5);
        matrix.print();
    }
}
