package com.siwoo.p13000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * O(M^2)
 */
@Used(algorithm = Algorithm.GRAPH)
public class P13023 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static Map<Integer, List<Edge>> G = new HashMap<>();
    private static List<Edge> edges = new ArrayList<>();
    private static boolean[][] G2 = new boolean[2000][2000];

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

    public static void main(String[] args) throws IOException {
        String[] data = reader.readLine().split("\\s+");
        N = Integer.parseInt(data[0]);
        M = Integer.parseInt(data[1]);
        for (int i=0; i<N; i++)
            G.put(i, new LinkedList<>());
        for (int i=0; i<M; i++) {
            data = reader.readLine().split("\\s+");
            int v = Integer.parseInt(data[0]);
            int w = Integer.parseInt(data[1]);
            G2[v][w] = G2[w][v] = true;
            Edge edge = new Edge(v, w);
            G.get(v).add(edge);
            G.get(w).add(edge.reverse());
            edges.add(edge);
            edges.add(edge.reverse());
        }

        for (Edge e1: edges) //brute force
            for (Edge e2: edges) {
                int a = e1.v;
                int b = e1.w;
                int c = e2.v;
                int d = e2.w;
                Set<Integer> set = new HashSet<>(Arrays.asList(a, b, c, d));
                if (set.size() != 4) continue;
                if (!G2[b][c]) continue;
                for (Edge edge: G.get(d)) {
                    int e = edge.w;
                    if (set.contains(e)) continue;
                    else {
                        System.out.println(1);
                        return;
                    }
                }
            }
        System.out.println(0);
    }
}
