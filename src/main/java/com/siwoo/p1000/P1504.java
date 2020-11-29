package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Used(algorithm = Algorithm.DIJKSTRA)
public class P1504 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int V, E;
    private static Map<Integer, List<Edge>> G = new HashMap<>();
    
    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        V = Integer.parseInt(token.nextToken());
        E = Integer.parseInt(token.nextToken());
        for (int i=1; i<=V; i++)
            G.put(i, new ArrayList<>());
        for (int i=0; i<E; i++) {
            token = new StringTokenizer(reader.readLine());
            int v = Integer.parseInt(token.nextToken()),
                    w = Integer.parseInt(token.nextToken()),
                    weight = Integer.parseInt(token.nextToken());
            Edge edge = new Edge(v, w, weight);
            G.get(v).add(edge);
            G.get(w).add(edge.reverse());
        }
        token = new StringTokenizer(reader.readLine());
        int v1 = Integer.parseInt(token.nextToken()),
                v2 = Integer.parseInt(token.nextToken());
        Dijkstra source = new Dijkstra(G, 1);
        Dijkstra fromOne = new Dijkstra(G, v1);
        Dijkstra fromTwo = new Dijkstra(G, v2);

        if (source.distanceOf(v1) == -1 || 
            source.distanceOf(v2) == -1 ||
            source.distanceOf(V) == -1) {
            System.out.println(-1);
            return;
        }
        // s -> v1 -> v2 -> v
        int min = source.distanceOf(v1) + fromOne.distanceOf(v2) + fromTwo.distanceOf(V);
        // s -> v2 -> v1 -> v
        min = Math.min(min, source.distanceOf(v2) + fromTwo.distanceOf(v1) + fromOne.distanceOf(V));
        System.out.println(min);
    }
    
    private static class Dijkstra {
        private final int s, MAX;
        private Map<Integer, List<Edge>> G;
        private boolean[] visit;
        private int[] distTo;
        private Queue<Pair> pq = new PriorityQueue<>(Pair.C);
        
        private static class Pair {
            static final Comparator<Pair> C = Comparator.comparingInt(p -> p.weight);
            int v, weight;

            public Pair(int v, int weight) {
                this.v = v;
                this.weight = weight;
            }
        }

        public Dijkstra(Map<Integer, List<Edge>> G, int s) {
            this.G = G;
            this.s = s;
            this.MAX = V * 1000 + 1;
            this.visit = new boolean[V+1];
            this.distTo = new int[V+1];
            Arrays.fill(distTo, MAX);
            distTo[s] = 0;
            pq.add(new Pair(s, distTo[s]));
            while (!pq.isEmpty()) {
                Pair p = pq.poll();
                if (visit[p.v]) continue;
                relax(p.v);
            }
        }

        private void relax(int v) {
            visit[v] = true;
            for (Edge e: G.get(v)) {
                if (distTo[e.to] > distTo[e.from] + e.weight) {
                    distTo[e.to] = distTo[e.from] + e.weight;
                    pq.add(new Pair(e.to, distTo[e.to]));
                }
            }
        }
        
        public int distanceOf(int v) {
            return distTo[v] == MAX? -1: distTo[v];
        }
    }
    
    private static class Edge {
        int from, to, weight;

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
