package com.siwoo.p16000;

import com.siwoo.util.Marker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Marker //시간초과
public class P16973 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] BOARD, D = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static int N, M, H, W;
    private static Set<Pair> BLOCK = new HashSet<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        BOARD = new int[N][M];
        for (int i=0; i<N; i++) {
            BOARD[i] = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int j=0; j<M; j++) {
                if (BOARD[i][j] == 1)
                    BLOCK.add(new Pair(i, j));
            }
        }
        token = new StringTokenizer(reader.readLine());
        H = Integer.parseInt(token.nextToken());
        W = Integer.parseInt(token.nextToken());
        Pair start = new Pair(Integer.parseInt(token.nextToken()) - 1, 
                Integer.parseInt(token.nextToken()) - 1),
                end = new Pair(Integer.parseInt(token.nextToken()) - 1,
                        Integer.parseInt(token.nextToken()) - 1);
        Map<Pair, Integer> distance = new HashMap<>();
        distance.put(start, 0);
        Queue<Pair> q = new LinkedList<>();
        q.add(start);
        while (!q.isEmpty()) {
            Pair p = q.poll();
            if (p.equals(end)) {
                System.out.println(distance.get(p));
                return;
            }
            for (int[] d: D) {
                Pair w = new Pair(p.x + d[0], p.y + d[1]);
                if (w.valid() && !distance.containsKey(w)) {
                    distance.put(w, distance.get(p) + 1);
                    q.add(w);
                }
            }
        }
        System.out.println(-1);
    }
    
    private static class Pair {
        final int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean valid() {
            int ex = x + H - 1,
                    ey = y + W - 1;
            if (!checkRange(x, y) || !checkRange(ex, ey))
                return false;
            for (Pair b: BLOCK) {
                if (b.x>=x && b.x<=ex && b.y>=y && b.y<=ey)
                    return false;
            }
            return true;
        }

        private boolean checkRange(int x, int y) {
            return x >= 0 && x < N && y >= 0 && y < M;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x &&
                    y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
