package com.siwoo.p16000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

@Used(algorithm = Algorithm.BFS)
public class P16932 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static int[][] BOARD, D = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
    
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
            if (get(v) == get(w)) return;
            int pv = get(v),
                    pw = get(w);
            components[pw] = pv;
            sizes[pv] += sizes[pw];
        }

        private int get(int v) {
            int root = v;
            while (root != components[root])
                root = components[root];
            while (root != v) {
                int p = components[v];
                components[v] = root;
                v = p;
            }
            return root;
        }

        public int size(int p) {
            return sizes[get(p)];
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        BOARD = new int[N][M];
        for (int i=0; i<N; i++)
            BOARD[i] = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        UF uf = new UF(N * M);
        for (int i=0; i<N; i++)
            for (int j=0; j<M; j++) {
                if (BOARD[i][j] == 1) {
                    for (int[] d : D) {
                        int dx = i + d[0], dy = j + d[1];
                        if (valid(dx, dy) && BOARD[dx][dy] == 1)
                            uf.connect(i * M + j, dx * M + dy);
                    }
                }
            }
        int answer = 0;
        for (int i=0; i<N; i++)
            for (int j=0; j<M; j++) {
                if (BOARD[i][j] == 0) {
                    Set<Integer> set = new HashSet<>();
                    for (int[] d: D) {
                        int dx = i + d[0],
                                dy = j + d[1];
                        if (valid(dx, dy) && BOARD[dx][dy] == 1)
                            set.add(uf.get(dx * M + dy));
                    }
                    int cnt = 1;
                    for (int p: set) 
                        cnt += uf.size(p);
                    answer = Math.max(cnt, answer);
                }
            }
        System.out.println(answer);
    }

    private static boolean valid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
