package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

@Used(algorithm = Algorithm.DIJKSTRA)
public class P1956 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<Integer, List<Edge>> G = new HashMap<>();
    private static int V, E, MAX;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        V = Integer.parseInt(token.nextToken());
        E = Integer.parseInt(token.nextToken());
        IntStream.rangeClosed(1, V).forEach(v -> G.put(v, new ArrayList<>()));
        MAX = 10000 * 2 * V + 1;
        for (int i=0; i<E; i++) {
            token = new StringTokenizer(reader.readLine());
            int v = Integer.parseInt(token.nextToken()),
                    w = Integer.parseInt(token.nextToken()),
                    weight = Integer.parseInt(token.nextToken());
            Edge edge = new Edge(v, w, weight);
            G.get(v).add(edge);
        }
        
        Dijkstra[] dijkstra = new Dijkstra[V+1];
        int min = MAX;
        for (int v=1; v<=V; v++) {
            dijkstra[v] = new Dijkstra(G, v);
            min = Math.min(MAX, dijkstra[v].distTo[v]);
        }
        System.out.println(min);
    }
    
    private static class Dijkstra {
        private Map<Integer, List<Edge>> G;
        private Queue<State> pq;
        private int[] distTo;
        private boolean[] visit;
        private int s;
        
        private static class State {
            static Comparator<State> C = Comparator.comparingInt(c -> c.weight);
            final int v, weight;

            public State(int v, int weight) {
                this.v = v;
                this.weight = weight;
            }
        }
        
        public Dijkstra(Map<Integer, List<Edge>> G, int s) {
            this.G = G;
            this.s = s;
            pq = new PriorityQueue<>(State.C);
            distTo = new int[V+1];
            visit = new boolean[V+1];
            for (int v=1; v<=V; v++)
                distTo[v] = MAX;
            pq.add(new State(s, 0));
            while (!pq.isEmpty()) {
                State state = pq.poll();
                if (visit[state.v] && state.v != s) 
                    continue;
                relax(state);
            }
        }

        private void relax(State state) {
            visit[state.v] = true;
            for (Edge e: G.get(state.v)) {
                if (distTo[e.to] > state.weight + e.weight) {
                    distTo[e.to] = state.weight + e.weight;
                    pq.add(new State(e.to, distTo[e.to]));
                }
            }
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
