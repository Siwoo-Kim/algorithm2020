package com.siwoo.p17000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * v=바이러스 갯수, m=바이러스을 놓을 수 있는 위치.
 * O(vCm*N^2)
 */
@Used(algorithm = Algorithm.BFS)
public class P17141 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] BOARD;
    private static int N, M, BLANKET;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        BOARD = new int[N][N];
        List<Point> viruses = new ArrayList<>();
        for (int i=0; i<N; i++) {
            token = new StringTokenizer(reader.readLine());
            for (int j=0; j<N; j++) {
                BOARD[i][j] = Integer.parseInt(token.nextToken());
                if (BOARD[i][j] == 2)
                    viruses.add(new Point(i, j));
                if (BOARD[i][j] != 1)
                    BLANKET++;
            }
        }
        List<List<Point>> combs = combinations(viruses, new Stack<>(), new ArrayList<>(), 0, M);
        int time = -1;
        for (List<Point> vs: combs) {
            int t = bfs(vs);
            if (t == -1) continue;
            if (time == -1 || time > t)
                time = t;
        }
        System.out.println(time);
    }

    private static int bfs(List<Point> vs) {
        Queue<Point> q = new LinkedList<>();
        Map<Point, Integer> dist = new HashMap<>();
        int cnt = BLANKET;
        for (Point p: vs) {
            q.add(p);
            dist.put(p, 0);
        }
        int max = 0;
        while (!q.isEmpty()) {
            Point v = q.poll();
            cnt--;
            max = Math.max(max, dist.get(v));
            for (Point d: Point.D) {
                Point w = new Point(v.x + d.x, v.y + d.y);
                if (!w.valid()) continue;
                if (BOARD[w.x][w.y] == 1) continue;
                if (!dist.containsKey(w)) {
                    dist.put(w, dist.get(v) + 1);
                    q.add(w);
                }
            }
        }
        return cnt != 0? -1: max;
    }

    private static List<List<Point>> combinations(List<Point> viruses, 
                                                  Stack<Point> stack, 
                                                  List<List<Point>> result, 
                                                  int index,
                                                  int m) {
        if (stack.size() == m) {
            result.add(new ArrayList<>(stack));
            return result;
        }
        for (int i=index; i<viruses.size(); i++) {
            stack.push(viruses.get(i));
            combinations(viruses, stack, result, i+1, m);
            stack.pop();
        }
        return result;
    }

    private static class Point {
        private static Point[] D = {
                new Point(-1, 0), new Point(1, 0),
                new Point(0, -1), new Point(0, 1)
        };
        private final int x, y;

        private Point(int x, int y) {
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
