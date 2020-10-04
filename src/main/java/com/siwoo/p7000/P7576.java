package com.siwoo.p7000;


import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 토마토.. 메모리, 시간 초과 빡빡하다....
 * O(E), E = 4(NM), 2 ≤ M,N ≤ 1,000
 * = 4,000,000
 */
@Used(algorithm = Algorithm.BFS)
public class P7576 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] BOARD;
    private static int N, M;

    private static int[][] D = {
            {1, 0}, {-1, 0}, {0, -1}, {0, 1}
    };

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        M = Integer.parseInt(token.nextToken());
        N = Integer.parseInt(token.nextToken());
        BOARD = new int[N][M];
        Queue<Integer> q = new LinkedList<>();
        Map<Integer, Integer> distance = new HashMap<>();
        int cnt = 0;
        for (int i=0; i<N; i++) {
            token = new StringTokenizer(reader.readLine());
            for (int j=0; j<M; j++) {
                BOARD[i][j] = Integer.parseInt(token.nextToken());
                if (BOARD[i][j] == 1) {
                    q.add(i * M + j);
                    distance.put(i * M + j, 0);
                }
                if (BOARD[i][j] != -1) {
                    cnt++;
                }
            }
        }
        int mature = q.size(), answer = 0;
        if (mature == cnt) {
            System.out.println(0);
            return;
        }
        while (!q.isEmpty()) {
            int v = q.poll();
            int x = v / M;
            int y = v % M;
            for (int[] d: D) {
                int dx =  x + d[0],
                        dy = y + d[1];
                if (dx < 0 || dx >= N || dy < 0 || dy >= M || BOARD[dx][dy] == -1) continue;
                if (distance.containsKey(dx * M + dy)) continue;
                mature++;
                distance.put(dx * M + dy, distance.get(v) + 1);
                q.add(dx * M + dy);
                answer = Math.max(distance.get(dx * M + dy), answer);
            }
        }
        if (mature != cnt)
            System.out.println(-1);
        else
            System.out.println(answer);
    }
}
