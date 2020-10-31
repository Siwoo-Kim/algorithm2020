package com.siwoo.p15000;

import org.omg.PortableServer.POA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P15686 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static int[][] BOARD;
    private static List<Point> chickens = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        BOARD = new int[N][N];
        for (int i=0; i<N; i++) {
            token = new StringTokenizer(reader.readLine());
            for (int j=0; j<N; j++) {
                BOARD[i][j] = Integer.parseInt(token.nextToken());
                if (BOARD[i][j] == 2)
                    chickens.add(new Point(i, j));
            }
        }
        int min = select(0, new Stack<>());
        System.out.println(min);
    }

    private static int select(int index, Stack<Point> stack) {
        if (stack.size() == M) {
            if (stack.isEmpty()) return Integer.MAX_VALUE;
            else return bfs(stack);
        }
        if (index == chickens.size()) return Integer.MAX_VALUE;
        stack.push(chickens.get(index));
        int x = select(index + 1, stack);
        stack.pop();
        return Math.min(x, select(index + 1, stack));
    }

    private static int bfs(Stack<Point> stack) {
        Map<Point, Point> distance = new HashMap<>();
        Queue<Point> q = new LinkedList();
        for (Point p: stack) {
            q.add(p);
            distance.put(p, p);
        }
        while (!q.isEmpty()) {
            Point p = q.poll();
            for (Point m: Point.MOVE) {
                Point w = new Point(p.x + m.x, p.y + m.y);
                if (!w.valid()) continue;
                if (distance.containsKey(w)) continue;
                distance.put(w, p);
                q.add(w);
            }
        }
        int d = 0;
        for (Point p: distance.keySet()) {
            if (BOARD[p.x][p.y] == 1) {
                Point p2 = getParent(distance, p);
                d += Math.abs(p.x - p2.x) + Math.abs(p.y - p2.y);
            }
        }
        return d;
    }

    private static Point getParent(Map<Point, Point> distance, Point p) {
        while (distance.get(p) != p)
            p = distance.get(p);
        return p;
    }

    private static class Point {
        private static final Point[] MOVE = {
                new Point(-1, 0), new Point(1, 0),
                new Point(0, -1), new Point(0, 1)
        };

        private final int x, y;
        private Point from;

        public Point(int x, int y) {
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

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", from=" + from +
                    '}';
        }
    }
}
