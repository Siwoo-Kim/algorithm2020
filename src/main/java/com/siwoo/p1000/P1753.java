package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Used(algorithm = Algorithm.DIJKSTRA)
public class P1753 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<Integer, List<Edge>> G = new HashMap<>();
    private static int V, E, S;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        V = Integer.parseInt(token.nextToken());
        for (int i=1; i<=V; i++)
            G.put(i, new ArrayList<>());
        E = Integer.parseInt(token.nextToken());
        token = new StringTokenizer(reader.readLine());
        S = Integer.parseInt(token.nextToken());
        for (int i=0; i<E; i++) {
            token = new StringTokenizer(reader.readLine());
            int v = Integer.parseInt(token.nextToken()),
                    w = Integer.parseInt(token.nextToken()),
                    weight = Integer.parseInt(token.nextToken());
            Edge edge = new Edge(v, w, weight);
            G.get(edge.from).add(edge);
        }
        Dijkstra dijkstra = new Dijkstra(G, S);
        StringBuilder sb = new StringBuilder();
        for (int i=1; i<=V; i++) {
            int d = dijkstra.distTo(i);
            sb.append(d == -1 ? "INF": d).append("\n");
        }
        System.out.println(sb);
    }
    
    private static class Dijkstra {
        private Map<Integer, List<Edge>> G;
        private Queue<Pair> pq;
        private Edge[] edgeTo;
        private int[] distTo;
        private boolean[] visit;
        private int s;
        
        private static class Pair {
            private int v, weight;

            public Pair(int v, int weight) {
                this.v = v;
                this.weight = weight;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Pair pair = (Pair) o;
                return v == pair.v &&
                        weight == pair.weight;
            }

            @Override
            public int hashCode() {
                return Objects.hash(v, weight);
            }
        }
        
        public Dijkstra(Map<Integer, List<Edge>> G, int s) {
            this.G = G;
            this.s = s;
            edgeTo = new Edge[V+1];
            distTo = new int[V+1];
            visit = new boolean[V+1];
            for (int i=1; i<=V; i++)
                distTo[i] = V * 11;
            distTo[s] = 0;
            pq = new PriorityQueue<>(Comparator.comparingInt(v -> v.weight));
            pq.add(new Pair(s, distTo[s]));
            while (!pq.isEmpty()) {
                Pair v = pq.poll();
                if (visit[v.v]) continue;
                relax(v.v);
            }
        }

        private void relax(Integer v) {
            this.visit[v] = true;
            for (Edge e: G.get(v)) {
                if (distTo[e.to] > distTo[e.from] + e.weight) {
                    distTo[e.to] = distTo[e.from] + e.weight;
                    edgeTo[e.to] = e;
                    pq.add(new Pair(e.to, distTo[e.to]));
                }
            }
        }
        
        private int distTo(int v) {
            if (v == s) return 0;
            return edgeTo[v] == null? -1: distTo[v];
        }
    }
    
    private static class Edge {
        final int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}
