package com.siwoo.p1000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

@Used(algorithm = Algorithm.BACK_TRACKING)
public class P1987 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static char[][] BOARD;
    private static boolean[] visit = new boolean['Z' + 1];

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        BOARD = new char[N][M];
        for (int i=0; i<N; i++)
            BOARD[i] = reader.readLine().toCharArray();
        visit[BOARD[0][0]] = true;
        int answer = select(0);
        System.out.println(answer);
    }

    private static int select(int index) {
        Point p = new Point(index);
        int max = 0;
        for (Point m: Point.POINTS) {
            Point p2 = new Point(p.row + m.row, p.col + m.col);
            if (p2.valid() && !visit[BOARD[p2.row][p2.col]]) {  //BACK-TRACKING
                visit[BOARD[p2.row][p2.col]] = true;
                max = Math.max(max, select(p2.index()));
                visit[BOARD[p2.row][p2.col]] = false;
            }
        }
        return max+1;
    }

    private static class Point {
        private static Point[] POINTS = {
                new Point(-1, 0), new Point(1, 0),
                new Point(0, -1), new Point(0, 1)
        };
        private int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Point(int index) {
            this(index / M, index % M);
        }

        public boolean valid() {
            return row >= 0 && row < N && col >= 0 && col < M;
        }

        public int index() {
            return row * M + col;
        }
    }
}
