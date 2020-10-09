package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 트리의 지름 구하기.
 *  bfs(v) = {u=정점 v 에서 가장 먼 정점, d=u-v 거리} 이라 한다면,
 *  bfs(bfs(v).u).d = 트리의 지름
 */
@Used(algorithm = Algorithm.BFS)
public class P1167 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static Map<Integer, Set<Edge>> G = new HashMap<>();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        for (int i=1; i<=N; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            int v = Integer.parseInt(token.nextToken());
            int w = -1;
            G.computeIfAbsent(v, k -> new HashSet<>());
            while ((w = Integer.parseInt(token.nextToken())) != -1) {
                Edge edge = new Edge(v, w, Integer.parseInt(token.nextToken()));
                G.computeIfAbsent(w, k -> new HashSet<>());
                G.get(v).add(edge);
                G.get(v).add(new Edge(w, v, edge.distance));
            }
        }
        System.out.println(bfs(bfs(1).v).distance);
    }

    private static Result bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        Map<Integer, Integer> distance = new HashMap<>();
        q.add(start);
        distance.put(start, 0);
        while (!q.isEmpty()) {
            int v = q.poll();
            for (Edge edge: G.get(v)) {
                if (!distance.containsKey(edge.w)) {
                    distance.put(edge.w, distance.get(v) + edge.distance);
                    q.add(edge.w);
                }
            }
        }
        int u = 0, d = 0;
        for (int v: distance.keySet()) {
            if (d < distance.get(v)) {
                u = v;
                d = distance.get(v);
            }
        }
        return new Result(u, d);
    }

    private static class Result {
        private int v, distance;

        public Result(int v, int distance) {
            this.v = v;
            this.distance = distance;
        }
    }

    private static class Edge {
        private final int v, w, distance;

        private Edge(int v, int w, int distance) {
            this.v = v;
            this.w = w;
            this.distance = distance;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return v == edge.v &&
                    w == edge.w;
        }

        @Override
        public int hashCode() {
            return Objects.hash(v, w);
        }
    }
}
