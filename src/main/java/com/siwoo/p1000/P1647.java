package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Used(algorithm = Algorithm.PRIM)
public class P1647 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<Integer, List<Edge>> G = new HashMap<>();
    private static int V, E;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        V = Integer.parseInt(token.nextToken());
        E = Integer.parseInt(token.nextToken());
        
        for (int i=0; i<E; i++) {
            token = new StringTokenizer(reader.readLine());
            int v = Integer.parseInt(token.nextToken()),
                    w = Integer.parseInt(token.nextToken()),
                    weight = Integer.parseInt(token.nextToken());
            Edge edge = new Edge(v, w, weight);
            G.putIfAbsent(edge.from, new ArrayList<>());
            G.putIfAbsent(edge.to, new ArrayList<>());
            G.get(edge.from).add(edge);
            G.get(edge.to).add(edge.reverse());
        }
        
        Prim mst = new Prim(G, 1);
        int max = 0;
        for (int i=2; i<=V; i++)
            max = Math.max(mst.edgeTo[i].weight, max);
        System.out.println(mst.weight() - max);
    }
    
    private static class Prim {
        private Map<Integer, List<Edge>> G;
        private Queue<Vertex> pq;
        private Set<Integer> mst;
        private Edge[] edgeTo;
        private int[] distTo;

        public Prim(Map<Integer, List<Edge>> G, int s) {
            this.G = G;
            mst = new HashSet<>();
            edgeTo = new Edge[V+1];
            distTo = new int[V+1];
            for (int v=1; v<=V; v++)
                distTo[v] = V * 1000 + 1;
            distTo[s] = 0;
            pq = new PriorityQueue<>(Vertex.C);
            pq.add(new Vertex(s, 0));
            while (!pq.isEmpty()) {
                Vertex v = pq.poll();
                visit(v);
            }
        }

        private void visit(Vertex v) {
            mst.add(v.v);
            for (Edge e: G.get(v.v)) {
                int w = e.to;
                if (mst.contains(w)) continue;
                if (distTo[w] > e.weight) {
                    edgeTo[w] = e;
                    distTo[w] = e.weight;
                    pq.add(new Vertex(w, distTo[w]));
                }
            }
        }
        
        public int weight() {
            int weight = 0;
            for (Edge e: edges())
                weight += e.weight;
            return weight;
        }
        
         public Iterable<Edge> edges() {
            Queue<Edge> mst = new LinkedList<>();
            for (int v=1; v<edgeTo.length; v++)
                if (edgeTo[v] != null)
                    mst.add(edgeTo[v]);
            return mst;
         }

        private static class Vertex {
            static Comparator<Vertex> C = Comparator.comparingInt(v -> v.weight);
            final int v, weight;

            public Vertex(int v, int weight) {
                this.v = v;
                this.weight = weight;
            }
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
    }
}
