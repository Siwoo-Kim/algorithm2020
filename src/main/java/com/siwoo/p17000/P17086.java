package com.siwoo.p17000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 전형적 bfs 문
 */
@Used(algorithm = Algorithm.BFS)
public class P17086 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static int[][] BOARD;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        BOARD = new int[N][M];
        for (int i=0; i<N; i++)
            BOARD[i] = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        int d = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (BOARD[i][j] == 0) {
                    d = Integer.max(bfs(i, j), d);
                }
            }
        }
        System.out.println(d);
    }

    private static int bfs(int i, int j) {
        class Point {
            private final int x, y;

            Point(int x, int y) {
                this.x = x;
                this.y = y;
            }

            public boolean valid() {
                return x >= 0 && x < N && y >= 0 && y < M;
            }
        }
        final Point[] D = {
                new Point(-1, 0), new Point(1, 0),
                new Point(0, -1), new Point(0, 1),
                new Point(-1, -1), new Point(-1, 1),
                new Point(1, -1), new Point(1, 1)
        };
        Point p = new Point(i, j);
        Queue<Point> q = new LinkedList<>();
        q.add(p);
        Map<Integer, Integer> map = new HashMap<>();
        map.put(p.x * M + p.y, 0);
        while (!q.isEmpty()) {
            Point v = q.poll();
            int vid = v.x * M + v.y;
            if (BOARD[v.x][v.y] == 1)   //found
                return map.get(vid);
            for (Point d: D) {
                Point w = new Point(v.x + d.x, v.y + d.y);
                int wid = w.x * M + w.y;
                if (!w.valid()) continue;
                if (map.containsKey(wid)) continue;
                map.put(wid, map.get(vid) + 1);
                q.add(w);
            }
        }
        throw new AssertionError("no sharks");
    }
}































