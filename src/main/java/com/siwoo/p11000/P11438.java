package com.siwoo.p11000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Used(algorithm = Algorithm.LCA)
public class P11438 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<Integer, List<Edge>> G = new HashMap<>();
    private static int N, M;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        for (int i=0; i<N-1; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            int v = Integer.parseInt(token.nextToken()),
                    w = Integer.parseInt(token.nextToken());
            Edge edge = new Edge(v, w);
            G.putIfAbsent(v, new ArrayList<>());
            G.putIfAbsent(w, new ArrayList<>());
            G.get(v).add(edge);
            G.get(w).add(edge.reverse());
        }
        LCA lca = new LCA(G, 1);
        M = Integer.parseInt(reader.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<M; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            int v = Integer.parseInt(token.nextToken()),
                    w = Integer.parseInt(token.nextToken());
            int u = lca.lowestAncestor(v, w);
            sb.append(u).append("\n");
        }
        System.out.println(sb);
    }
    
    private static class LCA {
        private int[] depth;
        private int[][] parent;
        private int maxDepth;

        public LCA(Map<Integer, List<Edge>> G, int root) {
            depth = new int[N+1];
            parent = new int[N+1][17];
            Queue<Integer> q = new PriorityQueue<>();
            q.add(root);
            Set<Integer> visit = new HashSet<>();
            visit.add(root);
            while (!q.isEmpty()) {
                int v = q.poll();
                for (Edge e: G.get(v)) {
                    if (!visit.contains(e.w)) {
                        visit.add(e.w);
                        depth[e.w] = depth[e.v] + 1;
                        maxDepth = Math.max(maxDepth, depth[e.w]);
                        parent[e.w][0] = e.v;
                        q.add(e.w);
                    }
                }
            }
            for (int k=1; (1<<k) < N; k++) {    //dp
                for (int node=1; node<=N; node++)
                    if (parent[node][k-1] != 0) 
                        // 2^k th ancestor
                        parent[node][k] = parent[parent[node][k-1]][k-1];
            }
        }

        public int lowestAncestor(int v, int w) {
            if (depth[v] < depth[w]) {
                int t = v;
                v = w;
                w = t;
            }
            int log;
            for (log=1; (1<<log) <= depth[v]; log++) ;
            log--;
            for (int k=log; k>=0; k--) {
                if (depth[v] - (1<<k) >= depth[w])
                    v = parent[v][k];
            }
            if (v == w) return v;
            for (int k=log; k>=0; k--) {
                if (parent[v][k] != 0 && parent[v][k] != parent[w][k]) {
                    v = parent[v][k];
                    w = parent[w][k];
                }
            }
            return parent[v][0];
        }
    }
    
    private static class Edge {
        int v, w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

        public Edge reverse() {
            return new Edge(w, v);
        }
    }
}
