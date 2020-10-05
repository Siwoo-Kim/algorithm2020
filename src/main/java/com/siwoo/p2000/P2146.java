package com.siwoo.p2000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 모든 섬을 연결 요소로 구분. -> union_find(i, adj(i))
 * 모든 좌표의 섬 i 에 대해서 bfs 로 바다 j 에 대한 거리 계산 d[j]=d[i]+1 이후 i, j을 연결-> bfs & union(i,j)
 * 다른 연결 요소 d[i], d[j] 을 이용하여 최소 거리 도출
 *
 */
@Used(algorithm = Algorithm.UNION_FIND)
public class P2146 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[][] D = {
            {-1, 0}, {1, 0},
            {0, -1}, {0, 1},
    };

    private static int[][] BOARD;

    private static class UnionFind {
        private static int[] components;

        public UnionFind(int size) {
            components = new int[size];
            for (int i=0; i<size; i++)
                components[i] = i;
        }

        public void connect(int v, int w) {
            if (connected(v, w)) return;
            int pv = get(v),
                    pw = get(w);
            components[pv] = pw;
        }

        private boolean connected(int v, int w) {
            return get(v) == get(w);
        }

        private int get(int v) {
            int root = v;
            while (root != components[root])
                root = components[root];
            while (v != root) {
                int pv = components[v];
                components[v] = root;
                v = pv;
            }
            return root;
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(reader.readLine());
        UnionFind uf = new UnionFind(N*N);
        BOARD = new int[N][N];
        Queue<Integer> q = new LinkedList<>();
        Map<Integer, Integer> distance = new HashMap<>();
        Set<Integer> visit = new HashSet<>();
        for (int i=0; i<N; i++) {
            BOARD[i] = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int j=0; j<N; j++) {
                if (BOARD[i][j] == 1) {
                    q.add(i * N + j);
                    distance.put(i * N + j, 0);
                    visit.add(i * N + j);
                    for (int[] d: D) {
                        int dx = i + d[0];
                        int dy = j + d[1];
                        if (dx >= 0 && dx < N && dy >= 0 && dy < N && BOARD[dx][dy] == 1) {
                            uf.connect(i * N + j, dx * N + dy);
                        }
                    }
                }
            }
        }
        while (!q.isEmpty()) {
            int v = q.poll();
            int x = v / N;
            int y = v % N;
            for (int[] d: D) {
                int dx = x + d[0],
                        dy = y + d[1],
                        id = dx * N + dy;
                if (dx >= 0 && dx < N && dy >= 0 && dy < N && !visit.contains(id)) {
                    visit.add(id);
                    q.add(id);
                    BOARD[dx][dy] = 1;
                    uf.connect(v, id);
                    distance.put(id, distance.get(v) + 1);
                }
            }
        }
        int answer = Integer.MAX_VALUE;
        for (int i=0; i<N; i++)
            for (int j=0; j<N; j++) {
                for (int[] d: D) {
                    int dx = i + d[0];
                    int dy = j + d[1];
                    if (dx >= 0 && dx < N && dy >= 0 && dy < N &&
                            uf.get(i * N + j) != uf.get(dx * N + dy)) {
                        answer = Math.min(answer, distance.get(i * N + j) + distance.get(dx * N + dy));
                    }
                }
            }
        System.out.println(answer);
    }
}
