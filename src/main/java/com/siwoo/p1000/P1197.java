package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

@Used(algorithm = Algorithm.KRUSKAL)
public class P1197 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Edge[] edges;
    private static int V, E;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        V = Integer.parseInt(token.nextToken());
        E = Integer.parseInt(token.nextToken());
        edges = new Edge[E];
        for (int i=0; i<E; i++) {
            token = new StringTokenizer(reader.readLine());
            int v = Integer.parseInt(token.nextToken()),
                    w = Integer.parseInt(token.nextToken()),
                    weight = Integer.parseInt(token.nextToken());
            edges[i] = new Edge(v, w, weight);
        }
        System.out.println(mst(edges));
    }
    
    private static int mst(Edge[] edges) {
        Arrays.sort(edges, Edge.C);
        UF uf = new UF(V);
        int dist = 0;
        for (Edge edge: edges) {
            if (uf.isConnected(edge.v, edge.w)) continue;
            dist += edge.weight;
            uf.connect(edge.v, edge.w);
        }
        return dist;
    }
    
    private static class UF {
        int[] components;
        int[] sizes;
        int size = 0;

        UF(int size) {
            this.size = size;
            this.components = new int[size+1];
            this.sizes = new int[size+1];
            for (int i=1; i<=size; i++) {
                components[i] = i;
                sizes[i] = 1;
            }
        }
        
        boolean connect(int v, int w) {
            if (isConnected(v, w)) return false;
            v = get(v);
            w = get(w);
            if (sizes[v] < sizes[w]) {
                int t = v;
                v = w;
                w = t;
            }
            components[w] = v;
            sizes[v] += sizes[w];
            return true;
        }
        
        boolean isConnected(int v, int w) {
            return get(v) == get(w);
        }
        
        int get(int v) {
            if (components[v] == v)
                return v;
            return components[v] = get(components[v]);
        }
    }
    
    private static class Edge {
        private static Comparator<Edge> C = Comparator.comparingInt((Edge e) -> e.weight);
        private int v, w, weight;

        public Edge(int v, int w, int weight) {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }
    }
}
