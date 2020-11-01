package com.siwoo.p17000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * d(x, y, Direction) 을 x, y 정점에서 (N,N) 을 가기위한 방법의 수라면,
 *  x, y, Horizontal = d(x, y+1, Horizontal) + d(x+1, y+1, DIAG)
 *  x, y, Vertical = d(x+1, y, Vertical) + d(x+1, y+1, DIAG)
 *  x, y, Diag = d(x, y+1, Horizontal) + d(x+1, y, Vertical) + d(x+1, y+1, DIAG)
 *  
 */
@Used(algorithm = Algorithm.DP)
public class P17069 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[][] BOARD;
    private static Map<Point, Long> DP = new HashMap<>();
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        BOARD = new int[N][N];
        for (int i=0; i<N; i++)
            BOARD[i] = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

        System.out.println(dp(new Point(0, 1, Point.Direction.HORIZONTAL)));
    }

    private static long dp(Point p) {
        if (p.x * N + p.y == N*N-1) return 1;
        if (DP.containsKey(p)) 
            return DP.get(p);
        long cnt = 0;
        for (Point m: Point.map.get(p.d)) {
            Point w = new Point(p.x + m.x, p.y + m.y, m.d);
            if (w.valid())
                 cnt += dp(w);
        }
        DP.put(p, cnt);
        return cnt;
    }

    private static class Point {
        private static final Map<Direction, List<Point>> map = new HashMap<>();
        private static final Point HORIZONTAL_POINT = new Point(0, 1, Direction.HORIZONTAL);
        private static final Point VERTICAL_POINT = new Point(1, 0, Direction.VERTICAL);
        private static final Point DIAG_POINT = new Point(1, 1, Direction.DIAG);
        private final int x, y;
        private final Direction d;
        
        static {
            map.put(Direction.HORIZONTAL, Arrays.asList(HORIZONTAL_POINT, DIAG_POINT));
            map.put(Direction.VERTICAL, Arrays.asList(VERTICAL_POINT, DIAG_POINT));
            map.put(Direction.DIAG, Arrays.asList(HORIZONTAL_POINT, VERTICAL_POINT, DIAG_POINT));
        }

        public boolean valid() {
            boolean ok = x >= 0 && x < N && y >= 0 && y < N 
                    && BOARD[x][y] != 1;
            if (ok && d == Direction.DIAG)
                ok = ok && (BOARD[x-1][y] != 1 && BOARD[x][y-1] != 1);
            return ok;
        }

        private enum Direction {
            HORIZONTAL, VERTICAL, DIAG
        }

        public Point(int x, int y, Direction d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y &&
                    d == point.d;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, d);
        }
    }
}
