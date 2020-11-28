package com.siwoo.p11000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Used(algorithm = Algorithm.BELLMAN_FORD)
public class P11657 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<Integer, List<Edge>> G = new HashMap<>();
    private static int N, M;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        for (int i=1; i<=N; i++)
            G.putIfAbsent(i, new ArrayList<>());
        for (int i = 0; i < M; i++) {
            token = new StringTokenizer(reader.readLine());
            int v = Integer.parseInt(token.nextToken()),
                    w = Integer.parseInt(token.nextToken()),
                    weight = Integer.parseInt(token.nextToken());
            Edge edge = new Edge(v, w, weight);
            G.get(edge.from).add(edge);
        }
        int[] distTo = bellmanFord(G, 1);
        if (distTo == null) { //cycle
            System.out.println(-1);
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i<=N; i++)
            sb.append(distTo[i] == Integer.MAX_VALUE ? -1 : distTo[i]).append("\n");
        System.out.println(sb.toString());
    }
    
    private static int cost;    //relaxation count
    private static boolean cycle;
    
    private static int[] bellmanFord(Map<Integer, List<Edge>> G, int s) {
        int[] distTo = new int[N+1];
        for (int v: G.keySet())
            distTo[v] = Integer.MAX_VALUE;
        Queue<Integer> q = new LinkedList<>();
        boolean[] onQ = new boolean[N+1];
        Edge[] edgeTo = new Edge[N+1];
        distTo[s] = 0;
        onQ[s] = true;
        q.add(s);
        while (!q.isEmpty()) {
            if (cycle) return null;
            int v = q.poll();
            onQ[v] = false;
            visit(G, q, edgeTo, v, distTo, onQ);
        }
        return distTo;
    }

    private static void visit(Map<Integer, List<Edge>> G, 
                              Queue<Integer> q,
                              Edge[] edgeTo, 
                              int v, 
                              int[] distTo, 
                              boolean[] onQ) {
        for (Edge e: G.get(v)) {
            if (distTo[e.to] > distTo[e.from] + e.weight) {
                distTo[e.to] = distTo[e.from] + e.weight;
                edgeTo[e.to] = e;
                if (!onQ[e.to]) {
                    onQ[e.to] = true;
                    q.add(e.to);
                }
            }
        }
        if (cost++ % N == 0)
            detectCycle(G, edgeTo);
    }

    private static boolean detectCycle(Map<Integer, List<Edge>> G, Edge[] edgeTo) {
        Map<Integer, List<Edge>> spt = new HashMap<>();
        for (int v: G.keySet()) {
            if (edgeTo[v] != null) {
                spt.putIfAbsent(edgeTo[v].from, new ArrayList<>());
                spt.putIfAbsent(edgeTo[v].to, new ArrayList<>());
                spt.get(edgeTo[v].from).add(edgeTo[v]);
            }
        }
        Set<Integer> visit = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        for (int v: spt.keySet())
            if (!visit.contains(v))
                if (detectCycle(spt, visit, stack, v))
                    return cycle = true;
        return false;
    }

    private static boolean detectCycle(Map<Integer, List<Edge>> G, Set<Integer> visit, Stack<Integer> stack, int v) {
        visit.add(v);
        stack.push(v);
        for (Edge edge: G.get(v)) {
            if (!visit.contains(edge.to)) {
                if (detectCycle(G, visit, stack, edge.to))
                    return true;
            }
            else if (stack.contains(edge.to))
                return true;
        }
        stack.pop();
        return false;
    }

    private static class Edge {
        private int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }
}
