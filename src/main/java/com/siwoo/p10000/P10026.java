package com.siwoo.p10000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Used(algorithm = Algorithm.UNION_FIND)
public class P10026 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static char[][] BOARD;
    private static int N;

    private static class UnionFind {
        private final int[] components;
        private int size;

        public UnionFind(int size) {
            this.size = size;
            this.components = new int[size];
            for (int i=0; i<size; i++)
                components[i] = i;
        }

        public void connect(int p, int w) {
            if (connected(p, w)) return;
            int pw = get(p),
                    pv = get(w);
            components[pw] = pv;
            size--;
        }

        private boolean connected(int p, int w) {
            return get(p) == get(w);
        }

        private int get(int p) {
            int root = p;
            while (components[root] != root)
                root = components[root];
            while (p != root) {     //zip
                int pv = components[p];
                components[pv] = root;
                p = pv;
            }
            return p;
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        BOARD = new char[N][N];
        for (int i=0; i<N; i++)
            BOARD[i] = reader.readLine().toCharArray();

        UnionFind uf1 = new UnionFind(N*N),
                uf2 = new UnionFind(N*N);
        for (int i=0; i<N; i++)
            for (int j=0; j<N; j++) {
                Point p = new Point(i, j);
                char c1 = BOARD[p.x][p.y];
                for (Point m: Point.POINTS) {
                    Point w = new Point(p.x + m.x, p.y + m.y);
                    if (!w.valid()) continue;
                    char c2 = BOARD[w.x][w.y];
                    if (c1 == c2)
                        uf1.connect(w.index(), p.index());
                    if (group(c1) == group(c2))
                        uf2.connect(w.index(), p.index());
                }
            }
        System.out.printf("%d %d", uf1.size, uf2.size);
    }

    private static int group(char c) {
        if (c == 'R' || c == 'G') return 1;
        else return 2;
    }

    private static class Point {
        private static Point[] POINTS = {
                new Point(1, 0), new Point(0, 1)
        };
        private final int x, y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean valid() {
            return x >= 0 && x < N && y >= 0 && y < N;
        }

        public int index() {
            return x * N + y;
        }
    }
}
