package com.siwoo.p16000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 4^N*M, 이때 최고 최대 횟수는 10이므로
 * 4^10 = 1,048,576 = 경우의 수.
 *
 */
@Used(algorithm = Algorithm.BFS)
public class P16197 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static char[][] board;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        board = new char[N][M];
        List<Point> points = new ArrayList<>();
        for (int i=0; i<N; i++) {
            String line = reader.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == 'o')
                    points.add(new Point(i, j));
            }
        }
        Pair<Point> pair = new Pair<>(points.get(0), points.get(1));
        Queue<Pair<Point>> q = new LinkedList<>();
        q.add(pair);
        Map<Pair<Point>, Integer> distance = new HashMap<>();
        distance.put(pair, 0);
        while (!q.isEmpty()) {
            Pair<Point> p = q.poll();
            Point a = p.a,
                    b = p.b;
            for (Point m: Point.POINTS) {
                Point am = a.plus(m),
                        bm = b.plus(m);
                if (am.drop() && bm.drop()) continue;   //base case
                if (am.drop() || bm.drop()) {   //found
                    System.out.println(distance.get(p) + 1);
                    return;
                }
                if (board[am.x][am.y] != '.')
                    am = a;
                if (board[bm.x][bm.y] != '.')
                    bm = b;
                Pair<Point> wp = new Pair<>(am, bm);
                if (!distance.containsKey(wp)   // one visit per edge
                        && 10 > distance.get(p) + 1) {  //max try
                    distance.put(wp, distance.get(p) + 1);
                    q.add(wp);
                }
            }
        }
        System.out.println(-1);
    }

    private static class Pair<E> {
        private E a, b;
        private Set<E> hash = new HashSet<>();

        public Pair(E a, E b) {
            this.a = a;
            this.b = b;
            hash.add(a);
            hash.add(b);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair<?> pair = (Pair<?>) o;
            return Objects.equals(hash, pair.hash);
        }

        @Override
        public int hashCode() {
            return Objects.hash(hash);
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

        public Point plus(Point p) {
            return new Point(x + p.x, y + p.y);
        }

        public boolean drop() {
            return !(x >= 0 && x < N && y >= 0 && y < M);
        }
    }
}
