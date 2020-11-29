package com.siwoo.p10000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Used(algorithm = Algorithm.FLOYD_MARSHALL)
public class P11403 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<Integer, List<Edge>> G = new HashMap<>();
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        for (int v=0; v<N; v++) {
            int[] adj = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            G.put(v, new ArrayList<>());
            for (int w=0; w<N; w++) {
                if (adj[w] == 1) {
                    Edge edge = new Edge(v, w);
                    G.get(v).add(edge);
                }
            }
        }
        
        FloydMarshall fm = new FloydMarshall(G);
        StringBuilder sb = new StringBuilder();
        int[][] d = fm.dist;
        for (int v=0; v<N; v++) {
            for (int w=0; w<N; w++)
                if (d[v][w] == fm.UNREACHABLE)
                    sb.append(0).append(" ");
                else
                    sb.append(1).append(" ");
            sb.append("\n");
        }
        System.out.println(sb);
    }
    
    private static class FloydMarshall {
        private Map<Integer, List<Edge>> G;
        private int[][] dist;
        private int UNREACHABLE;
        
        public FloydMarshall(Map<Integer, List<Edge>> G) {
            this.G = G;
            UNREACHABLE = G.size() + 1;
            dist = new int[N][N];
            Arrays.stream(dist).forEach(d -> Arrays.fill(d, UNREACHABLE));
            for (int v: G.keySet()) {
                for (Edge e : G.get(v))
                    dist[e.from][e.to] = 1;
            }
            for (int k=0; k<G.size(); k++)
                for (int v=0; v<G.size(); v++)
                    for (int w=0; w<G.size(); w++)
                        if (dist[v][w] > dist[v][k] + dist[k][w]) {
                            dist[v][w] = dist[v][k] + dist[k][w];
                        }
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
