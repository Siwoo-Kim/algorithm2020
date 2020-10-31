package com.siwoo.p17000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

@Used(algorithm = Algorithm.BRUTE_FORCE)
public class P17406 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, K;
    private static int[][] BOARD;;
    private static int[][] O;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        K = Integer.parseInt(token.nextToken());
        BOARD = new int[N][M];
        O = new int[K][3];
        for (int i=0; i<N; i++)
            BOARD[i] = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        for (int i=0; i<K; i++)
            O[i] = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        int min = permutation(new Stack<>());
        System.out.println(min);
    }

    private static int permutation(Stack<Integer> stack) {
        if (stack.size() == K) {
            int[] indexes = stack.stream().mapToInt(s -> s).toArray();
            for (int i=0; i<indexes.length; i++)
                rotate(true,O[indexes[i]][0]-1, O[indexes[i]][1]-1, O[indexes[i]][2]);
            int min = Integer.MAX_VALUE;
            for (int i=0; i<N; i++) {
                int cnt = 0;
                for (int j=0; j<M; j++)
                    cnt += BOARD[i][j];
                min = Math.min(cnt, min);
            }
            for (int i=indexes.length-1; i>=0; i--)
                rotate(false,O[indexes[i]][0]-1, O[indexes[i]][1]-1, O[indexes[i]][2]);
            return min;
        }
        int min = Integer.MAX_VALUE;
        for (int i=0; i<K; i++) {
            if (stack.contains(i)) continue;
            stack.push(i);
            min = Math.min(permutation(stack), min);
            stack.pop();
        }
        return min;
    }

    private static void rotate(boolean clockwise, int x, int y, int size) {
        if (size == 0) return;
        int dx = x - size, dy = y - size;
        int rows = size * 2 + 1;
        Point p = push(!clockwise, true, BOARD[dx][dy], dx, dy, rows);
        p = push(clockwise, true, p.v, p.x, p.y, rows);
        p = push(!clockwise, false, p.v, p.x, p.y, rows);
        push(clockwise, false, p.v, p.x, p.y, rows);
        rotate(clockwise, x, y, size-1);
    }

    private static class Point {
        int x, y, v;

        public Point(int x, int y, int v) {
            this.x = x;
            this.y = y;
            this.v = v;
        }
    }

    static int[] rotates(int size, boolean increase) {
        int[] is = new int[size];
        for (int i=0; i<size; i++)
            is[i] = increase? i: -i;
        return is;
    }

    private static Point push(boolean rowFixed,
                              boolean increase,
                              int start,  int x, int y, int size) {
        Point p = null;
        for (int i: rotates(size-1, increase)) {
            int dx = rowFixed? x + i + (increase? 1: -1): x;
            int dy = rowFixed? y: y + i + (increase? 1: -1);
            int tmp = BOARD[dx][dy];
            BOARD[dx][dy] = start;
            start = tmp;
            p = new Point(dx, dy, tmp);
        }
        return p;
    }
}
