package com.siwoo.p16000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.Scanner;

@Used(algorithm = Algorithm.BRUTE_FORCE)
public class P16945 {
    private static Scanner scanner = new Scanner(System.in);
    private static int[][] data = new int[3][3];
    private static int[] p = new int[9];

    public static void main(String[] args) {
        for (int i=0; i<9; i++) {
            data[i/3][i%3] = scanner.nextInt();
            p[i] = i+1;
        }
        int answer = Integer.MAX_VALUE;
        do {
            int[][] ms = build(p);
            int x = isMagicSquare(ms);
            if (x != -1)
                answer = Integer.min(x, answer);
        } while (nextPermutation(p));
        System.out.println(answer);
    }

    private static int isMagicSquare(int[][] ms) {
        if (ms[0][0] + ms[1][1] + ms[2][2] != 15) return -1;
        if (ms[0][2] + ms[1][1] + ms[2][0] != 15) return -1;
        int cost = 0;
        for (int i=0; i<3; i++) {
            int r = 0, c = 0;
            for (int j = 0; j < 3; j++) {
                r += ms[i][j];
                c += ms[j][i];
                cost += Math.abs(data[i][j] - ms[i][j]); 
            }
            if (r != 15 || c != 15) return -1;
        }
        return cost;
    }

    private static int[][] build(int[] p) {
        int[][] ms = new int[3][3];
        for (int i=0; i<9; i++)
            ms[i/3][i%3] = p[i];
        return ms;
    }

    private static boolean nextPermutation(int[] p) {
        int i = p.length - 1;
        while (i > 0 && p[i-1] >= p[i]) i--;
        if (i == 0) return false;
        int j = p.length - 1;
        while (p[i-1] >= p[j]) j--;
        swap(i-1, j, p);
        j = p.length - 1;
        while (i <= j)
            swap(i++, j--, p);
        return true;
    }

    private static void swap(int i, int j, int[] p) {
        int t = p[i];
        p[i] = p[j];
        p[j] = t;
    }
}
