package com.siwoo.p2000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 그래프 순회시의 "정점 분리하기" 문제.
 * => 주어진 메모리 제한 팍팍
 *  N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 1,000)
 *  O(2NM)
 */
@Used(algorithm = Algorithm.BFS)
public class P2206 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static int[][] BOARD;
    private static int[][][] check;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        BOARD = new int[N][M];
        check = new int[N][M][2];

        for (int i=0; i<N; i++)
            BOARD[i] = Arrays.stream(reader.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        State state = new State(new Point(0, 0));   //split vertex
        Queue<State> q = new LinkedList<>();
        q.add(state);
        check[0][0][0] = 1;
        Point end = new Point(N-1, M-1);

        while (!q.isEmpty()) {
            state = q.poll();
            Point v = state.point;
            if (end.equals(v)) {    //found
                System.out.println(check[N-1][M-1][state.index()]);
                return;
            }
            for (Point p: Point.POINTS) {
                Point w = new Point(v.x + p.x, v.y + p.y);
                if (!w.valid()) continue;
                State newState = null;
                if (BOARD[w.x][w.y] == 0)
                    newState = new State(w, state.used);
                else if (!state.used)   //allow to crash the wall once
                    newState = new State(w, true);
                if (newState != null &&
                        check[w.x][w.y][newState.index()] == 0) {
                    check[w.x][w.y][newState.index()] = check[v.x][v.y][state.index()] + 1;
                    q.add(newState);
                }
            }
        }
        System.out.println(-1);
    }

    private static class State {
        private Point point;
        private boolean used;

        public State(Point point, boolean used) {
            this.point = point;
            this.used = used;
        }

        public State(Point point) {
            this(point, false);
        }

        public int index() {
            return used? 1: 0;
        }
    }

    private static class Point {
        private static Point[] POINTS = {
                new Point(-1, 0), new Point(1, 0),
                new Point(0, -1), new Point(0, 1)
        };

        private int x, y;

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
