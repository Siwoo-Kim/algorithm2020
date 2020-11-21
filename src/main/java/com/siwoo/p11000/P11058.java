package com.siwoo.p11000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P11058 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static long[] D;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        D = new long[N+1];
        kriii(1);
        System.out.println(D[N]);
    }
    
    public static void kriii(int n) {
        if (n == N+1) return;
        long x = D[n-1] + 1;
        for (int j=1; j<n-3; j++)
            x = Math.max(x, D[n-(j+2)] * (j+1));
        D[n] = x;
        kriii(n+1);
    }
}
