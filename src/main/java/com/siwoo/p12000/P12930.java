package com.siwoo.p12000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Used(algorithm = Algorithm.DIJKSTRA)
public class P12930 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<Integer, List<Edge>> G = new HashMap<>();
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        Edge[] edges = new Edge[N*N];
        for (int v=0; v<N; v++) {
            String line = reader.readLine();
            for (int w=0; w<N; w++) {
                char weight = line.charAt(w);
                if (weight != '.')
                    edges[v*N+w] = new Edge(v, w, weight-'0', -1);
            }
        }
        for (int v=0; v<N; v++) {
            String line = reader.readLine();
            for (int w=0; w<N; w++) {
                char weight = line.charAt(w);
                if (weight != '.')
                    edges[v*N+w].w2 = weight-'0';
            }
        }
        for (int i=0; i<edges.length; i++) {
            int v = i / N;
            G.putIfAbsent(v, new ArrayList<>());
            if (edges[i] != null)
                G.get(v).add(edges[i]);
        }
        Dijkstra dijkstra = new Dijkstra(G, 0);
        System.out.println(dijkstra.distTo[1] == dijkstra.MAX? -1: dijkstra.distTo[1]);
    }
    
    private static class Dijkstra {
        private Map<Integer, List<Edge>> G;
        private Queue<Vertex> q;
        private int[] distTo;
        private int s, MAX;

        public Dijkstra(Map<Integer, List<Edge>> G, int s) {
            this.G = G;
            this.s = s;
            distTo = new int[N];
            MAX = (N * 9 * N * 9) + 1;
            for (int i=0; i<N; i++)
                distTo[i] = MAX;
            distTo[s] = 0;
            q = new PriorityQueue<>(Vertex.C);
            q.add(new Vertex(s, 0, 0));
            while (!q.isEmpty()) {
                Vertex v = q.poll();
                relax(v);
            }
        }

        private void relax(Vertex v) {
            for (Edge e: G.get(v.v)) {
                Vertex v2 = new Vertex(e.to,v.w1 + e.w1, v.w2 + e.w2);
                if (distTo[e.to] > v2.weight()) {
                    distTo[e.to] = v2.weight();
                    q.add(v2);
                }
            }
        }

        private static class Vertex {
            private static Comparator<Vertex> C = Comparator.comparingInt(Vertex::weight);
            int v, w1, w2;

            public Vertex(int v, int w1, int w2) {
                this.v = v;
                this.w1 = w1;
                this.w2 = w2;
            }
            
            public int weight() {
                return w1 * w2;
            }
        }
    }
    
    private static class Edge {
        int from, to, w1, w2;

        public Edge(int from, int to, int w1, int w2) {
            this.from = from;
            this.to = to;
            this.w1 = w1;
            this.w2 = w2;
        }

        public int weight() {
            return w1 * w2;
        }
    }
}
