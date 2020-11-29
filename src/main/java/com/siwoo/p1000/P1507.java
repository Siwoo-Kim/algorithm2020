package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

@Used(algorithm = Algorithm.FLOYD_MARSHALL)
public class P1507 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int V;
    private static int dist[][];
    private static boolean edge[][];

    public static void main(String[] args) throws IOException {
        V = Integer.parseInt(reader.readLine());
        dist = new int[V+1][V+1];
        edge = new boolean[V+1][V+1];
        for (int v=1; v<=V; v++) {
            int[] d = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            System.arraycopy(d, 0, dist[v], 1, V);
            Arrays.fill(edge[v], true);
        }
        
        for (int v=1; v<=V; v++) {
            for (int w=1; w<=V; w++) {
                if (v == w) continue;
                for (int k=1; k<=V; k++) {
                    if (k == v) continue;
                    if (k == w) continue;
                    // v-w is not the shortest path
                    if (dist[v][w] > dist[v][k] + dist[k][w]) {
                        System.out.println(-1);
                        return;
                    }
                    if (dist[v][w] == dist[v][k] + dist[k][w]) {
                        edge[v][w] = false; 
                    }
                }
            }
        }
        
        int answer = 0;
        for (int v=1; v<=V; v++)
            for (int w=1; w<=V; w++) {
                if (edge[v][w])
                    answer += dist[v][w];
            }
        System.out.println(answer / 2);
    }
}
