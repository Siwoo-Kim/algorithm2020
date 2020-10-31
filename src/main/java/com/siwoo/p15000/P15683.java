package com.siwoo.p15000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class P15683 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, SIZE, ANSWER;
    private static int[][] BOARD;
    private static List<CCTV> cctvs = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        BOARD = new int[N][M];
        SIZE = N * M;
        for (int i=0; i<N; i++) {
            token = new StringTokenizer(reader.readLine());
            for (int j=0; j<M; j++) {
                BOARD[i][j] = Integer.parseInt(token.nextToken());
                if (BOARD[i][j] != 0 && BOARD[i][j] != 6) {
                    cctvs.add(CCTV.create(new Point(i, j), BOARD[i][j]));
                }
                if (BOARD[i][j] != 0)
                    SIZE--;
            }
        }
        ANSWER = SIZE;
        select(0, new Stack<>());
        System.out.println(ANSWER);
    }

    private static void select(int index, Stack<CCTV> stack) {
        if (index == cctvs.size()) {
            int cnt = traverse(stack);
            ANSWER = Math.min(ANSWER, SIZE - cnt);
            return;
        }
        CCTV cctv = cctvs.get(index);
        for (CCTV c: cctv.rotate()) {
            stack.push(c);
            select(index + 1, stack);
            stack.pop();
        }
    }

    private static int traverse(Stack<CCTV> stack) {
        Set<Point> visit = new HashSet<>(stack.stream().map(c -> c.p).collect(Collectors.toList()));
        for (CCTV cctv: stack) {
            for (Direction d: cctv.directions) {
                Point p = new Point(cctv.p.x + d.point.x, cctv.p.y + d.point.y);
                while (p.valid()) {
                    if (BOARD[p.x][p.y] == 6) break;
                    visit.add(p);
                    p = new Point(p.x + d.point.x, p.y + d.point.y);
                }
            }
        }
        for (CCTV cctv: stack)
            visit.remove(cctv.p);
        return visit.size();
    }

    private static class Point {
        private final int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean valid() {
            return x >= 0 && x < N && y >= 0 && y <M;
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

    private enum Direction {
        LEFT(new Point(0, -1)), RIGHT(new Point(0, 1)),
        UP(new Point(-1, 0)), DOWN(new Point(1, 0));

        static  {
            LEFT.rotate = UP;
            UP.rotate = RIGHT;
            RIGHT.rotate = DOWN;
            DOWN.rotate = LEFT;
        }

        private final Point point;
        private Direction rotate;

        Direction(Point point) {
            this.point = point;
        }

        Direction rotate() {
            return rotate;
        }
    }

    private static class CCTV {
        private List<Direction> directions;
        private int rotates;
        private final Point p;

        public CCTV(Point p, List<Direction> directions, int rotates) {
            this.p = p;
            this.directions = directions;
            this.rotates = rotates;
        }

        public CCTV[] rotate() {
            CCTV[] r = new CCTV[rotates];
            r[0] = this;
            List<Direction> directions = new ArrayList<>(this.directions);
            for (int i=1; i<rotates; i++) {
                List<Direction> newDirection = new ArrayList<>();
                for (Direction d: directions)
                    newDirection.add(d.rotate());
                r[i] = new CCTV(p, newDirection, rotates);
                directions = newDirection;
            }
            return r;
        }

        public static CCTV create(Point p, int x) {
            if (x == 1)
                return new CCTV(p, Arrays.asList(Direction.LEFT), 4);
            if (x == 2)
                return new CCTV(p, Arrays.asList(Direction.LEFT, Direction.RIGHT), 2);
            if (x == 3)
                return new CCTV(p, Arrays.asList(Direction.UP, Direction.RIGHT), 4);
            if (x == 4)
                return new CCTV(p, Arrays.asList(Direction.LEFT, Direction.UP, Direction.RIGHT), 4);
            return new CCTV(p, Arrays.asList(Direction.LEFT, Direction.RIGHT, Direction.UP, Direction.DOWN), 1);
        }

        @Override
        public String toString() {
            return "CCTV{" +
                    "directions=" + directions +
                    ", rotates=" + rotates +
                    ", p=" + p +
                    '}';
        }
    }

}
