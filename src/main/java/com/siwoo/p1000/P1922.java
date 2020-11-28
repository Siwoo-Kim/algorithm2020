package com.siwoo.p1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1922 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static Map<Integer, List<Edge>> G = new HashMap<>();
    
    private static class Edge {
        final int v, w, weight;

        public Edge(int v, int w, int weight) {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }
        
        public Edge reverse() {
            return new Edge(w, v, weight);
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        M = Integer.parseInt(reader.readLine());
        Integer x = null;
        for (int i=0; i<M; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            int v = Integer.parseInt(token.nextToken()),
                    w = Integer.parseInt(token.nextToken()),
                    weight = Integer.parseInt(token.nextToken());
            Edge edge = new Edge(v, w, weight);
            if (x == null)
                x = v;
            G.putIfAbsent(v, new ArrayList<>());
            G.putIfAbsent(w, new ArrayList<>());
            G.get(v).add(edge);
            G.get(w).add(edge.reverse());
        }
        int answer = mst(G, x);
        System.out.println(answer);
    }

    private static int mst(Map<Integer, List<Edge>> G, int u) {
        Queue<Edge> q = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        Set<Integer> visit = new HashSet<>();
        for (Edge edge: G.get(u))
            q.add(edge);
        visit.add(u);
        int answer = 0;
        while (!q.isEmpty()) {
            Edge edge = q.poll();
            if (visit.contains(edge.w)) continue;
            visit.add(edge.w);
            answer += edge.weight;
            for (Edge ee: G.get(edge.w)) {
                q.add(ee);
            }
        }
        return answer;
    }
}
