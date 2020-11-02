package com.siwoo.p16000;


import org.omg.PortableInterceptor.INACTIVE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class P16988 {
    private static BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static Map<Integer, List<Integer>> G = new HashMap<>();
    private static int[][] BOARD;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(READER.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        BOARD = new int[N][M];
        for (int i=0; i<N; i++) 
            BOARD[i] = Arrays.stream(READER.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        UF uf = new UF(N*M);
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (BOARD[i][j] == 0) continue;
                Point p = new Point(i, j);
                Point ap1 = new Point(i+1, j);
                Point ap2 = new Point(i, j+1);
                for (Point w: Arrays.asList(ap1, ap2)) 
                if (w.valid() && BOARD[p.x][p.y] == BOARD[w.x][w.y])
                    uf.connect(p.index(), w.index());
            }
        }
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (BOARD[i][j] == 0) continue;
                int p = new Point(i, j).index();
                int root = uf.get(p);
                G.computeIfAbsent(root, k -> new ArrayList<>());
                G.get(root).add(p);
            }
        }
        int cnt = select(0, new Stack<>(), uf);
        System.out.println(cnt);
    }

    private static int select(int index, 
                              Stack<Integer> select,
                              UF uf) {
        if (select.size() == 2) {
            return calc(select.stream()
                    .map(i -> new Point(i / M, i % M))
                    .collect(Collectors.toList()), uf);
        }
        if (index == N * M) return 0;
        int x = 0;
        if (BOARD[index/M][index%M] == 0) {
            select.add(index);
            x = select(index + 1, select, uf);
            select.pop();
        }
        return Math.max(x, select(index+1, select, uf));
    }

    private static int calc(List<Point> black, UF uf) {
        Set<Integer> roots = new HashSet<>();
        for (Point b: black)
            BOARD[b.x][b.y] = 1;
        for (Point b: black) {
            for (Point m: Point.POINTS) {
                Point w = new Point(b.x + m.x, b.y + m.y);
                if (w.valid() 
                        && BOARD[w.x][w.y] == 2)
                    roots.add(uf.get(w.index()));
            }
        }
        int cnt = 0;
        for (int root: roots) {
            boolean ok = true;
            for (int c: G.get(root)) {
                Point p = new Point(c / M, c % M);
                for (Point m: Point.POINTS) {
                    Point adj = new Point(p.x + m.x, p.y + m.y);
                    if (adj.valid() && BOARD[adj.x][adj.y] == 0) {
                        ok = false;
                        break;
                    }
                }
            }
            cnt += ok? uf.size(root): 0;
        }
        for (Point b: black)
            BOARD[b.x][b.y] = 0;
        return cnt;
    }


    private static class Point {
        private static Point[] POINTS = {
                new Point(-1, 0), new Point(1, 0),
                new Point(0, -1), new Point(0, 1),
        };
        private final int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int index() {
            return x * M  + y;
        }

        public boolean valid() {
            return x >= 0 && x < N && y >= 0 && y < M;
        }
    }
    
    private static class UF {
        private int[] components;
        private int[] sizes;

        public UF(int size) {
            components = new int[size];
            sizes = new int[size];
            for (int i=0; i<size; i++) {
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
            while (v != root) {
                int parent = components[v];
                components[v] = root;
                v = parent;
            }
            return root;
        }

        public int size(int p) {
            return sizes[get(p)];
        }
    }
}
