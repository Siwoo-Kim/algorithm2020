package com.siwoo.p16000;

import java.io.*;
import java.util.*;

/**
 * O(4*NM*UF(N*M)*WALLS)
 */
public class P16946 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int[][] BOARD;
    private static boolean[][] WALLS;
    private static int N, M;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        BOARD = new int[N][M];
        WALLS = new boolean[N][M];
        for (int i = 0; i < N; i++)
            BOARD[i] = Arrays.stream(reader.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        UnionFind uf = new UnionFind(N * M);
        List<Point> walls = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Point p = new Point(i, j);
                if (BOARD[i][j] == 0) {
                    for (Point m : Arrays.asList(Point.POINTS[1], Point.POINTS[3])) { //optimization - below & right
                        Point w = new Point(p.x + m.x, p.y + m.y);
                        if (w.valid() && BOARD[w.x][w.y] == 0)
                            uf.connect(p.index(), w.index());
                    }
                } else {
                    walls.add(p);
                    WALLS[p.x][p.y] = true;
                }
            }
        }
        for (Point p : walls) {
            Set<Integer> visit = new HashSet<>();
            for (Point m : Point.POINTS) {
                Point w = new Point(p.x + m.x, p.y + m.y);
                if (!w.valid()) continue;
                if (BOARD[w.x][w.y] != 0) continue;   //zzz.. 10 % 10 == 0
                if (WALLS[w.x][w.y]) continue;
                int c = uf.get(w.index());
                visit.add(c);
            }
            int answer = 1;
            for (int group : visit)
                answer += uf.sizeOf(group);
            BOARD[p.x][p.y] = answer % 10;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++)
                writer.write(BOARD[i][j] + "");
            writer.write("\n");
        }
        writer.flush();
    }

    private static class Point {
        private static Point[] POINTS = {
                new Point(-1, 0), new Point(1, 0),
                new Point(0, -1), new Point(0, 1),
        };
        private int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean valid() {
            return x >= 0 && x < N && y >= 0 && y < M;
        }

        public int index() {
            return x * M + y;
        }
    }

    private static class UnionFind {
        private int[] components;
        private long[] sizes;

        public UnionFind(int size) {
            this.components = new int[size];
            this.sizes = new long[size];
            for (int i = 0; i < size; i++) {
                components[i] = i;
                sizes[i] = 1;
            }
        }

        public void connect(int v, int w) {
            if (connected(v, w)) return;
            int pv = get(v),
                    pw = get(w);
            sizes[pv] += sizes[pw];
            components[pw] = pv;
        }

        private boolean connected(int v, int w) {
            return get(v) == get(w);
        }

        private int get(int v) {
            int root = v;
            while (root != components[root])
                root = components[root];
            while (v != root) { //zip
                int pv = components[v];
                components[v] = pv;
                v = pv;
            }
            return v;
        }

        public long sizeOf(int c) {
            return sizes[get(c)];
        }
    }
}