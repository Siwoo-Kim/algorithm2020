package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Used(algorithm = Algorithm.DFS)
public class P1613 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<Integer, List<Edge>> G = new HashMap<>();
    private static int V, E;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        V = Integer.parseInt(token.nextToken());
        E = Integer.parseInt(token.nextToken());
        for (int i=1; i<=V; i++)
            G.putIfAbsent(i, new ArrayList<>());
        for (int i=0; i<E; i++) {
            token = new StringTokenizer(reader.readLine());
            int v = Integer.parseInt(token.nextToken()),
                    w = Integer.parseInt(token.nextToken());
            G.get(v).add(new Edge(v, w));
        }
        DFS[] dfs = new DFS[V+1];
        for (int v=1; v<=V; v++)
            dfs[v] = new DFS(G, v);
        
        int s = Integer.parseInt(reader.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<s; i++) {
            token = new StringTokenizer(reader.readLine());
            int v = Integer.parseInt(token.nextToken()),
                    w = Integer.parseInt(token.nextToken());
            if (dfs[v].visit.contains(w))
                sb.append(-1);
            else if (dfs[w].visit.contains(v))
                sb.append(1);
            else
                sb.append(0);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static class DFS {
        private Map<Integer, List<Edge>> G;
        private Set<Integer> visit;
        private int s;

        public DFS(Map<Integer, List<Edge>> G, int s) {
            this.G = G;
            this.s = s;
            visit = new HashSet<>();
            dfs(s);
        }

        private void dfs(int s) {
            visit.add(s);
            for (Edge e: G.get(s))
                if (!visit.contains(e.to))
                    dfs(e.to);
        }
    }
    
    private static class Edge {
        final int from, to;

        private Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }
}