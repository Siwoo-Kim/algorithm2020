package com.siwoo.p16000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

@Used(algorithm = Algorithm.BFS)
public class P16236 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] BOARD;
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        BOARD = new int[N][N];
        Shark shark = null;
        for (int i=0; i<N; i++) {
            String[] data = reader.readLine().split("\\s+");
            for (int j=0; j<N; j++) {
                BOARD[i][j] = Integer.parseInt(data[j]);
                if (BOARD[i][j] == 9) {
                    shark = new Shark(2, 0, new Point(i, j));
                    BOARD[i][j] = 0;
                }
            }
        }
        int seconds = 0;
        while (true) {
            Result result = simulate(shark);
            if (result.seconds == 0) {
                System.out.println(seconds);
                return;
            }
            seconds += result.seconds;
            shark = new Shark(shark.size, shark.eat + 1, result.p);
        }
    }

    private static Result simulate(Shark shark) {
        Queue<Point> q = new LinkedList<>();
        q.add(shark.p);
        Map<Point, Integer> distance = new HashMap<>();
        distance.put(shark.p, 0);
        List<Point> prays = new ArrayList<>();
        int min = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            Point p = q.poll();
            for (Point m: Point.POINTS) {
                Point w = new Point(p.x + m.x, p.y + m.y);
                if (!w.valid()
                        || BOARD[w.x][w.y] > shark.size
                        || distance.containsKey(w)) continue;
                if (BOARD[w.x][w.y] == 0
                        || shark.size == BOARD[w.x][w.y]) {
                    distance.put(w, distance.get(p) + 1);
                    q.add(w);
                } else {
                    distance.put(w, distance.get(p) + 1);
                    min = Math.min(distance.get(w), min);
                    prays.add(w);
                }
            }
        }
        int minFinal = min;
        prays = prays.stream()
                .filter(p -> distance.get(p) == minFinal)
                .collect(Collectors.toList());
        Collections.sort(prays);
        Result result;
        if (prays.isEmpty())    //no pray
            result  = new Result(0, null);
        else {
            Point p = prays.get(0);
            result = new Result(distance.get(p), p);
            BOARD[p.x][p.y] = 0;
        }
        return result;
    }

    private static class Result {
        private int seconds;
        private Point p;    //last position

        public Result(int seconds, Point p) {
            this.seconds = seconds;
            this.p = p;
        }
    }

    private static class Shark {
        private final int size;
        private final int eat;
        private final Point p;

        public Shark(int size, int eat, Point p) {
            if (size <= eat) {  //growing
                int remain = eat - size;
                this.size = size + 1;
                this.eat = remain;
            } else {
                this.size = size;
                this.eat = eat;
            }
            this.p = p;
        }
    }

    private static class Point implements Comparable<Point> {
        private static Comparator<Point>
                comparator = Comparator.comparingInt(Point::getX)
                                    .thenComparing(Point::getY);

        private static Point[] POINTS = {
                new Point(-1, 0), new Point(0, -1),
                new Point(0, 1), new Point(1, 0)
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
        public int compareTo(Point o) {
            return comparator.compare(this, o);
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
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
