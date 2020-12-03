package com.siwoo.p1000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1761 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<Integer, List<Edge>> G = new HashMap<>();
    private static int V, M;

    public static void main(String[] args) throws IOException {
        V = Integer.parseInt(reader.readLine());
        for (int i=0; i<V-1; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            int v = Integer.parseInt(token.nextToken()),
                    w = Integer.parseInt(token.nextToken()),
                    weight = Integer.parseInt(token.nextToken());
            G.putIfAbsent(v, new ArrayList<>());
            G.putIfAbsent(w, new ArrayList<>());
            Edge edge = new Edge(v, w, weight);
            G.get(v).add(edge);
            G.get(w).add(edge.reverse());
        }
        LCA lca = new LCA(G, 1);
        M = Integer.parseInt(reader.readLine());
        for (int i=0; i<M; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            int v = Integer.parseInt(token.nextToken()),
                    w = Integer.parseInt(token.nextToken());
            int u = lca.lowestAncestor(v, w);
            System.out.println(lca.distTo[v] + lca.distTo[w] -(lca.distTo[u] * 2));
        }
    }
    
    private static class LCA {
        private int[] depth, parent, distTo;

        public LCA(Map<Integer, List<Edge>> G, int root) {
            depth = new int[V+1];
            parent = new int[V+1];
            distTo = new int[V+1];
            parent[root] = root;
            Set<Integer> visit = new HashSet<>();
            visit.add(root);
            Queue<Integer> q = new LinkedList<>();
            q.add(root);
            while (!q.isEmpty()) {
                int v = q.poll();
                for (Edge e: G.get(v)) {
                    if (!visit.contains(e.w)) {
                        depth[e.w] = depth[e.v] + 1;
                        parent[e.w] = e.v;
                        distTo[e.w] = distTo[e.v] + e.weight;
                        visit.add(e.w);
                        q.add(e.w);
                    }
                }
            }
        }

        public int lowestAncestor(int v, int w) {
            if (depth[v] < depth[w]) {
                int t = v;
                v = w;
                w = t;
            }
            while (depth[v] != depth[w])
                v = parent[v];
            while (v != w) {
                v = parent[v];
                w = parent[w];
            }
            return v;
        }
    }
    
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
}
