package com.siwoo.p3000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * wallsOnSeconds(seconds, x, y) 가 seconds 이후에 좌표에 (x, y) 물이 차오르는지 확인한다 가정한다면,
 * bfs 로 비버의 최단 경로를 알 수 있다.
 *
 * O(N*M)
 */
public class P3055 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static char[][] BOARD;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        BOARD = new char[N][M];
        Point start = null, end = null;
        Queue<Point> waters = new LinkedList<>();
        for (int i=0; i<N; i++) {
            BOARD[i] = reader.readLine().toCharArray();
            for (int j=0; j<M; j++) {
                if (BOARD[i][j] == '*')
                    waters.add(new Point(i, j));
                if (BOARD[i][j] == 'D')
                    end = new Point(i, j);
                if (BOARD[i][j] == 'S')
                    start = new Point(i, j);
            }
        }
        Map<Point, Integer> waterOnSeconds = new HashMap<>();
        for (Point water: waters)
            waterOnSeconds.put(water, 0);
        while (!waters.isEmpty()) {
            Point p = waters.poll();
            for (Point m: Point.POINTS) {
                Point w = new Point(p.x + m.x, p.y + m.y);
                if (w.valid()
                        && BOARD[w.x][w.y] != 'D'
                        && BOARD[w.x][w.y] != 'X'
                        && !waterOnSeconds.containsKey(w)) {
                    waterOnSeconds.put(w, waterOnSeconds.get(p) + 1);
                    waters.add(w);
                }
            }
        }
        Queue<State> q = new LinkedList<>();
        q.add(new State(0, start));
        Map<State, Integer> distance = new HashMap<>();
        distance.put(q.peek(), 0);
        while (!q.isEmpty()) {
            State s = q.poll();
            if (end.equals(s.p)) {
                System.out.println(distance.get(s));
                return;
            }
            for (Point m: Point.POINTS) {
                Point w = new Point(s.p.x + m.x, s.p.y + m.y);
                if (!w.valid()) continue;
                if (BOARD[w.x][w.y] == 'X') continue;
                if (waterOnSeconds(s.seconds, w, waterOnSeconds)) continue;
                if (waterOnSeconds(s.seconds + 1, w, waterOnSeconds)) continue;
                State newState = new State(s.seconds+1, w);
                if (!distance.containsKey(newState)) {
                    distance.put(newState, distance.get(s) + 1);
                    q.add(newState);
                }
            }
        }
        System.out.println("KAKTUS");
    }

    private static boolean waterOnSeconds(int seconds, Point p, Map<Point, Integer> calc) {
        if (!calc.containsKey(p)) return false;
        return calc.get(p) <= seconds;
    }

    private static class State {
        private final int seconds;
        private final Point p;

        public State(int seconds, Point p) {
            this.seconds = seconds;
            this.p = p;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return seconds == state.seconds &&
                    Objects.equals(p, state.p);
        }

        @Override
        public int hashCode() {
            return Objects.hash(seconds, p);
        }
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
            return x >= 0 && x < N && y >= 0 && y < M;
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
