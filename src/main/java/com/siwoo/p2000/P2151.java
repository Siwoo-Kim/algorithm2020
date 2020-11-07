package com.siwoo.p2000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Used(algorithm = Algorithm.BFS)
public class P2151 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static char[][] BOARD;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        BOARD = new char[N][N];
        Point start = null, end = null;
        for (int i=0; i<N; i++) {
            String line = reader.readLine();
            for (int j=0; j<N; j++) {
                BOARD[i][j] = line.charAt(j);
                if (BOARD[i][j] == '#') {
                    Point p = new Point(i, j);
                    if (start == null)
                        start = p;
                    else
                        end = p;
                }
            }
        }
        Queue<Point> q = new LinkedList<>();
        q.add(start);
        Map<Point, Integer> dist = new HashMap<>();
        dist.put(start, 0);
        while (!q.isEmpty()) {
            Point v = q.poll();
            if (v.equals(end)) {
                System.out.println(dist.get(v));
                return;
            }
            for (Point d: Point.D) {
                Point w = new Point(v.x + d.x, v.y + d.y);
                while (w.valid() && 
                        BOARD[w.x][w.y] != '*') {
                    if ((BOARD[w.x][w.y] == '!' 
                            || w.equals(end))
                            && !dist.containsKey(w)) {  //only add mirror and end point
                        dist.put(w, dist.get(v) + (w.equals(end)? 0: 1));   //only mirror increase weight
                        q.add(w);
                    }
                    w = new Point(w.x + d.x, w.y + d.y);
                }
            }
        }
    }
    
    private static class Point {
        private static Point[] D = {
                new Point(-1, 0), new Point(1, 0),
                new Point(0, -1), new Point(0, 1)
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
                    '}';
        }
    }
}
