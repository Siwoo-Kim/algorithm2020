package com.siwoo.p16000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class P16929 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static char[][] BOARD;
    private static int[][] D = {
            {-1, 0}, {1, 0},
            {0, -1}, {0, 1},
    };

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        BOARD = new char[N][M];
        for (int i=0; i<N; i++)
            BOARD[i] = reader.readLine().toCharArray();
        Set<Integer> visit = new HashSet<>();
        for (int i=0; i<N; i++)
            for (int j=0; j<M; j++) {
                int id = i * M + j;
                if (!visit.contains(id)) {
                    if (cycle(id, -1, visit, BOARD[i][j])) {
                        System.out.println("Yes");
                        return;
                    }
                }
            }
        System.out.println("No");
    }

    private static boolean cycle(int v, int w, Set<Integer> visit, char color) {
        if (visit.contains(v)) return true;
        visit.add(v);
        int x = v / M;
        int y = v % M;
        for (int[] d: D) {
            int dx = x + d[0],
                    dy = y + d[1];
            if (dx >= 0 && dx < N && dy >= 0 && dy < M
                    && dx * M + dy != w //길이가 2인 사이클 배제.
                    && BOARD[dx][dy] == color)
                if (cycle(dx * M + dy, v, visit, color))
                    return true;
        }
        return false;
    }
}
