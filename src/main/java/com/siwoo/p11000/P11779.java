package com.siwoo.p11000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Used(algorithm = Algorithm.DIJKSTRA)
public class P11779 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<Integer, List<Edge>> G = new HashMap<>();
    private static int N, M, S, E;
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        M = Integer.parseInt(reader.readLine());
        StringTokenizer token;
        for (int i=1; i<=N; i++)
            G.put(i, new ArrayList<>());
        for (int i=0; i<M; i++) {
            token = new StringTokenizer(reader.readLine());
            int v = Integer.parseInt(token.nextToken()),
                    w = Integer.parseInt(token.nextToken()),
                    weight = Integer.parseInt(token.nextToken());
            Edge edge = new Edge(v, w, weight);
            G.get(edge.from).add(edge);
        }
        token = new StringTokenizer(reader.readLine());
        S = Integer.parseInt(token.nextToken());
        E = Integer.parseInt(token.nextToken());
        Dijkstra dijkstra = new Dijkstra(G, S);
        Stack<Integer> stack = new Stack<>();
        int v = E;
        while (v != S) {
            stack.push(v);
            v = dijkstra.edgeTo[v].from;
        }
        stack.push(S);
        System.out.println(dijkstra.distTo[E]);
        System.out.println(stack.size());
        while (!stack.isEmpty())
            System.out.printf("%d ", stack.pop());
    }

    private static class Dijkstra {
        private Edge[] edgeTo;
        private int[] distTo;
        private boolean[] visit;

        public Dijkstra(Map<Integer, List<Edge>> G, int s) {
            edgeTo = new Edge[N+1];
            visit = new boolean[N+1];
            distTo = new int[N+1];
            for (int i=1; i<=N; i++)
                distTo[i] = 1000*100000+1;
            distTo[s] = 0;
            Queue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(x -> distTo[x]));
            pq.add(s);
            while (!pq.isEmpty()) {
                int v = pq.poll();
                if (visit[v]) continue;
                relax(G, pq, edgeTo, visit, distTo, v);
            }
        }

        private void relax(Map<Integer, List<Edge>> G, 
                           Queue<Integer> pq, Edge[] edgeTo, 
                           boolean[] visit, int[] distTo, Integer v) {
            visit[v] = true;
            for (Edge e: G.get(v)) {
                if (distTo[e.to] > distTo[e.from] + e.weight) {
                    distTo[e.to] = distTo[e.from] + e.weight;
                    edgeTo[e.to] = e;
                    pq.add(e.to);
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
