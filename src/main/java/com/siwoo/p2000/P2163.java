package com.siwoo.p2000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.Scanner;

@Used(algorithm = Algorithm.DP)
public class P2163 {
    private static Scanner scanner = new Scanner(System.in);
    private static int N, M;
    private static Integer[][] D;
    
    public static void main(String[] args) {
        N = scanner.nextInt();
        M = scanner.nextInt();
        D = new Integer[N+1][M+1];
        int x = go(N, M);
        System.out.println(x);
    }

    private static int go(int n, int m) {
        if (n == 1 && m == 1) return D[n][m] = 0;
        if (D[n][m] != null)
            return D[n][m];
        int min = Integer.MAX_VALUE;
        for (int i=1; i<=n-1; i++) {    //v cut
            int x = go(i, m) + go(n-i, m) + 1;
            min = Math.min(x, min);
        }
        for (int i=1; i<=m-1; i++) {    //h cut
            int x = go(n, i) + go(n, m-i) + 1;
            min = Math.min(x, min);
        }
        return D[n][m] = min;
    }
}
