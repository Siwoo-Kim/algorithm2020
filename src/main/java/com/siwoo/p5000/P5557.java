package com.siwoo.p5000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

@Used(algorithm = Algorithm.DP)
public class P5557 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static long N, MAX = 20;
    private static long[][] D;
    private static long[] A;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine()) - 1;
        A = new long[(int) N];
        D = new long[(int) N][(int) (MAX+1)];
        StringTokenizer token = new StringTokenizer(reader.readLine());
        for (int i=0; i<N; i++)
            A[i] = Integer.parseInt(token.nextToken());
        int answer = Integer.parseInt(token.nextToken());
        D[0][(int) A[0]] = 1;
        go(1);
        System.out.println(D[(int) (N-1)][answer]);
    }

    private static void go(int index) {
        if (index == N) return;
        for (int k=0; k<=20; k++) {
            long v = k - A[index];
            if (v >= 0)
                D[index][k] += D[index-1][(int) v];
            v = k + A[index];
            if (v <= 20)
                D[index][k] += D[index-1][(int) v];
        }
        go(index+1);
    }
}