package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Used(algorithm = Algorithm.BELLMAN_FORD)
public class P1865 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, W;
    private static Map<Integer, List<Edge>> G;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(reader.readLine());
        for (int t=0; t<T; t++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            N = Integer.parseInt(token.nextToken());
            M = Integer.parseInt(token.nextToken());
            W = Integer.parseInt(token.nextToken());
            G = new HashMap<>();
            for (int i=0; i<M; i++) {
                token = new StringTokenizer(reader.readLine());
                int v = Integer.parseInt(token.nextToken()),
                        w = Integer.parseInt(token.nextToken()),
                        weight = Integer.parseInt(token.nextToken());
                Edge edge = new Edge(v, w, weight);
                G.putIfAbsent(v, new ArrayList<>());
                G.putIfAbsent(w, new ArrayList<>());
                G.get(v).add(edge);
                G.get(w).add(edge.reverse());
            }
            for (int i=2*M; i<2*M+W; i++)  {
                token = new StringTokenizer(reader.readLine());
                int v = Integer.parseInt(token.nextToken()),
                        w = Integer.parseInt(token.nextToken()),
                        weight = -Integer.parseInt(token.nextToken());
                G.putIfAbsent(v, new ArrayList<>());
                G.putIfAbsent(w, new ArrayList<>());
                Edge edge = new Edge(v, w, weight);
                G.get(v).add(edge);
            }
            boolean cycle = hasNegativeCycle(G);
            System.out.println(cycle? "YES": "NO");
        }
    }
    
    private static boolean hasNegativeCycle(Map<Integer, List<Edge>> G) {
        final int MAX = 10000000;
        int[] distTo = new int[N+1];
        for (int v: G.keySet())
            distTo[v] = MAX;
        for (int pass=1; pass<=N; pass++) {
            for (int v: G.keySet()) {
                for (Edge e: G.get(v)) {
                    if (distTo[e.to] > distTo[e.from] + e.weight) {
                        distTo[e.to] = distTo[e.from] + e.weight;
                        if (pass == N) 
                            return true;
                    }
                }
            }
        }
        return false;
    }
    
    private static class Edge {
        final int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public Edge reverse() {
            return new Edge(to, from, weight);
        }
    }
}
