package com.siwoo.p1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1978 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        StringTokenizer token = new StringTokenizer(reader.readLine());
        int cnt = 0;
        for (int i=0; i<N; i++) {
            if (isPrime(Integer.parseInt(token.nextToken())))
                cnt++;
        }
        System.out.println(cnt);
    }

    private static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i=2; i*i<=n; i++)
            if (n % i == 0)
                return false;
        return true;
    }
}
