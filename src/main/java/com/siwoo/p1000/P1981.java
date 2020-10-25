package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Used(algorithm = Algorithm.BINARY_SEARCH)
public class P1981 {
    private static Point END;
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, ANSWER;
    private static int[][] BOARD;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        BOARD = new int[N][N];
        END = new Point(N-1, N-1);
        int min = 200, max = 0;
        for (int i=0; i<N; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            for (int j=0; j<N; j++) {
                int x = Integer.parseInt(token.nextToken());
                min = Math.min(x, min);
                max = Math.max(x, max);
                BOARD[i][j] = x;
            }
        }
        ANSWER = max - min;
        int diff = bs(0, max - min, BOARD);
        System.out.println(diff);
    }

    private static int bs(int minBoundary, int maxBoundary, int[][] BOARD) {
        if (minBoundary > maxBoundary) return ANSWER;
        int b = (minBoundary + maxBoundary) / 2;
        boolean ok = dfs(b, BOARD);
        if (ok) {
            ANSWER = Math.min(ANSWER, b);
            bs(minBoundary, b - 1, BOARD);
        } else {
            bs(b + 1, maxBoundary, BOARD);
        }
        return ANSWER;
    }

    private static boolean dfs(int B, int[][] BOARD) {  //state 을 나누는 dfs 은 시간초과남.
        Point p = new Point(0, 0);
        int v = BOARD[p.x][p.y];
        for (int i=0; i+B<=200; i++) {  //Boundary 을 이용해 min, max 을 결정.,
                                        // i 가 min 일때,  max = min + B
            int min = i, max = min+B;
            if (min > v || max < v) continue;
            if (dfs(p, min, max, new HashSet<>(), BOARD))
                return true;
        }
        return false;
    }

    private static boolean dfs(Point p, int min, int max, Set<Point> visit, int[][] BOARD) {
        if (p.equals(END)) return true;
        visit.add(p);
        for (Point m: Point.POINTS) {
            Point w = new Point(p.x + m.x, p.y + m.y);
            if (!w.valid() ||
                    visit.contains(w)) continue;
            int v = BOARD[w.x][w.y];
            if (min <= v && v <= max) {
                if (dfs(w, min, max, visit, BOARD))
                    return true;
            }
        }
        return false;
    }

    private static class Point {
        private static Point[] POINTS = {
                new Point(-1, 0), new Point(1, 0),
                new Point(0, -1), new Point(0, 1)
        };
        private final int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean valid() {
            return x >= 0 && x < N && y >= 0 && y < N;
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
    }
}
