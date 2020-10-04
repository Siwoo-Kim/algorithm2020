package com.siwoo.p2000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 가중치가 1인 그래프에서의 최단거리 = BFS
 * = O(E), E = 4(N*M),  N, M(2 ≤ N, M ≤ 100)
 * = 40,000
 */
@Used(algorithm = Algorithm.BFS)
public class P2178 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Point[][] BOARD;
    private static int N, M;

    public static void main(String[] args) throws IOException {
        int[] data = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = data[0];
        M = data[1];
        BOARD = new Point[N][M];
        for (int i=0; i<N; i++) {
            data = Arrays.stream(reader.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int j=0; j<M; j++)
                BOARD[i][j] = new Point(i, j, data[j]);
        }

        Queue<Point> q = new LinkedList<>();
        Map<Point, Integer> distance = new HashMap<>();
        q.add(BOARD[0][0]);
        distance.put(BOARD[0][0], 1);
        while (!q.isEmpty()) {
            Point v = q.poll();
            for (Point d: Point.D) {
                Point w = v.move(d);
                if (w.valid() &&
                        !distance.containsKey(w) &&
                        BOARD[w.x][w.y].value == 1) {
                    distance.put(w, distance.get(v) + 1);
                    q.add(w);
                }
            }
        }
        System.out.println(distance.get(BOARD[N-1][M-1]));
    }

    private static class Point {
        private final int x, y, value;

        private static Point[] D = {
                new Point(1, 0, 0), new Point(-1, 0, 0),
                new Point(0, -1, 0), new Point(0, 1, 0)
        };

        private Point(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }

        public Point move(Point p) {
            return new Point(x + p.x, y + p.y, 0);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        public boolean valid() {
            return x >= 0 && x < N && y >= 0 && y < M;
        }
    }
}

