package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Used(algorithm = Algorithm.BFS)
public class P1175 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static char[][] BOARD;
    private static int N, M;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        BOARD = new char[N][M];
        Point s = null, c1 = null, c2 = null;
        for (int i=0; i<N; i++) {
            String line = reader.readLine();
            for (int j=0; j<M; j++) {
                BOARD[i][j] = line.charAt(j);
                if (BOARD[i][j] == 'S')
                    s = new Point(i, j);
                else if (BOARD[i][j] == 'C') {
                    if (c1 == null)
                        c1 = new Point(i, j);
                    else
                        c2 = new Point(i, j);
                }
            }
        }
        
        Queue<State> q = new LinkedList<>();
        q.add(new State(s, null, null, null));
        Map<State, Integer> dist = new HashMap<>();
        dist.put(q.peek(), 0);
        while (!q.isEmpty()) {
            State now = q.poll();
            int distance = dist.get(now);
            if (now.v.equals(c1))
                now = new State(now.v, c1, now.c2, now.d);
            if (now.v.equals(c2))
                now = new State(now.v, now.c1, c2, now.d);
            if (now.c1 != null && now.c2 != null) {
                System.out.println(distance);
                return;
            }
            for (Direction d: Direction.values()) {
                if (now.d == d) continue;
                Point w = new Point(now.v.x + d.x, now.v.y + d.y);
                State next = new State(w, now.c1, now.c2, d);
                if (w.valid() && 
                        BOARD[w.x][w.y] != '#' &&
                        !dist.containsKey(next)) {
                    dist.put(next, distance + 1);
                    q.add(next);
                }
            }
        }
        System.out.println(-1);
    }
    
    enum Direction {
        LEFT(0, -1), RIGHT(0, 1), 
        UP(-1, 0), DOWN(1, 0);
        int x, y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    private static class State {
        final Point v, c1, c2;
        final Direction d;

        public State(Point v, Point c1, Point c2, Direction d) {
            this.v = v;
            this.c1 = c1;
            this.c2 = c2;
            this.d = d;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return Objects.equals(v, state.v) &&
                    Objects.equals(c1, state.c1) &&
                    Objects.equals(c2, state.c2) &&
                    d == state.d;
        }

        @Override
        public int hashCode() {
            return Objects.hash(v, c1, c2, d);
        }
    }
    
    private static class Point {
        final int x, y;

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
