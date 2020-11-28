package com.siwoo.p2000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Used(algorithm = Algorithm.TOPOLOGICAL_ORDER)
public class P2056 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<Vertex, List<Integer>> G = new HashMap<>();
    private static Map<Integer, Vertex> VERTEXES = new HashMap<>();
    private static int N;
    
    private static class Vertex {
        int v;
        int t;
        int degree; //inbound

        public Vertex(int v, int t, int degree) {
            this.v = v;
            this.t = t;
            this.degree = degree;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex vertex = (Vertex) o;
            return v == vertex.v;
        }

        @Override
        public int hashCode() {
            return Objects.hash(v);
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        
        for (int i=1; i<=N; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            Vertex v = new Vertex(i,
                    Integer.parseInt(token.nextToken()),
                    Integer.parseInt(token.nextToken()));
            G.put(v, new ArrayList<>());
            VERTEXES.put(i, v);
            for (int j=0; j<v.degree; j++) {
                int w = Integer.parseInt(token.nextToken());
                G.get(VERTEXES.get(w)).add(v.v);
            }
        }
        int x = scheduling(G);
        System.out.println(x);
    }

    private static int scheduling(Map<Vertex, List<Integer>> G) {
        Queue<Vertex> q = new LinkedList<>();
        Map<Vertex, Integer> distance = new HashMap<>();
        int max = 0;
        for (Vertex v: G.keySet()) {
            if (v.degree == 0) {
                distance.put(v, v.t);
                q.add(v);
            }
        }
        while (!q.isEmpty()) {
            Vertex v = q.poll();
            max = Math.max(distance.get(v), max);
            for (int wid: G.get(v)) {
                Vertex w = VERTEXES.get(wid);
                w.degree--;
                if (!distance.containsKey(w))       // 큐에 넣을 때가 아니라
                                                    // 해당 노드를 방문할때마다 최대 시간을 저장해야 한다.
                    distance.put(w, distance.get(v) + w.t);
                else
                    distance.put(w, Math.max(distance.get(w), distance.get(v) + w.t));
                if (w.degree == 0)
                    q.add(w);
            }
        }
        return max;
    }
}
