package com.siwoo.p16000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 정점 나누기
 * O(NM*2(K+2))
 */
@Used(algorithm = Algorithm.BFS)
public class P16933 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] BOARD;
    private static int[][][][] DISTANCE;
    private static int N, M, K;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());
        DISTANCE = new int[N][M][K+1][2];
        BOARD = new int[N][M];
        for (int i=0; i<N; i++) {
            String line = reader.readLine();
            for (int j=0; j<M; j++)
                BOARD[i][j] = line.charAt(j) - '0';
        }
        State state = new State(false, K, new Point(0, 0));
        Queue<State> q = new LinkedList<>();
        q.add(state);
        DISTANCE[state.point.x][state.point.y][state.latch][state.day()] = 1;
        Point end = new Point(N-1, M-1);
        while (!q.isEmpty()) {
            state = q.poll();
            Point v = state.point;
            if (end.x == v.x && end.y == v.y) {
                System.out.println(DISTANCE[v.x][v.y][state.latch][state.day()]);
                return;
            }
            for (Point m: Point.POINTS) {
                Point w = new Point(v.x + m.x, v.y + m.y);
                if (!w.valid()) continue;
                State newState = null;
                if (BOARD[w.x][w.y] == 0)   //no constraint
                    newState = new State(!state.night, state.latch, w);
                else if (state.latch != 0 && !state.night)
                    newState = new State(!state.night, state.latch-1, w);
                if (newState != null
                        && check(newState)) {
                    DISTANCE[w.x][w.y][newState.latch][newState.day()] =
                            DISTANCE[v.x][v.y][state.latch][state.day()] + 1;
                    q.add(newState);
                }
            }
            State newState = new State(!state.night, state.latch, v);
            if (check(newState)) {
                DISTANCE[v.x][v.y][newState.latch][newState.day()] =
                        DISTANCE[v.x][v.y][state.latch][state.day()] + 1;
                q.add(newState);
            }
        }
        System.out.println(-1);
    }

    private static boolean check(State s) {
        Point p = s.point;
        return DISTANCE[p.x][p.y][s.latch][s.day()] == 0;
    }

    private static class State {
        private final boolean night;
        private final int latch;
        private final Point point;

        public State(boolean night, int latch, Point point) {
            this.night = night;
            this.latch = latch;
            this.point = point;
        }

        public int day() {
            return night ? 1: 0;
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
