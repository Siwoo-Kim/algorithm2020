package com.siwoo.p9000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Used(algorithm = Algorithm.DFS)
public class P9944 {
    private static Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static int N, M, ANSWER;
    private static char[][] BOARD;

    public static void main(String[] args) throws IOException {
        int CASE = 0;
        while (scanner.hasNext()) {
            N = scanner.nextInt();
            M = scanner.nextInt();
            BOARD = new char[N][M];
            ANSWER = -1;
            int cnt = 0;
            for (int i=0; i<N; i++) {
                String s = scanner.next();
                for (int j=0; j<M; j++) {
                    BOARD[i][j] = s.charAt(j);
                    if (BOARD[i][j] == '.')
                        cnt++;
                }
            }
            for (int i=0; i<N; i++)
                for (int j=0; j<M; j++)
                    if (BOARD[i][j] == '.')
                        go(cnt, new Point(i, j));
            System.out.printf("Case %d: %d%n", ++CASE, ANSWER);
        }
    }

    private static int go(int space, Point point) {
        return dfs(point, 0, space, new Stack<>());
    }

    private static int dfs(Point v, int cnt, int goal, Stack<Point> stack) {
        if (stack.size()+1 == goal) {
            if (ANSWER == -1 || ANSWER > cnt)
                ANSWER = cnt;
            return cnt;
        }
        int answer = -1;
        for (Point m: Point.POINTS) {
            Point w = new Point(v.x, v.y);
            boolean ok = false;
            while (w.valid()
                    && BOARD[w.x][w.y] == '.') {
                if (!w.equals(v)) ok = true;
                BOARD[w.x][w.y] = '#';
                stack.push(w);
                w = new Point(w.x + m.x, w.y + m.y);
            }
            if (ok) {
                w = stack.pop();
                BOARD[w.x][w.y] = '.';
                int x = dfs(w, cnt + 1, goal, stack);
                if (x != -1 && (answer == -1 || x < answer))
                    answer = x;
            }
            while (!v.equals(stack.peek())) {
                Point p = stack.pop();
                BOARD[p.x][p.y] = '.';
            }
            stack.pop();
            BOARD[v.x][v.y] = '.';
        }
        return answer;
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
