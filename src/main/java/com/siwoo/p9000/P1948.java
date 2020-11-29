package com.siwoo.p9000;

import com.siwoo.util.Marker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Marker
public class P1948 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<Integer, List<Edge>> G = new HashMap<>();
    private static Map<Integer, List<Edge>> RG = new HashMap<>();
    private static int N, M;
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        M = Integer.parseInt(reader.readLine());
        for (int i=1; i<=N; i++) {
            G.put(i, new ArrayList<>());
            RG.put(i, new ArrayList<>());
        }
        for (int i=0; i<M; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            int v = Integer.parseInt(token.nextToken()),
                    w = Integer.parseInt(token.nextToken()),
                    weight = Integer.parseInt(token.nextToken());
            Edge edge = new Edge(v, w, weight);
            G.get(v).add(edge);
            RG.get(w).add(edge.reverse());
        }
        StringTokenizer token = new StringTokenizer(reader.readLine());
        int s = Integer.parseInt(token.nextToken()),
                e = Integer.parseInt(token.nextToken());
        TopologicalOrder tpo = new TopologicalOrder(G);
        System.out.println(tpo.distTo[e]);
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visit = new HashSet<>();
        visit.add(e);
        q.add(e);
        int answer = 0;
        while (!q.isEmpty()) {
            int v = q.poll();
            for (Edge edge: RG.get(v)) {
                int w = edge.to;
                if (visit.contains(v) && 
                        tpo.distTo[v] - tpo.distTo[w] == edge.weight) {
                    answer++;
                    visit.add(w);
                }
            }
        }
        System.out.println(answer);
    }
    
    private static class TopologicalOrder {
        private Map<Integer, List<Edge>> G;
        private boolean[] visit;
        private int[] distTo;
        private Stack<Integer> reversedPostOrder;

        public TopologicalOrder(Map<Integer, List<Edge>> G) {
            this.G = G;
            visit = new boolean[N+1];
            distTo = new int[N+1];
            reversedPostOrder = new Stack<>();
            for (int v: G.keySet()) {
                if (!visit[v])
                    dfs(v);
            }
        }

        private void dfs(int v) {
            visit[v] = true;
            for (Edge e: G.get(v)) {
                if (visit[e.to]) continue;
                distTo[e.to] = Math.max(distTo[e.to], distTo[e.from] + e.weight);
                dfs(e.to);
            }
            reversedPostOrder.add(v);
        }
    }
    
    private static class Edge {
        final int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public Edge reverse() {
            return new Edge(to, from, weight);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return from == edge.from &&
                    to == edge.to &&
                    weight == edge.weight;
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to, weight);
        }
    }
}
