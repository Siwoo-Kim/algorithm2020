package com.siwoo.p4000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 숫자를 놓을 시 domino pair 로 같이 넣어야 한다.
 * v 을 짝을 지을 시 w 을 확인시, 오른쪽, 아래 방향으로만 확인하며 w == 0 이라면 해당 w 은 아직 pairing 이 되지 않는다.
 * (index 0 부터 N*N 으로 순회시)
 */
@Used(algorithm = Algorithm.BACK_TRACKING)
public class P4574 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int MAX = 9, GROUP_SIZE = MAX / 3, N, TRY;
    private static int[][] BOARD;
    private static boolean[][] pairs;
    private static State state;

    public static void main(String[] args) throws IOException {
        while (true) {
            N = Integer.parseInt(reader.readLine());
            if (N == 0) return;
            state = new State();
            BOARD = new int[MAX][MAX];
            pairs = new boolean[MAX+1][MAX+1];
            StringTokenizer token;
            for (int i=0; i<N; i++) {
                token = new StringTokenizer(reader.readLine());
                int v1 = Integer.parseInt(token.nextToken());
                Point p1 = new Point(token.nextToken());
                int v2 = Integer.parseInt(token.nextToken());
                Point p2 = new Point(token.nextToken());
                BOARD[p1.row][p1.col] = v1;
                BOARD[p2.row][p2.col] = v2;
                state.push(p1, v1);
                state.push(p2, v2);
                pairs[v1][v2] = pairs[v2][v1] = true;
            }
            token = new StringTokenizer(reader.readLine());
            for (int i=1; i<=MAX; i++) {
                Point p = new Point(token.nextToken());
                BOARD[p.row][p.col] = i;
                state.push(p, i);
            }
            select(0);
        }
    }

    private static boolean select(int index) {
        if (index == MAX * MAX) {
            System.out.printf("Puzzle %d%n", ++TRY);
            for (int i=0; i<MAX; i++) {
                for (int j = 0; j < MAX; j++)
                    System.out.print(BOARD[i][j]);
                System.out.println();
            }
            return true;
        }
        Point p = new Point(index);
        if (BOARD[p.row][p.col] != 0) return select(index+1);
        for (int i=1; i<=MAX; i++) {
            if (state.check(p, i)) {
                for (Point m: Point.POINT) {
                    Point p2 = new Point(p.row + m.row, p.col + m.col);
                    if (!p2.valid()) continue;
                    if (BOARD[p2.row][p2.col] != 0) continue;
                    for (int j=1; j<=MAX; j++) {
                        if (i == j) continue;
                        if (state.check(p2, j) && !pairs[i][j]) {   //BACK-TRACKING
                            state.push(p, i);
                            state.push(p2, j);
                            pairs[i][j] = pairs[j][i] = true;
                            BOARD[p.row][p.col] = i;
                            BOARD[p2.row][p2.col] = j;
                            if (select(index + 1))
                                return true;
                            pairs[i][j] = pairs[j][i] = false;
                            BOARD[p.row][p.col] = 0;
                            BOARD[p2.row][p2.col] = 0;
                            state.pop(p, i);
                            state.pop(p2, j);
                        }
                    }
                }
            }
        }
        return false;
    }

    private static class State {
        private boolean[][] cols = new boolean[MAX][MAX+1];
        private boolean[][] rows = new boolean[MAX][MAX+1];
        private boolean[][] groups = new boolean[MAX][MAX+1];

        public void push(Point p, int v) {
            putState(p, v, true);
        }

        public void pop(Point p, int v) {
            putState(p, v, false);
        }

        private void putState(Point p, int v, boolean b) {
            cols[p.col][v] = rows[p.row][v] = groups[p.group()][v] = b;
        }

        public boolean check(Point p, int v) {
            if (cols[p.col][v]) return false;
            if (rows[p.row][v]) return false;
            return !groups[p.group()][v];
        }
    }

    private static class Point {
        private static Point[] POINT = {new Point(1, 0), new Point(0, 1)};
        private int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Point(int index) {
            this(index / MAX, index % MAX);
        }

        public Point(String s) {
            this(s.charAt(0) - 'A',
                    s.charAt(1) - '1');
        }

        public int index() {
            return row * MAX + col;
        }

        public int group() {
            return (row / GROUP_SIZE) * GROUP_SIZE + (col / GROUP_SIZE);
        }

        public boolean valid() {
            return row >= 0 && row < MAX && col >= 0 && col < MAX;
        }

        @Override
        public String toString() {
            return String.format("(%d,%d)", row, col);
        }
    }
}
