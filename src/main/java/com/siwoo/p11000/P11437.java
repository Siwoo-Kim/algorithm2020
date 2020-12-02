package com.siwoo.p11000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Used(algorithm = Algorithm.LCA)
public class P11437 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, ROOT = 1;
    private static Map<Integer, List<Edge>> TREE = new HashMap<>();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        for (int i=0; i<N-1; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            int v = Integer.parseInt(token.nextToken()),
                    w = Integer.parseInt(token.nextToken());
            TREE.putIfAbsent(v, new ArrayList<>());
            TREE.putIfAbsent(w, new ArrayList<>());
            TREE.get(v).add(new Edge(v, w));
            TREE.get(w).add(new Edge(w, v));
        }
        M = Integer.parseInt(reader.readLine());
        LCA lca = new LCA(TREE);
        for (int i=0; i<M; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            int left = Integer.parseInt(token.nextToken()),
                    right = Integer.parseInt(token.nextToken());
            System.out.println(lca.lca(left, right));
        }
    }
    
    private static class LCA {
        private int[] depth, parent;

        public LCA(Map<Integer, List<Edge>> tree) {
            depth = new int[tree.size()+1];
            parent = new int[tree.size()+1];
            boolean[] visit = new boolean[tree.size()+1];
            Queue<Integer> q = new LinkedList<>();
            visit[ROOT] = true;
            q.add(ROOT);
            while (!q.isEmpty()) {
                int v = q.poll();
                for (Edge e: tree.get(v)) {
                    if (visit[e.to]) continue;
                    depth[e.to] = depth[e.from] + 1;
                    parent[e.to] = e.from;
                    visit[e.to] = true;
                    q.add(e.to);
                }
            }
        }

        public int lca(int left, int right) {
            if (depth[left] < depth[right]) {
                int t = left;
                left = right;
                right = t;
            }
            while (depth[left] != depth[right])
                left = parent[left];
            while (left != right) {
                left = parent[left];
                right = parent[right];
            }
            return left;
        }
    }
    
    private static class Edge {
        final int from, to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }
}
