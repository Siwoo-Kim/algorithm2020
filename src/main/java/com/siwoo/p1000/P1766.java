package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Used(algorithm = Algorithm.TOPOLOGICAL_ORDER)
public class P1766 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static Map<Integer, List<Integer>> G = new HashMap<>();
    private static Map<Integer, Integer> DEGREE = new HashMap<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        for (int i=1; i<=N; i++) {
            G.put(i, new ArrayList<>());
            DEGREE.put(i, 0);
        }
        
        for (int i=0; i<M; i++) {
            token = new StringTokenizer(reader.readLine());
            int v = Integer.parseInt(token.nextToken()),
                w = Integer.parseInt(token.nextToken());
            G.get(v).add(w);
            DEGREE.put(w, DEGREE.get(w) + 1);
        }
        List<Integer> to = topologicalOrder(G);
        StringBuilder sb = new StringBuilder();
        for (int v: to)
            sb.append(v).append(" ");
        System.out.println(sb);
    }

    private static List<Integer> topologicalOrder(Map<Integer, List<Integer>> G) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        List<Integer> result = new ArrayList<>();
        for (int v: G.keySet())
            if (DEGREE.get(v) == 0)
                q.add(v);
        while (!q.isEmpty()) {
            int v = q.poll();
            result.add(v);
            for (int w: G.get(v)) {
                DEGREE.put(w, DEGREE.get(w) - 1);
                if (DEGREE.get(w) == 0)
                    q.add(w);
            }
        }
        return result;
    }
}
