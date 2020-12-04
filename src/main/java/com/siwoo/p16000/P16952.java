package com.siwoo.p16000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Used(algorithm = Algorithm.BFS)
public class P16952 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] BOARD;
    private static int N;

    private static class Result {
        private int cnt, dist;

        public Result(int cnt, int dist) {
            this.cnt = cnt;
            this.dist = dist;
        }
    }
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        BOARD = new int[N][N];
        Point start = null;
        for (int i=0; i<N; i++) {
            BOARD[i] = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(k -> Integer.parseInt(k) - 1)
                    .toArray();
            for (int j=0; j<N; j++)
                if (BOARD[i][j] == 0)
                    start = new Point(i, j);
        }
        Unit[] units = {new Bishop(), new Knight(), new Rook()};
        Queue<State> q = new LinkedList<>();
        Map<State, Result> distance = new HashMap<>();
        for (Unit unit: units) {
            State state = new State(start, unit, 0, 1);
            q.add(state);
            distance.put(state, new Result(0, 0));
        }
        int minCnt = Integer.MAX_VALUE;
        int minDist = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            State s = q.poll();
            Result r = distance.get(s);
            if (BOARD[s.p.x][s.p.y] == s.to)
                s = new State(s.p, s.unit, s.to, s.to+1);
            if (s.from == N*N-1) {
                if (minDist >= r.cnt) {
                    if (minDist == r.dist)
                        minCnt = Math.min(r.cnt, minCnt);
                    else {
                        minDist = r.dist;
                        minCnt = r.cnt;
                    }
                }
            }
            for (Point p: s.moves()) {
                if (p.valid()) {
                    State s2 = new State(p, s.unit, s.from, s.to);
                    if (!distance.containsKey(s2) ||
                            (distance.get(s2).dist > r.dist + 1 && distance.get(s2).cnt > r.cnt)) {
                        distance.put(s2, new Result(r.cnt, r.dist + 1));
                        q.add(s2);
                    }
                }
            }
            for (Unit unit: units) {
                if (unit == s.unit) continue;
                State s2 = new State(s.p, unit, s.from, s.to);
                Result r2 = new Result(r.cnt + 1, r.dist + 1);
                if (!distance.containsKey(s2) || 
                        (distance.get(s2).dist > r2.dist && distance.get(s2).cnt > r2.cnt)) {
                    distance.put(s2, r2);
                    q.add(s2);
                }
            }
        }
        System.out.printf("%d %d%n", minDist, minCnt);
    }

    private static class State {
        private Point p;
        private Unit unit;
        private int from, to;

        public State(Point p, Unit unit, int from, int to) {
            this.p = p;
            this.unit = unit;
            this.from = from;
            this.to = to;
        }

        public List<Point> moves() {
            return unit.moves(this.p);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return from == state.from &&
                    to == state.to &&
                    Objects.equals(p, state.p) &&
                    Objects.equals(unit, state.unit);
        }

        @Override
        public int hashCode() {
            return Objects.hash(p, unit, from, to);
        }

        @Override
        public String toString() {
            return "State{" +
                    "p=" + p +
                    ", unit=" + unit +
                    ", from=" + from +
                    ", to=" + to +
                    '}';
        }
    }

    private static class Point {
        final int x, y;

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
            return "{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    private interface Unit {
        List<Point> moves(Point p);
    }

    private static class Bishop implements Unit {
        private Map<Point, List<Point>> DP = new HashMap<>();
        private static final int[][] D = {
                {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
        };

        @Override
        public List<Point> moves(Point p) {
            if (DP.containsKey(p)) return DP.get(p);
            List<Point> moves = new ArrayList<>();
            for (int[] d: D) {
                int dx = p.x, dy = p.y;
                while (true) {
                    dx += d[0];
                    dy += d[1];
                    Point dp = new Point(dx, dy);
                    if (dp.valid())
                        moves.add(dp);
                    else
                        break;
                }
            }
            DP.put(p, moves);
            return moves;
        }
    }
    private static class Knight implements Unit {
        private static final int[][] D = {
                {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2},
                {1, -2}, {2, -1}, {2, 1}, {1, 2}
        };

        @Override
        public List<Point> moves(Point p) {
            List<Point> moves = new ArrayList<>();
            for (int[] d: D) {
                Point dp = new Point(p.x + d[0], p.y + d[1]);
                if (dp.valid())
                    moves.add(dp);
            }
            return moves;
        }
    }

    private static class Rook implements Unit {
        private Map<Point, List<Point>> DP = new HashMap<>();

        @Override
        public List<Point> moves(Point p) {
            if (DP.containsKey(p)) return DP.get(p);
            List<Point> moves = new ArrayList<>();
            for (int i=0; i<N; i++) {
                if (i != p.x)
                    moves.add(new Point(i, p.y));
                if (i != p.y)
                    moves.add(new Point(p.x, i));
            }
            DP.put(p, moves);
            return moves;
        }
    }
}
