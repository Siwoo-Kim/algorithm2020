package com.siwoo.p16000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

@Used(algorithm = Algorithm.BRUTE_FORCE)
public class P16924 {
    private static final Scanner scanner = new Scanner(
            new BufferedReader(new InputStreamReader(System.in)));
    private static int N, M;
    private static char[][] boards;
    private static int cnt = 0;
    private static final StringBuilder answer = new StringBuilder();
    private static final Set<Point> visit = new HashSet<>();

    public static void main(String[] args) {
        N = scanner.nextInt();
        M = scanner.nextInt();
        scanner.nextLine();
        boards = new char[N][M];
        for (int i = 0; i < N; i++) {
            boards[i] = scanner.nextLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (boards[i][j] == '*')
                    visit.add(new Point(i, j));
            }
        }
        bruteForce(new Point(0, 0));
        if (!visit.isEmpty())
            System.out.println(-1);
        else {
            System.out.println(cnt);
            System.out.println(answer.toString());
        }
    }

    private static void bruteForce(Point point) {
        if (point.index() == N * M)
            return;
        if (point.value() != '*')
            bruteForce(point.next());
        else {
            int size = 0;
            Point[] points = new Point[4];
            Arrays.fill(points, point);
            while (true) {
                boolean ok = true;
                for (int i = 0; i < Point.MV.length; i++) {
                    points[i] = points[i].plus(Point.MV[i]);
                    if (!points[i].valid()
                            || points[i].value() != '*') {
                        ok = false;
                        break;
                    }
                }
                if (!ok)
                    break;
                else {
                    for (Point p : points)
                        visit.remove(p);
                }
                size++;
            }
            if (size > 0) {
                visit.remove(point);
                cnt++;
                answer.append(point.x + 1)
                        .append(" ")
                        .append(point.y + 1)
                        .append(" ")
                        .append(size)
                        .append("\n");
            }
            bruteForce(point.next());
        }
    }

    private static class Point {
        private static final Point[] MV = {
                new Point(0, -1), new Point(0, 1),
                new Point(-1, 0), new Point(1, 0)
        };

        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int index() {
            return x * M + y;
        }

        public Point plus(Point p) {
            return new Point(x + p.x, y + p.y);
        }

        public boolean valid() {
            return x >= 0 && x < N && y >= 0 && y < M;
        }

        public char value() {
            return boards[x][y];
        }

        public Point next() {
            return y == M - 1 ? new Point(x + 1, 0) : new Point(x, y + 1);
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