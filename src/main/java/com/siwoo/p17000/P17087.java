package com.siwoo.p17000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P17087 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, S;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        S = Integer.parseInt(token.nextToken());
        token = new StringTokenizer(reader.readLine());
        int[] distances = new int[N];
        for (int i=0; i<N; i++)
            distances[i] = Math.abs(S - Integer.parseInt(token.nextToken()));
        int gcd = distances[0];
        for (int i=1; i<N; i++)
            gcd = gcd(gcd, distances[i]);
        System.out.println(gcd);
    }

    private static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
