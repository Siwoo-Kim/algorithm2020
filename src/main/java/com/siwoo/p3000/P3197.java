package com.siwoo.p3000;

import com.siwoo.util.Marker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Marker
public class P3197 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int H, W;
    private static char[][] BOARD;
    private static int[][] D = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        H = Integer.parseInt(token.nextToken());
        W = Integer.parseInt(token.nextToken());
        BOARD = new char[H][W];
        Point s1 = null, s2 = null;
        for (int i=0; i<H; i++) {
            String line = reader.readLine();
            for (int j=0; j<W; j++) {
                BOARD[i][j] = line.charAt(j);
                if (BOARD[i][j] == 'L') {
                    if (s1 == null)
                        s1 = new Point(i, j);
                    else
                        s2 = new Point(i, j);
                }
                if (BOARD[i][j] == '.')
                    water.add(new Point(i, j));
            }
        }
        water.add(s1);
        water.add(s2);
        swans.add(s1);
        Queue<Point> q = new LinkedList<>();
        q.addAll(water);
        Queue<Point> sq = new LinkedList<>();
        sq.addAll(swans);
        int day = 0;
        while (true) {
            sq = dfs(sq);
            if (swans.contains(s1) && swans.contains(s2)) {
                System.out.println(day);
                return;
            }
            q = swamp(q);
            day++;
        }
    }

    private static Set<Point> swans = new HashSet<>();
    
    private static Queue<Point> dfs(Queue<Point> q) {
        Queue<Point> nq = new LinkedList<>();
        while (!q.isEmpty()) {
            Point v = q.poll();
            nq.add(v);
            for (int[] d: D) {
                Point w = new Point(v.x + d[0], v.y + d[1]);
                if (w.valid()
                        && !swans.contains(w)
                        && BOARD[w.x][w.y] != 'X') {
                    swans.add(w);
                    nq.add(w);
                    q.add(w);
                }
            }
        }
        return nq;
    }

    private static Set<Point> water = new HashSet<>();

    private static Queue<Point> swamp(Queue<Point> q) {
        Queue<Point> nq = new LinkedList<>();
        while (!q.isEmpty()) {
            Point v = q.poll();
            nq.add(v);
            for (int[] d: D) {
                Point w = new Point(v.x + d[0], v.y + d[1]);
                if (w.valid()
                        && !water.contains(w)
                        && BOARD[w.x][w.y] == 'X') {
                    BOARD[w.x][w.y] = '.';
                    water.add(w);
                    nq.add(w);
                }
            }
        }
        return nq;
    }

    private static class Point {
        final int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean valid() {
            return x >= 0 && x < H && y >= 0 && y < W;
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
