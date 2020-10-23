package com.siwoo.p1000;

import java.util.Scanner;

public class P1783 {
    private static Scanner scanner = new Scanner(System.in);
    private static int N, M;

    public static void main(String[] args) {
        N = scanner.nextInt();
        M = scanner.nextInt();

        if (N == 1) // 1h
            System.out.println(1);
        else if (N == 2)   // 2h
            System.out.println(Math.min((M + 1) / 2, 4));
        else if (M < 7) {
            System.out.println(Math.min(M, 4));
        } else {
            System.out.println(5 + M-7);
        }
    }
}
