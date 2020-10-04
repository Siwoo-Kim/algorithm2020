package com.siwoo.p4000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Used(algorithm = Algorithm.UNION_FIND)
public class P4963 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int W, H;
    private static int[][] BOARD;

    private static class UnionFind {
        private Map<Integer, Integer> components = new HashMap<>();
        private int size;

        public UnionFind(int size) {
            this.size = size;
            components = new HashMap<>();
        }

        public void add(int c) {
            components.put(c, c);
        }

        public void connect(int v, int w) {
            if (connected(v, w)) return;
            int pv = get(v),
                    pw = get(w);
            components.put(pv, pw);
            size--;
        }

        private int get(int v) {
            int root = v;
            while (components.get(root) != root)
                root = components.get(root);
            while (v != root) {
                int pv = components.get(v);
                components.put(v, root);
                v = pv;
            }
            return root;
        }

        private boolean connected(int v, int w) {
            return get(v) == get(w);
        }
    }

    private static int[][] D = {
            {-1, 0}, {1, 0},
            {0, -1}, {0, 1},
            {-1, -1}, {-1, 1},
            {1, -1}, {1, 1}
    };

    public static void main(String[] args) throws IOException {
        while (true) {
            int[] data = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            W = data[0];
            H = data[1];
            if (W == 0) return;
            BOARD = new int[H][W];

            List<Integer> components = new ArrayList<>();
            for (int i=0; i<H; i++) {
                data = Arrays.stream(reader.readLine().split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                for (int j=0; j<W; j++) {
                    BOARD[i][j] = data[j];
                    if (BOARD[i][j] == 1)
                        components.add(i * W + j);
                }
            }
            UnionFind uf = new UnionFind(components.size());
            for (int c: components)
                uf.add(c);

            for (int i=0; i<H; i++)
                for (int j=0; j<W; j++) {
                    if (BOARD[i][j] == 1) {
                        for (int[] d: D) {
                            int dx = i + d[0];
                            int dy = j + d[1];
                            if (dx >= 0 && dx < H &&
                                    dy >= 0 && dy < W
                                    && BOARD[dx][dy] == 1) {
                                uf.connect(i * W + j, dx * W + dy);
                            }
                        }
                    }
                }
            System.out.println(uf.size);
        }
    }
}
