package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Used(algorithm = Algorithm.DIJKSTRA)
public class P1854 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int V, E, K;
    private static Map<Integer, List<Edge>> G = new HashMap<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        V = Integer.parseInt(token.nextToken());
        E = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());

        for (int i=1; i<=V; i++)
            G.put(i, new ArrayList<>());
        for (int i=0; i<E; i++) {
            token = new StringTokenizer(reader.readLine());
            int v = Integer.parseInt(token.nextToken()),
                    w = Integer.parseInt(token.nextToken()),
                    weight = Integer.parseInt(token.nextToken());
            Edge edge = new Edge(v, w, weight);
            G.get(v).add(edge);
        }

        Dijkstra dijkstra = new Dijkstra(G, 1);
        for (int v=1; v<=V; v++) {
            if (!dijkstra.hasPath(v))
                System.out.println(-1);
            else
                System.out.println(dijkstra.dists[v].peek());
        }
    }

    private static class Dijkstra {
        private Map<Integer, List<Edge>> G;
        private Queue<Integer>[] dists;
        private boolean[] visit;
        private Queue<Pair> pq;
        private int s, MAX;

        private static class Pair {
            static final Comparator<Pair> C = Comparator.comparingInt((Pair p) -> p.weight);
            final int v, weight;
            private Pair(int v, int weight) {
                this.v = v;
                this.weight = weight;
            }
        }

        public Dijkstra(Map<Integer, List<Edge>> G, int s) {
            this.G = G;
            this.s = s;
            dists = new PriorityQueue[V+1];
            MAX = V*K*1000+1;
            for (int v=1; v<=V; v++) {
                dists[v] = new PriorityQueue<>(Comparator.comparingInt((Integer d) -> -d));
                for (int k=0; k<K; k++)
                    dists[v].add(MAX);
            }
            dists[s].poll();
            dists[s].add(0);
            visit = new boolean[V+1];
            pq = new PriorityQueue<>(Pair.C);
            pq.add(new Pair(s, 0));
            while (!pq.isEmpty()) {
                Pair p = pq.poll();
                relax(p);
            }
        }

        private void relax(Pair p) {
            for (Edge e: G.get(p.v)) {
                if (dists[e.to].peek() > p.weight + e.weight) {
                    dists[e.to].poll();
                    dists[e.to].add(p.weight + e.weight);
                    pq.add(new Pair(e.to, p.weight + e.weight));
                }
            }
        }

        public boolean hasPath(int v) {
            return dists[v].peek() != MAX;
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
