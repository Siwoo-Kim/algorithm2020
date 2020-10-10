package com.siwoo.p9000;

import java.util.Scanner;

/**
 * N <= 15
 * O(N!) = 1,307,674,368,000 (왜 ok 인지 의문)
 */
public class P9663 {
    private static Scanner scanner = new Scanner(System.in);
    private static int N;
    private static boolean[] H, C, D1, D2;

    public static void main(String[] args) {
        N = scanner.nextInt();
        H = new boolean[N];
        C = new boolean[N];
        D1 = new boolean[N*2];
        D2 = new boolean[N*2];
        int answer = select(0);
        System.out.println(answer);
    }

    private static int select(int row) {
        if (row == N) {
            return 1;
        }
        int sum = 0;
        for (int col=0; col<N; col++) {
            if (check(row, col)) {
                H[row] = C[col] = true;
                D1[row+col] = D2[row-col+(N-1)] = true;
                sum += select(row+1);
                H[row] = C[col] = false;
                D1[row+col] = D2[row-col+(N-1)] = false;
            }
        }
        return sum;
    }

    private static boolean check(int row, int col) {
        if (H[row]) return false;
        if (C[col]) return false;
        if (D1[row+col]) return false;
        if (D2[row-col+(N-1)]) return false;
        return true;
    }
}
