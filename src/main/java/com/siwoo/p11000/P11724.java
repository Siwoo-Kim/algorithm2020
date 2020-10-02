package com.siwoo.p11000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Used(algorithm = Algorithm.DFS)
public class P11724 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<Integer, List<Edge>> G = new HashMap<>();
    private static int N, M;

    public static void main(String[] args) throws IOException {
        int[] data = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = data[0];
        M = data[1];
        for (int i=0; i<M; i++) {
            data = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            Edge edge = new Edge(data[0], data[1]);
            G.computeIfAbsent(edge.v, (v) -> new LinkedList<>());
            G.computeIfAbsent(edge.w, (v) -> new LinkedList<>());
            G.get(edge.v).add(edge);
            G.get(edge.w).add(edge.reverse());
        }
        Set<Integer> visit = new HashSet<>();
        for (int v: G.keySet()) {
            if (!visit.contains(v)) {
                dfs(v, visit);
            }
        }
        System.out.println(N);
    }

    private static void dfs(int v, Set<Integer> visit) {
        visit.add(v);
        for (Edge edge: G.get(v))
            if (!visit.contains(edge.w)) {
                N--;
                dfs(edge.w, visit);
            }
    }

    private static class Edge {
        private final int v, w;

        private Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

        public Edge reverse() {
            return new Edge(w, v);
        }
    }
}
