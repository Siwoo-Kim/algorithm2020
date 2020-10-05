package com.siwoo.p16000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *  DFS, BFS 의 PATH 순서는
 *  그래프의 각 정점 v 에 대한 링크드리스트의 순서에 의해 정해진다.
 */
public class P16964 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<Integer, List<Edge>> G = new HashMap<>();
    private static Map<Integer, Integer> precedence = new HashMap<>();
    private static List<Integer> dfsOrder = new ArrayList<>();
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        for (int i=0; i<N-1; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            Edge edge = new Edge(Integer.parseInt(token.nextToken()),
                    Integer.parseInt(token.nextToken()));
            G.computeIfAbsent(edge.v, k -> new ArrayList<>());
            G.computeIfAbsent(edge.w, k -> new ArrayList<>());
            G.get(edge.v).add(edge);
            G.get(edge.w).add(edge.reverse());
        }
        int[] order = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        for (int i=0; i<order.length; i++)
            precedence.put(order[i], i);
        for (int v: G.keySet())
            G.get(v).sort(Comparator.comparingInt(edge -> precedence.get(edge.w)));

        dfs(1, new HashSet<>());
        for (int i=0; i<order.length; i++) {
            if (order[i] != dfsOrder.get(i)) {
                System.out.println(0);
                return;
            }
        }
        System.out.println(1);
    }

    private static void dfs(int v, Set<Integer> visit) {
        visit.add(v);
        dfsOrder.add(v);
        for (Edge edge: G.get(v)) {
            if (!visit.contains(edge.w))
                dfs(edge.w, visit);
        }
    }

    private static class Edge {
        private final int v, w;

        private Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

        public Edge reverse() {
            return new Edge(w, v);
        }
    }
}
