package com.siwoo.p11000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 루트가 없는 트리의 정보가 주어졌을 때, root = 1 이라 가정하였을 때
 * 각 노드의 부모는 1 정점부터 시작하는 bfs
 *  bfs(v) => pathTo[w] = v
 */
@Used(algorithm = Algorithm.BFS)
public class P11725 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<Integer, List<Edge>> G = new HashMap<>();
    private static int N;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        for (int i=0; i<N-1; i++) {
            token = new StringTokenizer(reader.readLine());
            int v = Integer.parseInt(token.nextToken()),
                    w = Integer.parseInt(token.nextToken());
            G.computeIfAbsent(v, k -> new ArrayList<>());
            G.computeIfAbsent(w, k -> new ArrayList<>());
            G.get(v).add(new Edge(v, w));
            G.get(w).add(new Edge(w, v));
        }
        int[] pathTo = new int[N+1];
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        while (!q.isEmpty()) {
            int v = q.poll();
            for (Edge edge: G.get(v)) {
                if (pathTo[edge.w] == 0) {
                    pathTo[edge.w] = v;
                    q.add(edge.w);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i=2; i<=N; i++)
            sb.append(pathTo[i]).append("\n");
        System.out.println(sb.toString());
    }

    private static class Edge {
        private final int v, w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}
