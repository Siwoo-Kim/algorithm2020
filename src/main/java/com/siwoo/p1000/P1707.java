package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Used(algorithm = Algorithm.DFS)
public class P1707 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<Integer, List<Edge>> G;
    private static int N, M, T;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(reader.readLine());
        for (int t=0; t<T; t++) {
            G = new HashMap<>();
            String[] data = reader.readLine().split("\\s+");
            N = Integer.parseInt(data[0]);
            M = Integer.parseInt(data[1]);
            for (int i=0; i<M; i++) {
                data = reader.readLine().split("\\s+");
                int v = Integer.parseInt(data[0]);
                int w = Integer.parseInt(data[1]);
                Edge edge = new Edge(v, w);
                G.computeIfAbsent(edge.v, k -> new LinkedList<>());
                G.computeIfAbsent(edge.w, k -> new LinkedList<>());
                G.get(v).add(edge);
                G.get(w).add(edge.reverse());
            }
            boolean bipartite = true;
            Set<Integer> red = new HashSet<>(),
                    black = new HashSet<>();
            for (int v: G.keySet()) {
                if (red.contains(v)) continue;
                if (black.contains(v)) continue;
                bipartite = bipartite & dfs(v, 0, red, black);
            }
            if (!bipartite)
                System.out.println("NO");
            else
                System.out.println("YES");
        }
    }

    private static boolean dfs(int v, int flip, Set<Integer> red, Set<Integer> black) {
        if (flip == 0)
            red.add(v);
        else
            black.add(v);
        for (Edge edge: G.get(v)) {
            if (flip == 0 && red.contains(edge.w))
                return false;
            if (flip == 1 && black.contains(edge.w))
                return false;
            if (red.contains(edge.w)) continue;
            if (black.contains(edge.w)) continue;
            if (!dfs(edge.w, 1-flip, red, black))
                return false;;
        }
        return true;
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
