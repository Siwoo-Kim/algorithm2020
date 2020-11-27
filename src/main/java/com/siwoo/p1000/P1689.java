package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

@Used(algorithm = Algorithm.GREEDY)
public class P1689 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;

    private static class Pair {
        int x, type;

        public Pair(int x, int type) {
            this.x = x;
            this.type = type;
        }
    }
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        List<Pair> a = new ArrayList<>(N*2);
        for (int i=0; i<N; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            int s = Integer.parseInt(token.nextToken()),
                    e = Integer.parseInt(token.nextToken());
            a.add(new Pair(s, 1));
            a.add(new Pair(e, -1));
        }
        a.sort(Comparator.comparingInt((Pair p) -> p.x)
                .thenComparingInt(p -> p.type));    // 겹칠때, 끝나는 쪽 먼저 처
        int overlap = 0;
        int max = 0;
        for (Pair p: a) {
            overlap += p.type;
            max = Math.max(max, overlap);
        }
        System.out.println(max);
    }
}
