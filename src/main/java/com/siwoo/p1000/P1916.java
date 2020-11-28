package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Used(algorithm = Algorithm.DIJKSTRA)
public class P1916 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<Integer, List<Edge>> G = new HashMap<>();
    private static int N, M, S, E;

    private static class Edge {
        final int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        M = Integer.parseInt(reader.readLine());
        StringTokenizer token;
        for (int i=0; i<M; i++) {
            token = new StringTokenizer(reader.readLine());
            int v = Integer.parseInt(token.nextToken()),
                    w = Integer.parseInt(token.nextToken()),
                    weight = Integer.parseInt(token.nextToken());
            Edge edge = new Edge(v, w, weight);
            G.putIfAbsent(v, new ArrayList<>());
            G.putIfAbsent(w, new ArrayList<>());
            G.get(v).add(edge);
        }
        token = new StringTokenizer(reader.readLine());
        S = Integer.parseInt(token.nextToken());
        E = Integer.parseInt(token.nextToken());
        
        int[] distTo = dijkstra(G, S);
        System.out.println(distTo[E]);
    }

    private static int[] dijkstra(Map<Integer, List<Edge>> G, int s) {
        int[] distTo = new int[N+1];
        for (int i=1; i<=N; i++)
            distTo[i] = Integer.MAX_VALUE;
        boolean[] visit = new boolean[N+1];
        distTo[s] = 0;
        Queue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(x -> distTo[x]));
        pq.add(s);
        while (!pq.isEmpty()) {
            int v = pq.poll();
            if (visit[v]) continue;
            relax(G, pq, visit, distTo, v);
        }
        return distTo;
    }

    private static void relax(Map<Integer, List<Edge>> G, Queue<Integer> pq,
                              boolean[] visit, int[] distTo, Integer v) {
        visit[v] = true;
        for (Edge e: G.get(v)) {
            if (distTo[e.to] > distTo[e.from] + e.weight) {
                distTo[e.to] = distTo[e.from] + e.weight;
                pq.add(e.to);
            }
        }
    }
}
