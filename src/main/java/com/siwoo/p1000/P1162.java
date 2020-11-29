package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 다익스트라 - 정점 나누기
 */
@Used(algorithm = Algorithm.DIJKSTRA)
public class P1162 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, K;
    private static Map<Integer, List<Edge>> G = new HashMap<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());
        for (int i=1; i<=N; i++)
            G.put(i, new ArrayList<>());
        for (int i=0; i<M; i++) {
            token = new StringTokenizer(reader.readLine());
            int v = Integer.parseInt(token.nextToken()),
                    w = Integer.parseInt(token.nextToken()),
                    weight = Integer.parseInt(token.nextToken());
            Edge edge = new Edge(v, w, weight);
            G.get(v).add(edge);
            G.get(w).add(edge.reverse());
        }
        
        Dijkstra dijkstra = new Dijkstra(G, 1);
        long min = dijkstra.MAX;
        for (int k=0; k<=K; k++) {
            if (dijkstra.visit[k][N])
                min = Math.min(min, dijkstra.distTo[k][N]);
        }
        System.out.println(min);
    }
    
    private static class Dijkstra {
        private Map<Integer, List<Edge>> G;
        private long[][] distTo;
        private boolean[][] visit;
        private Queue<Vertex> pq = new PriorityQueue<>();
        private final int s;
        private final long MAX;
        
        public Dijkstra(Map<Integer, List<Edge>> G, int s) {
            this.G = G;
            this.s = s;
            this.MAX = 1000000000L*50000L;
            distTo = new long[K+1][N+1];
            for (int k=0; k<=K; k++)
                for (int i=1; i<=N; i++)
                    distTo[k][i] = this.MAX;
            distTo[K][s] = 0;
            visit = new boolean[K+1][N+1];
            pq.add(new Vertex(s, K, distTo[K][s]));
            while (!pq.isEmpty()) {
                Vertex v = pq.poll();
                if (visit[v.k][v.id]) continue;
                relax(v);
            }
        }

        private void relax(Vertex v) {
            visit[v.k][v.id] = true;
            for (Edge e: G.get(v.id)) {
                if (distTo[v.k][e.to] > distTo[v.k][e.from] + e.weight) {
                    distTo[v.k][e.to] = distTo[v.k][e.from] + e.weight;
                    pq.add(new Vertex(e.to, v.k, distTo[v.k][e.to]));
                }
                if (v.k > 0 && distTo[v.k-1][e.to] > distTo[v.k][e.from]) {
                    distTo[v.k-1][e.to] = distTo[v.k][e.from];
                    pq.add(new Vertex(e.to, v.k-1, distTo[v.k-1][e.to]));
                }
            }
        }
    }
    
    private static class Vertex implements Comparable<Vertex> {
        final int id, k;
        final long weight;

        public Vertex(int id, int k, long weight) {
            this.id = id;
            this.k = k;
            this.weight = weight;
        }

        @Override
        public int compareTo(Vertex o) {
            return Long.compare(weight, o.weight);
        }
    }
    
    private static class Edge {
        final int from, to;
        final int weight;

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
