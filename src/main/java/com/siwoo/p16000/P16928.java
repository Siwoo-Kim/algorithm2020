package com.siwoo.p16000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * O(V+E), E = 100 + 6*100
 */
@Used(algorithm = Algorithm.BFS)
public class P16928 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, MAX = 100;
    private static int[] pathTo = new int[MAX+1];

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        for (int i=0; i<N+M; i++) {
            token = new StringTokenizer(reader.readLine());
            int v = Integer.parseInt(token.nextToken()),
                    w = Integer.parseInt(token.nextToken());
            pathTo[v] = w;
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        Map<Integer, Integer> distance = new HashMap<>();
        distance.put(1, 0);
        while (!q.isEmpty()) {
            int v = q.poll();
            if (v == MAX) {
                System.out.println(distance.get(v));
                return;
            }
            for (int dice=1; dice<=6; dice++) {
                int w = v + dice;
                if (w > MAX) continue;
                if (pathTo[w] != 0)
                    w = pathTo[w];
                if (!distance.containsKey(w)) {
                    distance.put(w, distance.get(v) + 1);
                    q.add(w);
                }
            }
        }
    }
}
