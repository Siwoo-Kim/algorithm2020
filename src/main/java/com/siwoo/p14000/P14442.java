package com.siwoo.p14000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 정점 분리 bfs 로 문제 해결 가능.
 * HashMap visit 으로는 문제를 풀 수 없음.
 *  get 이 O(1) 아니란 증거 (bucket sizing ? ) 아니면 hashcode 문제
 *
 *  N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 1,000), K(1 ≤ K ≤ 10)
 *  O(NM(K+1)) = 11,000,000
 */
@Used(algorithm = Algorithm.BFS)
public class P14442 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, K;
    private static int[][] BOARD;
    private static int[][][] VISIT;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());
        BOARD = new int[N][M];
        VISIT = new int[N][M][K+1];
        for (int i=0; i<N; i++)
            BOARD[i] = Arrays.stream(reader.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        State state = new State(new Point(0, 0), K);
        Point end = new Point(N-1, M-1);
        Queue<State> q = new LinkedList<>();
        q.add(state);
        VISIT[state.point.x][state.point.y][state.latch] = 1;
        while (!q.isEmpty()) {
            state = q.poll();
            Point v = state.point;
            if (v.equals(end)) {
                System.out.println(VISIT[v.x][v.y][state.latch]);
                return;
            }
            for (Point m: Point.POINTS) {
                Point w = new Point(v.x + m.x, v.y + m.y);
                if (!w.valid()) continue;
                State newState = null;
                if (BOARD[w.x][w.y] == 0)
                    newState = new State(w, state.latch);
                else if (state.latch != 0)
                    newState = new State(w, state.latch-1);
                if (newState != null
                        && VISIT[w.x][w.y][newState.latch] == 0) {
                    VISIT[w.x][w.y][newState.latch] = VISIT[v.x][v.y][state.latch] + 1;
                    q.add(newState);
                }
            }
        }
        System.out.println(-1);
    }

    private static class State {
        private Point point;
        private int latch;

        public State(Point point, int latch) {
            this.point = point;
            this.latch = latch;
        }
    }

    private static class Point {
        private static Point[] POINTS = {
                new Point(-1, 0), new Point(1, 0),
                new Point(0, -1), new Point(0, 1),
        };
        private final int x, y;

        private Point(int x, int y) {
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
