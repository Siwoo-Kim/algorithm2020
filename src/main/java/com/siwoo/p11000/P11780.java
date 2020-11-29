package com.siwoo.p11000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

@Used(algorithm = Algorithm.DIJKSTRA)
public class P11780 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<Integer, List<Edge>> G = new HashMap<>();
    private static int N, M, MAX_COST;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        M = Integer.parseInt(reader.readLine());
        MAX_COST = N * 100000 + 1;
        IntStream.rangeClosed(1, N).forEach(v -> G.put(v, new ArrayList<>()));
        for (int i=0; i<M; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            int v = Integer.parseInt(token.nextToken()),
                    w = Integer.parseInt(token.nextToken()),
                    weight = Integer.parseInt(token.nextToken());
            Edge edge = new Edge(v, w, weight);
            G.get(edge.from).add(edge);
        }
        Dijkstra[] dijkstras = new Dijkstra[N+1];
        for (int v=1; v<=N; v++)
            dijkstras[v] = new Dijkstra(G, v);
        StringBuilder sb = new StringBuilder();
        for (int v=1; v<=N; v++) {
            int[] distTo = dijkstras[v].distTo;
            for (int w=1; w<=N; w++) {
                if (!dijkstras[v].hasPath(w)) sb.append(0);
                else sb.append(distTo[w]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        for (int v=1; v<=N; v++) {
            Edge[] edgeTo = dijkstras[v].edgeTo;
            for (int w=1; w<=N; w++) {
                if (!dijkstras[v].hasPath(w)) {
                    sb.append(0).append("\n");
                    continue;
                }
                printTo(sb, v, w, 0, edgeTo);
                sb.append("\n");
            }
        }
        
        System.out.println(sb);
    }

    private static void printTo(StringBuilder sb, int v, int w, int size, Edge[] edge) {
        if (w == v) {
            sb.append(size + 1).append(" ");
            sb.append(w);
            return;
        }
        printTo(sb, v, edge[w].from, size+1, edge);
        sb.append(" ").append(w);
    }

    private static class Dijkstra {
        private Map<Integer, List<Edge>> G;
        private Edge[] edgeTo;
        private int[] distTo;
        private boolean[] visit;
        private int s;
        private Queue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.weight));

        public Dijkstra(Map<Integer, List<Edge>> G, int s) {
            this.G = G;
            this.s = s;
            edgeTo = new Edge[N+1];
            distTo = new int[N+1];
            visit = new boolean[N+1];
            Arrays.fill(distTo, MAX_COST);
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
                    edgeTo[e.to] = e;
                    pq.add(new Pair(e.to, distTo[e.to]));
                }
            }
        }

        public boolean hasPath(int v) {
            return v != s && edgeTo[v] != null;
        }

        private static class Pair {
            int v, weight;

            public Pair(int v, int weight) {
                this.v = v;
                this.weight = weight;
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
