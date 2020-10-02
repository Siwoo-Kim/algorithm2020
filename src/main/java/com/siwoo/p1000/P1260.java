package com.siwoo.p1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1260 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, START;
    private static Map<Integer, List<Edge>> G = new HashMap<>();
    private static StringBuilder ANSWER = new StringBuilder();

    private static class Edge implements Comparable<Edge> {
        private final int v, w;

        private Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

        public Edge reverse() {
            return new Edge(w, v);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return v == edge.v &&
                    w == edge.w;
        }

        @Override
        public int hashCode() {
            return Objects.hash(v, w);
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(w, o.w);
        }
    }

    public static void main(String[] args) throws IOException {
        int[] data = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = data[0];
        M = data[1];
        START = data[2];
        G.put(START, new ArrayList<>());
        for (int i=0; i<M; i++) {
            data = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            Edge edge = new Edge(data[0], data[1]);
            G.computeIfAbsent(edge.v, k -> new ArrayList<>());
            G.computeIfAbsent(edge.w, k -> new ArrayList<>());
            G.get(edge.v).add(edge);
            edge = edge.reverse();
            G.get(edge.v).add(edge);
        }
        for (int v: G.keySet())
            G.get(v).sort(Comparator.naturalOrder());

        dfs(START, new HashSet<>());
        ANSWER.append("\n");
        bfs(START);
        System.out.println(ANSWER.toString());
    }

    private static void bfs(int v) {
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visit = new HashSet<>();
        q.add(v);
        visit.add(v);
        ANSWER.append(v).append(" ");
        while (!q.isEmpty()) {
            int w = q.poll();
            for (Edge edge: G.get(w)) {
                if (!visit.contains(edge.w)) {
                    ANSWER.append(edge.w).append(" ");
                    visit.add(edge.w);
                    q.add(edge.w);
                }
            }
        }
    }

    private static void dfs(int v, Set<Integer> visit) {
        visit.add(v);
        ANSWER.append(v).append(" ");
        for (Edge edge: G.get(v)) {
            if (!visit.contains(edge.w))
                dfs(edge.w, visit);
        }
    }

}
