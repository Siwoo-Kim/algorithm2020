package com.siwoo.p2000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class P2170 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;

    private static class Pair {
        private static Comparator<Pair> C = Comparator.comparingInt((Pair p) -> p.x)
                .thenComparingInt(p -> p.type);
        private int x, type;

        public Pair(int x, int type) {
            this.x = x;
            this.type = type;
        }
    }
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        List<Pair> lines = new ArrayList<>();
        for (int i=0; i<N; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            int s = Integer.parseInt(token.nextToken()),
                    e = Integer.parseInt(token.nextToken());
            lines.add(new Pair(s, 1));
            lines.add(new Pair(e, -1));
        }
        lines.sort(Pair.C);
        int overlap = 0;
        int k = 0;
        int length = 0;
        for (Pair pair: lines) {
            if (pair.type == 1 && overlap == 0)
                k = pair.x;
            overlap += pair.type;
            if (pair.type == -1 && overlap == 0) 
                length += pair.x - k;
        }
        System.out.println(length);
    }
}
