package com.siwoo.p6000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *  bfs 에서 v 정점에 대한 특정 방향에 존재하는 모든 정점을 방문 (레이져) 하여 거울을 설치
 *  90 도란 조건은 특정 방향에 대해서 모두 거울을 설치하므로 고려할필요 없음. (이미 방문했거나, 다음 방향에서 처리하기 때문에)
 *  bfs 특성에 의하여 시간 복잡도는 O(N*M), (1 ≤ W, H ≤ 100) = 10,000
 */
@Used(algorithm = Algorithm.BFS)
public class P6087 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static char[][] BOARD;
    private static int N, M;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        M = Integer.parseInt(token.nextToken());
        N = Integer.parseInt(token.nextToken());
        BOARD = new char[N][M];
        Point start = null, end = null;
        for (int i=0; i<N; i++) {
            char[] data = reader.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                BOARD[i][j] = data[j];
                if (BOARD[i][j] == 'C') {
                    if (start == null)
                        start = new Point(i, j);
                    else
                        end = new Point(i, j);
                }
            }
        }
        Queue<Point> q = new LinkedList<>();
        q.add(start);
        Map<Point, Integer> mirrors = new HashMap<>();
        mirrors.put(start, 0);
        while (!q.isEmpty()) {
            Point p = q.poll();
            if (end.equals(p)) {
                System.out.println(mirrors.get(p)-1);   //lines - 1 = mirrors
                return;
            }
            for (Point m: Point.POINTS) {
                Point w = new Point(p.x + m.x, p.y + m.y);
                while (w.valid() && BOARD[w.x][w.y] != '*') {
                    if (!mirrors.containsKey(w)) {
                        mirrors.put(w, mirrors.get(p) + 1);
                        q.add(w);
                    }
                    w = new Point(w.x + m.x, w.y + m.y);
                }
            }
        }
    }

    private static class Point {
        private static Point[] POINTS = {
                new Point(-1, 0), new Point(1, 0),
                new Point(0, -1), new Point(0, 1)
        };
        private final int x, y;

        private Point(int x, int y) {
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
