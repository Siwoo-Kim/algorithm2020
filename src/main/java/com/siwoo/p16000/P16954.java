package com.siwoo.p16000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * BFS 정점 분리 문제
 *
 * s 초 뒤 벽의 위치를 계산.
 *
 * => wallOnSeconds(v, s) 가 s 시간 뒤 해당 위치에 벽이 있을지을 계산했다고 가졍 했을 때,
 * 다음 정점의 이동 여부는
 *  !wallOnSeconds(v, s) && !wallOnSeconds(v, s + 1)
 *     (이동 점정의 벽 여부)       (1 초뒤 정점의 벽 여부)
 *
 * => 8 초뒤에는 벽이 모두 없어지므로 8 초 이후는 무조건 성공.
 */
public class P16954 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final int N = 8;
    private static char[][] BOARD = new char[N][N];

    public static void main(String[] args) throws IOException {
        for (int i=0; i<N; i++)
            BOARD[i] = reader.readLine().toCharArray();
        State s = new State(0, new Point(N-1, 0));
        Queue<State> q = new LinkedList<>();
        q.add(s);
        while (!q.isEmpty()) {
            s = q.poll();
            if (s.seconds == 8) {
                System.out.println(1);
                return;
            }
            for (Point p: Point.POINTS) {
                Point w = new Point(s.p.x + p.x, s.p.y + p.y);
                if (w.valid() &&
                        !wallOnSeconds(w, s.seconds) &&
                        !wallOnSeconds(w, s.seconds + 1))
                    q.add(new State(s.seconds + 1, w));
            }
        }
        System.out.println(0);
    }

    private static boolean wallOnSeconds(Point p, int seconds) {
        int row = p.x - seconds;
        if (row >= 0)
            return BOARD[row][p.y] == '#';
        else
            return false;
    }

    private static class State {
        private final int seconds;
        private final Point p;

        public State(int seconds, Point p) {
            this.seconds = seconds;
            this.p = p;
        }

        @Override
        public String toString() {
            return "State{" +
                    "seconds=" + seconds +
                    ", p=" + p +
                    '}';
        }
    }

    private static class Point {
        private static Point[] POINTS = {
                new Point(0, 0),
                new Point(-1, 0), new Point(1, 0),
                new Point(0, -1), new Point(0, 1),
                new Point(-1, -1), new Point(-1, 1),
                new Point(1, -1), new Point(1, 1)
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
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
