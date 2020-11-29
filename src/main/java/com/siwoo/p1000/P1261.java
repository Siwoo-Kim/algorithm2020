package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Used(algorithm = Algorithm.DIJKSTRA)
public class P1261 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static Map<Integer, List<Edge>> G = new HashMap<>();
    private static int[][] D = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        M = Integer.parseInt(token.nextToken());
        N = Integer.parseInt(token.nextToken());
        for (int i=0; i<N; i++) {
            String line = reader.readLine();
            for (int j=0; j<M; j++) {
                int vid = i*M+j;
                G.putIfAbsent(vid, new ArrayList<>());
                for (int[] d: D) {
                    int dx = i + d[0],
                            dy = j + d[1];
                    if (valid(dx, dy)) {
                        int wid = dx*M+dy;
                        G.putIfAbsent(wid, new ArrayList<>());
                        G.get(wid).add(new Edge(wid, vid, line.charAt(j)-'0'));
                    }
                }
                
            }   
        }
        Dijkstra dijkstra = new Dijkstra(G, 0);
        System.out.println(dijkstra.distTo[N*M-1]);
    }

    private static class Dijkstra {
        private boolean[] visit;
        private int[] distTo;
        private Queue<Pair> q;

        private static class Pair {
            int v, weight;

            public Pair(int v, int weight) {
                this.v = v;
                this.weight = weight;
            }
        }

        public Dijkstra(Map<Integer, List<Edge>> G, int s) {
            visit = new boolean[N*M];
            distTo = new int[N*M];
            for (int i=0; i<N*M; i++)
                distTo[i] = N*M+1;
            distTo[s] = 0;
            q = new PriorityQueue<>(Comparator.comparingInt(v -> v.weight));
            q.add(new Pair(s, distTo[s]));
            while (!q.isEmpty()) {
                Pair p = q.poll();
                if (visit[p.v]) continue;
                relax(p.v);
            }
        }

        private void relax(int v) {
            visit[v] = true;
            for (Edge e: G.get(v)) {
                if (distTo[e.to] > distTo[e.from] + e.weight) {
                    distTo[e.to] = distTo[e.from] + e.weight;
                    q.add(new Pair(e.to, distTo[e.to]));
                }
            }
        }
    }
    
    private static boolean valid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    private static class Edge {
        final int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}
