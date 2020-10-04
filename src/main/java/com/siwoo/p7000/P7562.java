package com.siwoo.p7000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * O(E) = 8(N*N),  N(4 ≤ N ≤ 300)
 * 360,000
 */
@Used(algorithm = Algorithm.BFS)
public class P7562 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N;
    private static Point start, end;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(reader.readLine());
        for (int t=0; t<T; t++) {
            N = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            start = new Point(Integer.parseInt(tokenizer.nextToken()),
                    Integer.parseInt(tokenizer.nextToken()));
            tokenizer = new StringTokenizer(reader.readLine());
            end = new Point(Integer.parseInt(tokenizer.nextToken()),
                    Integer.parseInt(tokenizer.nextToken()));

            Queue<Point> q = new LinkedList<>();
            q.add(start);
            Map<Point, Integer> distance = new HashMap<>();
            distance.put(start, 0);
            while (!q.isEmpty()) {
                Point v = q.poll();
                if (v.equals(end)) {
                    System.out.println(distance.get(v));
                    break;
                }
                for (Point m: Point.MOVE) {
                    Point w = new Point(v.x + m.x, v.y + m.y);
                    if (w.valid() && !distance.containsKey(w)) {
                        distance.put(w, distance.get(v) + 1);
                        q.add(w);
                    }
                }
            }
        }
    }

    private static class Point {
        private static Point[] MOVE = {
                new Point(-2, 1), new Point(-1, 2),
                new Point(2, 1), new Point(1, 2),
                new Point(2, -1), new Point(1, -2),
                new Point(-2, -1), new Point(-1, -2),
        };
        final int x, y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        boolean valid() {
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
