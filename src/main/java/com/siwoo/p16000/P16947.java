package com.siwoo.p16000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 무방향 그래프에서 싸이클 탐지 알고리즘 -> dfs(p, pv) -> if (v != pv) then dfs(v, p)
 * 싸이클에 해당하는 정점 확인 -> while (stack.peek != cycle) set.add
 *  eg) 1 -> 2 -> 3 -> 4 -> 2
 *      stack = 1, 2, 3, 4, 2 cycle=2
 *      while(stack.peek != 2)
 *          -> 4, 3, 2
 *
 * 싸이클에서의 각 정점 거리 확인 -> bfs
 */
@Used(algorithm = Algorithm.DFS)
public class P16947 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<Integer, List<Edge>> G = new HashMap<>();
    private static Set<Integer> CYCLE = new HashSet<>();
    private static int N;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        for (int i=0; i<N; i++) {
            token = new StringTokenizer(reader.readLine());
            Edge edge = new Edge(Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()));
            G.computeIfAbsent(edge.v, v -> new LinkedList<>());
            G.computeIfAbsent(edge.w, v -> new LinkedList<>());
            G.get(edge.v).add(edge);
            G.get(edge.w).add(edge.reverse());
        }

        Set<Integer> visit = new HashSet<>();
        int v = G.keySet().stream().findAny().get();
        //one connected component in the graph
        dfs(v, -1, new Stack<>(), visit);

        Map<Integer, Integer> distance = new HashMap<>();
        for (int c: CYCLE)
            distance.put(c, 0);
        Queue<Integer> q = new LinkedList<>(CYCLE);
        while (!q.isEmpty()) {
            v = q.poll();
            for (Edge edge: G.get(v)) {
                if (!distance.containsKey(edge.w)) {
                    distance.put(edge.w, distance.get(v) + 1);
                    q.add(edge.w);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (v=1; v<=N; v++)
            sb.append(distance.get(v)).append(" ");
        System.out.println(sb.toString());
    }

    private static boolean dfs(int v, int pv, Stack<Integer> stack, Set<Integer> visit) {
        if (visit.contains(v)) {
            //cycle find
            while (stack.peek() != v)
                CYCLE.add(stack.pop());
            CYCLE.add(v);
            return true;
        }
        visit.add(v);
        stack.push(v);
        for (Edge edge: G.get(v)) {
            if (edge.w != pv)   //no mean in undirected graph
                if (dfs(edge.w, v, stack, visit))
                    return true;
        }
        stack.pop();
        return false;
    }

    static class Edge {
        final int v, w;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

        public Edge reverse() {
            return new Edge(w, v);
        }
    }
}
