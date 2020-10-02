package com.siwoo.p2000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

@Used(algorithm = Algorithm.UNION_FIND)
public class P2667 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[][] D = { {0, 1},  {1, 0} };
    private static int[][] BOARD;

    private static class UnionFind {
        private static int[] uf;
        private static int[] sizes;
        private static int size;

        public UnionFind(int n) {
            size = n;
            uf = new int[size];
            sizes = new int[size];
            for (int i=0; i<size; i++) {
                uf[i] = i;
                sizes[i] = 1;
            }
        }

        public void connect(int v, int w) {
            if (connected(v, w)) return;
            int pv = get(v),
                    pw = get(w);
            uf[pv] = pw;    //quick find
            sizes[pw] += sizes[pv];
            size--;
        }

        private boolean connected(int v, int w) {
            return get(v) == get(w);
        }

        private int get(int v) {
            while (v != uf[v]) {
                int pv = uf[v];
                uf[v] = pv; //zip
                v = pv;
            }
            return v;
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        BOARD = new int[N][N];
        for (int i=0; i<N; i++)
            BOARD[i] = Arrays.stream(reader.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        int block = 0;
        UnionFind uf = new UnionFind(N*N);
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (BOARD[i][j] == 1) {
                    int id = i * N + j;
                    for (int[] d: D) {
                        int dx = i + d[0];
                        int dy = j + d[1];
                        if (dx < N && dy < N && BOARD[dx][dy] == 1) {
                            uf.connect(id, dx * N + dy);
                        }
                    }
                } else {
                    block++;
                }
            }
        }
        System.out.println(uf.size - block);
        PriorityQueue<Integer> q = new PriorityQueue<>();
        Set<Integer> visit = new HashSet<>();
        for (int i=0; i<N; i++)
            for (int j=0; j<N; j++)
                if (!visit.contains(uf.get(i * N + j))
                        && BOARD[i][j] == 1) {
                    visit.add(uf.get(i * N + j));
                    q.add(uf.sizes[uf.get(i * N + j)]);
                }
        while (!q.isEmpty())
            System.out.println(q.poll());
    }
}
