package com.siwoo.p17000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Used(algorithm = Algorithm.BRUTE_FORCE)
public class P17085 {
    private static BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static char[][] BOARD;
    
    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(READER.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        BOARD = new char[N][M];
        for (int i=0; i<N; i++)
            BOARD[i] = READER.readLine().toCharArray();
        int cnt = go(0, new Stack<>());
        System.out.println(cnt);
    }
    
    private static int go(int index, Stack<Point> select) {
        if (select.size() == 2) return calc(select);
        if (index == N * M) return 0;
        Point p = new Point(index / M, index % M);
        if (BOARD[p.x][p.y] != '#') 
            return go(index + 1, select);
        else {
            int cnt = 0;
            int size = 0;
            Set<Point> visit = new HashSet<>();
            while (true) {
                Point[] crosses = new Point[]{ 
                        new Point(p.x - size, p.y),
                        new Point(p.x + size, p.y),
                        new Point(p.x, p.y - size),
                        new Point(p.x, p.y + size)};
                boolean ok = true;
                for (int i=0; i<crosses.length; i++)
                    if (!crosses[i].valid() 
                            || BOARD[crosses[i].x][crosses[i].y] != '#') {
                        ok = false;
                        break;
                    }
                if (!ok)
                    break;
                else {
                    p.size = size;
                    select.push(p);
                    BOARD[p.x][p.y] = '.';
                    for (Point c: crosses) {
                        BOARD[c.x][c.y] = '.';
                        visit.add(c);
                    }
                    cnt = Math.max(go(index + 1, select), cnt);
                    select.pop();
                    size++;
                }
            }
            for (Point w: visit)    //cleanup
                BOARD[w.x][w.y] = '#';
            return Math.max(cnt, go(index + 1, select));
        }
    }

    private static int calc(Stack<Point> stack) {
        return stack.stream().mapToInt(p -> p.size * 4 + 1).reduce((a, b) -> a * b).getAsInt();
    }

    private static class Point {
        private final int x, y;
        private int size;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean valid() {
            return x >= 0 && x < N && y >= 0 && y < M;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
