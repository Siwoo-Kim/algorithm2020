package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * BFS - 정점 분리
 */
@Used(algorithm = Algorithm.BFS)
public class P1600 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int K, N, M;
    private static int[][] BOARD;

    public static void main(String[] args) throws IOException {
        K = Integer.parseInt(reader.readLine());
        StringTokenizer token = new StringTokenizer(reader.readLine());
        M = Integer.parseInt(token.nextToken());
        N = Integer.parseInt(token.nextToken());
        BOARD = new int[N][M];
        for (int i=0; i<N; i++)
            BOARD[i] = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        Monkey monkey = new Monkey(new Point(0, 0), K);
        Queue<Monkey> q = new LinkedList<>();
        q.add(monkey);
        Map<Monkey, Integer> distance = new HashMap<>();
        distance.put(monkey, 0);
        while (!q.isEmpty()) {
            Monkey m = q.poll();
            if (m.p.x == N-1 && m.p.y == M-1) {
                System.out.println(distance.get(m));
                return;
            }
            int end = m.k == 0? 4: 12;
            for (int i=0; i<end; i++) {
                Point d = Monkey.MOVE[i];
                Point w = new Point(m.p.x + d.x, m.p.y + d.y);
                if (!w.valid()) continue;
                if (BOARD[w.x][w.y] == 1) continue;
                Monkey m2 = new Monkey(w, i < 4? m.k: m.k-1);
                if (!distance.containsKey(m2)) {
                    distance.put(m2, distance.get(m) + 1);
                    q.add(m2);
                }
            }
        }
        System.out.println(-1);
    }
    
    private static class Monkey {
        private static Point[] MOVE = {
                new Point(-1, 0), new Point(1, 0),
                new Point(0, -1), new Point(0, 1),
                new Point(-2, -1), new Point(-1, -2),
                new Point(-2, 1), new Point(-1, 2),
                new Point(2, -1), new Point(1, -2),
                new Point(2, 1), new Point(1, 2)
        };
        
        private Point p;
        private int k;

        public Monkey(Point p, int k) {
            this.p = p;
            this.k = k;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Monkey monkey = (Monkey) o;
            return k == monkey.k &&
                    Objects.equals(p, monkey.p);
        }

        @Override
        public int hashCode() {
            return Objects.hash(p, k);
        }

        @Override
        public String toString() {
            return "Monkey{" +
                    "p=" + p +
                    ", k=" + k +
                    '}';
        }
    }
    
    private static class Point {
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

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
    
}
