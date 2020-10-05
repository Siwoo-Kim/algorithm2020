package com.siwoo.p16000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * BFS 순서 확인.
 *  1. 현재 방문한 v 와 인접 정점 w 을 pathTo[w]=v 을 저장.
 *  2. for (m..degreeOf(v)) -> pathTo[order[i]] 가 v 인지 확인.
 *  3. m += degreeOf(v)
 *
 */
public class P16940 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<Integer, List<Edge>> G = new HashMap<>();
    private static int N;
    private static int[] pathTo;
    private static int[] orders;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        for (int i=0; i<N-1; i++) {
            token = new StringTokenizer(reader.readLine());
            Edge edge = new Edge(Integer.parseInt(token.nextToken()),
                    Integer.parseInt(token.nextToken()));
            G.computeIfAbsent(edge.v, k -> new LinkedList<>());
            G.computeIfAbsent(edge.w, k -> new LinkedList<>());
            G.get(edge.v).add(edge);
            G.get(edge.w).add(edge.reverse());
        }
        orders = Arrays.stream(reader.readLine().split("\\s+"))
            .mapToInt(Integer::parseInt)
            .toArray();
        pathTo = new int[N+1];
        Set<Integer> visit = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        visit.add(1);
        boolean ok = true;
        int m = 1;
        for (int i=0; i<N; i++) {
            if (q.isEmpty()) {
                ok = false;
                break;
            }
            int v = q.poll();
            if (v != orders[i]) {
                ok = false;
                break;
            }
            int cnt = 0;
            for (Edge edge: G.get(v)) {
                if (!visit.contains(edge.w)) {
                    pathTo[edge.w] = v;
                    cnt++;
                }
            }
            for (int j=0; j<cnt; j++) {
                if (m + j >= N || pathTo[orders[m+j]] != v) {
                    ok = false;
                    break;
                }
                q.add(orders[m+j]);
                visit.add(orders[m+j]);
            }
            m += cnt;
        }
        if (ok)
            System.out.println(1);
        else
            System.out.println(0);
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
