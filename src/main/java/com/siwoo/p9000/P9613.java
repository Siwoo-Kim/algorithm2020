package com.siwoo.p9000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P9613 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(reader.readLine());
        for (int t=0; t<T; t++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            N = Integer.parseInt(token.nextToken());
            int[] sequence = new int[N];
            for (int i=0; i<N; i++)
                sequence[i] = Integer.parseInt(token.nextToken());
            long answer = 0;
            for (int i=0; i<N; i++)
                for (int j=i+1; j<N; j++)
                    answer += gcd(sequence[i], sequence[j]);
            System.out.println(answer);
        }
    }

    private static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
