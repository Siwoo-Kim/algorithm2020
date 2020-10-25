package com.siwoo.p1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1939 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static long N, M, FROM, TO;
    private static long ANSWER;
    private static Map<Long, List<Edge>> G = new HashMap<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        long MAX = 0;
        for (int i=0; i<M; i++) {
            token = new StringTokenizer(reader.readLine());
            long x = Long.parseLong(token.nextToken());
            long y = Long.parseLong(token.nextToken());
            long weight = Long.parseLong(token.nextToken());
            MAX = Long.max(weight, MAX);
            G.computeIfAbsent(x, k -> new ArrayList<>());
            G.computeIfAbsent(y, k -> new ArrayList<>());
            G.get(x).add(new Edge(x, y, weight));
            G.get(y).add(new Edge(y, x, weight));
        }
        token = new StringTokenizer(reader.readLine());
        FROM = Integer.parseInt(token.nextToken());
        TO = Integer.parseInt(token.nextToken());
        long answer = binarySearch(1, MAX+1, G);
        System.out.println(answer);
    }

    private static long binarySearch(long left, long right, Map<Long, List<Edge>> G) {
        if (left > right) return ANSWER;
        long mid = (left + right) / 2;
        boolean ok = dfs(mid, G);
        if (ok) {
            ANSWER = Math.max(ANSWER, mid);
            binarySearch(mid+1, right, G);
        } else {
            binarySearch(left, mid-1, G);
        }
        return ANSWER;
    }

    private static boolean dfs(long weight, Map<Long, List<Edge>>  G) {
        return dfs(FROM, weight, new HashSet<>(), G);
    }

    private static boolean dfs(long v, long weight, Set<Long> visit, Map<Long, List<Edge>> G) {
        if (visit.contains(v)) return false;
        if (v == TO) return true;
        visit.add(v);
        for (Edge edge: G.get(v)) {
            if (edge.weight >= weight)
                if (dfs(edge.w, weight, visit, G))
                    return true;
        }
        return false;
    }


    private static class Edge {
        private final long v, w, weight;

        public Edge(long v, long w, long weight) {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return v == edge.v &&
                    w == edge.w &&
                    weight == edge.weight;
        }

        @Override
        public int hashCode() {
            return Objects.hash(v, w, weight);
        }
    }
}
