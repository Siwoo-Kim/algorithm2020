package com.siwoo.p2000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

@Used(algorithm = Algorithm.UNION_FIND)
public class P2468 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[][] BOARD, D = {{1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        BOARD = new int[N][N];
        int max = 0;
        for (int i=0; i<N; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            for (int j=0; j<N; j++) {
                BOARD[i][j] = Integer.parseInt(token.nextToken());
                max = Math.max(BOARD[i][j], max);
            }
        }
        int answer = 0;
        for (int depth=0; depth<=max; depth++) {
            UF uf = new UF(N*N);
            int overflows = 0;
            for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    if (BOARD[i][j] <= depth) {
                        overflows++;
                        continue;
                    }
                    for (int[] d: D) {
                        int dx = i + d[0],
                                dy = j + d[1];
                        if (dx >= 0 && dx < N && dy >= 0 && dy < N && BOARD[dx][dy] > depth)
                            uf.connect(i * N + j, dx * N + dy);
                    }
                }
            }
            answer = Math.max(uf.size - overflows, answer);
        }
        System.out.println(answer);
    }
    
    private static class UF {
        final int[] components;
        final int[] sizes;
        int size;

        public UF(int size) {
            this.size = size;
            this.components = new int[size];
            this.sizes = new int[size];
            for (int i=0; i<size; i++) {
                components[i] = i;
                sizes[i] = 1;
            }
        }

        public boolean connect(int v, int w) {
            if (connected(v, w)) return false;
            v = get(v);
            w = get(w);
            if (sizes[v] < sizes[w]) {
                int t = v;
                v = w;
                w = t;
            }
            components[w] = v;
            sizes[v] += sizes[w];
            size--;
            return true;
        }

        private boolean connected(int v, int w) {
            return get(v) == get(w);
        }
        
        private int get(int v) {
            if (v == components[v]) return v;
            return components[v] = get(components[v]);  //zip
        }
    }
}
