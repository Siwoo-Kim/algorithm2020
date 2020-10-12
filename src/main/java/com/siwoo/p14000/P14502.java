package com.siwoo.p14000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *  (3 ≤ N, M ≤ 8)
 *  O(N*MC3 * DFS(E)) -> 41664 * 64 = 2,666,496
 */
@Used(algorithm = Algorithm.DFS)
public class P14502 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, BLANK_SPACE;
    private static int[][] BOARD;
    private static Set<Point> viruses = new HashSet<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        BOARD = new int[N][M];
        int blankSpace = 0;
        for (int i=0; i<N; i++) {
            String[] data = reader.readLine().split("\\s+");
            for (int j=0; j<M; j++) {
                int e = Integer.parseInt(data[j]);
                BOARD[i][j] = e;
                if (e == 0) blankSpace++;
                if (e == 2) viruses.add(new Point(i, j));
            }
        }
        BLANK_SPACE = blankSpace;
        int answer = select(0, new Stack<>());
        System.out.println(answer);
    }

    private static int select(int index, Stack<Integer> stack) {
        if (stack.size() == 3 || index == N * M) {
            if (stack.size() != 3) return 0;
            stack.stream().map(Point::new).forEach(p -> BOARD[p.x][p.y] = 1);
            Set<Point> visit = new HashSet<>();
            for (Point v: viruses) {
                if (!visit.contains(v))
                     dfs(v, visit);
            }
            stack.stream().map(Point::new).forEach(p -> BOARD[p.x][p.y] = 0);
            return (BLANK_SPACE - stack.size()) - (visit.size() - viruses.size());
            //Safe Area = (BLANK SPACE - 3 (walls)) - (VIRUS AREA)
        }
        Point p = new Point(index);
        int max = 0;
        if (BOARD[p.x][p.y] == 0) {
            stack.push(index);
            max = Math.max(select(index + 1, stack), max);
            stack.pop();
        }
        if (max == BLANK_SPACE) //BACK-TRACKING
            return max;
        else
            return Math.max(select(index + 1, stack), max);
    }

    private static int dfs(Point v, Set<Point> visit) {
        visit.add(v);
        int sum = 0;
        for (Point p: Point.POINTS) {
            Point w = new Point(v.x + p.x, v.y + p.y);
            if (w.valid()
                    && !visit.contains(w)
                    && BOARD[w.x][w.y] == 0) {
                sum += dfs(w, visit) + 1;
            }
        }
        return sum;
    }

    private static class Point {
        private static Point[] POINTS = {
            new Point(-1, 0), new Point(1, 0),
            new Point(0, -1), new Point(0, 1)
        };

        private final int x, y;

        private Point(int index) {
            this(index / M, index % M);
        }

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
