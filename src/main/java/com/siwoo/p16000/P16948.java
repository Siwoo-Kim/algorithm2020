package com.siwoo.p16000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.util.*;

@Used(algorithm = Algorithm.BFS)
public class P16948 {
    private static Scanner scanner = new Scanner(System.in);
    private static int N;
    private static Point START, END;

    public static void main(String[] args) {
        N = scanner.nextInt();
        START = new Point(scanner.nextInt(), scanner.nextInt());
        END = new Point(scanner.nextInt(), scanner.nextInt());
        Queue<Point> q = new LinkedList<>();
        q.add(START);
        Map<Point, Integer> distance = new HashMap<>();
        distance.put(START, 0);
        while (!q.isEmpty()) {
            Point p = q.poll();
            if (END.equals(p)) {
                System.out.println(distance.get(p));
                return;
            }
            for (Point m: Point.POINTS) {
                Point w = new Point(p.x + m.x, p.y + m.y);
                if (w.valid() && !distance.containsKey(w)) {
                    distance.put(w, distance.get(p) + 1);
                    q.add(w);
                }
            }
        }
        System.out.println(-1);
    }

    private static class Point {
        private static Point POINTS[] = {
                new Point(-2, -1), new Point(-2, 1),
                new Point(0, -2), new Point(0, 2),
                new Point(+2, -1), new Point(+2, 1)
        };

        private final int x, y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean valid() {
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
