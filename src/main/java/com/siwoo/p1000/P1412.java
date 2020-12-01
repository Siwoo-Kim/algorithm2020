package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 무방향 그래프에서 방향을 정하여 싸이클이 없는 방향 그래프를 만들고 싶다면 
 * 특정한 제약된 기준으로 한쪽 방향으로만 계속 이어주면 사이클이 없어진다.
 * 
 *  eg) min(u,v) -> max(u,v) 정점의 정수이고 크기가 작은 것을 크기가 큰 것으로만 이어주면
 *      싸이클이 없어진다.
 *
 * 즉, 이 문제는 방향 간선이 싸이클이 없다면, 무방향 간선을 고려하지 않아도
 * 해당 그래프을 항상 싸이클이 없는 그래프로 바꿀 수 있다.
 * 
 */
@Used(algorithm = Algorithm.DFS)
public class P1412 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static Map<Integer, List<Edge>> G = new HashMap<>();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        for (int v=0; v<N; v++) {
            String line = reader.readLine();
            G.put(v, new ArrayList<>());
            for (int w=0; w<N; w++) {
                Edge edge = new Edge(v, w);
                if (line.charAt(w) == 'Y' 
                        && G.get(w) != null 
                        && G.get(w).contains(edge.reverse()))
                    G.get(w).remove(edge.reverse());
                else if (line.charAt(w) == 'Y')
                    G.get(v).add(new Edge(v, w));
            }
        }
        boolean cycle = cycle(G);
        System.out.println(cycle ? "NO": "YES");
    }

    private static boolean cycle(Map<Integer, List<Edge>> G) {
        Set<Integer> visit = new HashSet<>();
        Set<Integer> stack = new HashSet<>();
        for (int v: G.keySet())
            if (!visit.contains(v))
                if (cycle(v, visit, stack))
                    return true;
        return false;
    }

    private static boolean cycle(int v, Set<Integer> visit, Set<Integer> stack) {
        visit.add(v);
        stack.add(v);
        for (Edge e: G.get(v)) {
            if (!visit.contains(e.to)) {
                if (cycle(e.to, visit, stack))
                    return true;
            } else if (stack.contains(e.to))
                return true;
        }
        stack.remove(v);
        return false;
    }

    private static class Edge {
        private int from, to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }

        public Edge reverse() {
            return new Edge(to, from);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return from == edge.from &&
                    to == edge.to;
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }
    }
}
