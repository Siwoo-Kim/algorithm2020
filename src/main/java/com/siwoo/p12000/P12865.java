package com.siwoo.p12000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P12865 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int K, N;
    private static int[][] D;
    private static Pair[] PAIRS;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());
        PAIRS = new Pair[N+1];
        D = new int[N+1][K+1];
        for (int i=1; i<=N; i++) {
            token = new StringTokenizer(reader.readLine());
            PAIRS[i] = new Pair(Integer.parseInt(token.nextToken()), 
                    Integer.parseInt(token.nextToken()));
        }
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=K; j++) {
                D[i][j] = D[i-1][j];
                int bag = j - PAIRS[i].w; 
                if (bag >= 0)
                    D[i][j] = Math.max(D[i][j], D[i-1][bag] + PAIRS[i].v);
            }
        }
        System.out.println(Arrays.stream(D[N-1]).max().getAsInt());
    }


    private static class Pair {
        private final int w, v;

        public Pair(int w, int v) {
            this.w = w;
            this.v = v;
        }
    }
}
