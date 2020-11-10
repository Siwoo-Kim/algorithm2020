package com.siwoo.p2000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class P2234 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static Cell[][] BOARD;
    private static Map<Cell.Direction, int[]> D = new HashMap<>();
    
    static {
        D.put(Cell.Direction.N, new int[] {-1, 0});
        D.put(Cell.Direction.S, new int[] {1, 0});
        D.put(Cell.Direction.W, new int[] {0, -1});
        D.put(Cell.Direction.E, new int[] {0, 1});
    }

    private static class UF {
        private Map<Cell, Integer> sizes = new HashMap<>();
        private Map<Cell, Cell> components = new HashMap<>();
        private int size = 0;

        public void add(Cell cell) {
            components.put(cell, cell);
            sizes.put(cell, 1);
            size++;
        }

        public void connect(Cell v, Cell w) {
            if (get(v) == get(w)) return;
            Cell pv = get(v),
                    pw = get(w);
            components.put(pw, pv);
            sizes.put(pv, sizes.get(pw) + sizes.get(pv));
            size--;
        }

        private Cell get(Cell v) {
            Cell root = v;
            while (root != components.get(root))
                root = components.get(root);
            while (v != root) {
                Cell p = components.get(v);
                components.put(p, root);
                v = p;
            }
            return v;
        }

        public int size(Cell cell) {
            return sizes.get(get(cell));
        }
    }
    
    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        M = Integer.parseInt(token.nextToken());
        N = Integer.parseInt(token.nextToken());
        BOARD = new Cell[N][M];
        UF uf = new UF();
        for (int i=0; i<N; i++) {
            token = new StringTokenizer(reader.readLine());
            for (int j=0; j<M; j++) {
                BOARD[i][j] = new Cell(i, j, Integer.parseInt(token.nextToken()));
                uf.add(BOARD[i][j]);
            }
        }
    
        for (int i=0; i<N; i++)
            for (int j=0; j<M; j++) {
                Cell cell = BOARD[i][j];
                for (Cell.Direction d: D.keySet()) {
                    if (cell.hasPath(d)) {
                        int[] dxy = D.get(d);
                        int dx = i + dxy[0],
                                dy = j + dxy[1];
                        uf.connect(cell, BOARD[dx][dy]);
                    }
                }
            }
        
        int maxCnt = 0;
        for (Cell cell: uf.components.keySet())
            maxCnt = Math.max(maxCnt, uf.size(cell));
        int maxForBreakWall = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                Cell cell = BOARD[i][j];
                for (Cell.Direction d: D.keySet()) {
                    int[] dxy = D.get(d);
                    int dx = i + dxy[0],
                            dy = j + dxy[1];
                    if (!cell.hasPath(d) && 
                            valid(dx, dy) &&
                            uf.get(cell) != uf.get(BOARD[dx][dy])) {
                        maxForBreakWall = Math.max(
                                uf.size(cell) + uf.size(BOARD[dx][dy]), 
                                maxForBreakWall);
                    }
                }
            }
        }
        System.out.println(uf.size);
        System.out.println(maxCnt);
        System.out.println(maxForBreakWall);
    }

    private static boolean valid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    private static class Cell {
        private final int wall;
        private final int id;
        
        private enum Direction {
            S(8), E(4), N(2), W(1);
            
            private final int bit;

            Direction(int bit) {
                this.bit = bit;
            }
        }
        
        public Cell(int x, int y, int wall) {
            this.wall = wall;
            this.id = x * M + y;
        }
        
        public boolean hasPath(Direction d) {
            return (d.bit & wall) == 0;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cell cell = (Cell) o;
            return wall == cell.wall &&
                    id == cell.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(wall, id);
        }

        @Override
        public String toString() {
            return "{" +
                    "wall=" + wall +
                    ", id=" + id +
                    '}';
        }
    }
}
