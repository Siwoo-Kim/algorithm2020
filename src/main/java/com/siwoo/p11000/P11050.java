package com.siwoo.p11000;

import java.util.Scanner;

public class P11050 {
    private static Scanner scanner = new Scanner(System.in);
    private static int N, K;
    private static Long[] D = new Long[11];
    
    public static void main(String[] args) {
        N = scanner.nextInt();
        K = scanner.nextInt();
        System.out.println(nCr(N, K));
    }

    private static long nCr(int n, int k) {
        return fac(n) / (fac(k) * fac(n - k));
    }

    private static long fac(int i) {
        if (D[i] != null) return D[i];
        if (i == 0) return D[i] = 1L;
        return D[i] = i * fac(i-1);
    }
}
