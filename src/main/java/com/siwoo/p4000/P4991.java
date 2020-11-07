package com.siwoo.p4000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * BFS + PERMUTATION
 *  => BFS 로 가중치 계산 이후로 순열 대신 다익스트라로 풀 수 있을 듯.
 *  => N, M 의 크기와 더러운 칸의 갯수가 10개 밖에 되지 않으므로 ok
 *  
 *  =(10^2*NM)+(10!*10)
 *  =10!*10
 *  =36,288,000
 *  
 */
@Used(algorithm = Algorithm.BFS)
public class P4991 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static char[][] BOARD;
    private static int[][] DISTANCE;

    public static void main(String[] args) throws IOException {
        while (true) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            M = Integer.parseInt(token.nextToken());
            N = Integer.parseInt(token.nextToken());
            if (M == 0 && N == 0) return;
            BOARD = new char[N][M];
            DISTANCE = new int[N * M][N * M];
            Point robot = null;
            List<Point> dirties = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                String line = reader.readLine();
                for (int j = 0; j < M; j++) {
                    BOARD[i][j] = line.charAt(j);
                    if (BOARD[i][j] == '*')
                        dirties.add(new Point(i, j));
                    if (BOARD[i][j] == 'o') {
                        robot = new Point(i, j);
                        dirties.add(robot);
                    }
                }
            }

            boolean ok = true;
            for (Point d1 : dirties) {
                for (Point d2 : dirties) {
                    if (d1 == d2) continue;
                    int d = bfs(d1, d2);
                    if (d == -1) {
                        ok = false;
                        break;
                    }
                    DISTANCE[d1.index()][d2.index()] = d;
                }
            }
            if (!ok) {
                System.out.println(-1);
            } else {
                final Point r = robot;
                int[] tmp = dirties.stream().filter(e -> e != r)
                        .map(Point::index)
                        .sorted()
                        .mapToInt(i -> i)
                        .toArray();
                int[] p = new int[tmp.length + 1];
                System.arraycopy(tmp, 0, p, 1, tmp.length);
                p[0] = r.index();
                int answer = Integer.MAX_VALUE;
                do {
                    int d = 0;
                    for (int i = 0; i < p.length - 1; i++)
                        d += DISTANCE[p[i]][p[i + 1]];
                    answer = Math.min(d, answer);
                } while (nextPermutation(1, p.length, p));
                System.out.println(answer);
            }
        }
    }
    
    private static boolean nextPermutation(int begin, int end, int[] p) {
        int i = end-1;
        while (i > begin && p[i-1] >= p[i])
            i--;
        if (i == begin) return false;
        int j = end-1;
        while (p[i-1] >= p[j])
            j--;
        swap(i-1, j, p);
        j = end-1;
        while (j > i)
            swap(j--, i++, p);
        return true;
    }

    private static void swap(int i, int j, int[] p) {
        int t = p[i];
        p[i] = p[j];
        p[j] = t;
    }

    private static int bfs(Point start, Point end) {
        Queue<Point> q = new LinkedList<>();
        q.add(start);
        Map<Point, Integer> dist = new HashMap<>();
        dist.put(start, 0);
        while (!q.isEmpty()) {
            Point v = q.poll();
            if (v.equals(end))
                return dist.get(v);
            for (Point d: Point.D) {
                Point w = new Point(v.x + d.x, v.y + d.y);
                if (!w.valid()) continue;
                if (BOARD[w.x][w.y] == 'x') continue;
                if (!dist.containsKey(w)) {
                    dist.put(w, dist.get(v) + 1);
                    q.add(w);
                }
            }
        }
        return -1;
    }

    private static class Point {
        private static final Point[] D = {
                new Point(-1, 0), new Point(1, 0),
                new Point(0, -1), new Point(0, 1)
        };
        private final int x, y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int index() {
            return x * M + y;
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
