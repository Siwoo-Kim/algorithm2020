package com.siwoo.p9000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Used(algorithm = Algorithm.BFS)
public class P9328 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static char[][] BOARD;
    private static int[][] D = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static int N, M;
    private static char DOC = '$', WALL = '*';
    
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(reader.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<T; i++) {
            StringTokenizer token = new StringTokenizer(reader.readLine());
            N = Integer.parseInt(token.nextToken()) + 2;
            M = Integer.parseInt(token.nextToken()) + 2;
            BOARD = new char[N][M];
            for (int j=1; j<N-1; j++) {
                String line = reader.readLine();
                for (int k=1; k<M-1; k++)
                    BOARD[j][k] = line.charAt(k-1);
            }
            boolean[] key = new boolean[26];
            String s = reader.readLine();
            if (!s.equals("0")) {
                for (int j = 0; j < s.length(); j++)
                    key[s.charAt(j) - 'a'] = true;
            }
            int x = getDoc(BOARD, N, M, key);
            sb.append(x).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int getDoc(char[][] BOARD, int N, int M, boolean[] keys) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0));
        boolean[][] visit = new boolean[N][M];
        visit[0][0] = true;
        Map<Integer, Stack<Point>> waitingDoors = new HashMap<>();
        int cnt = 0;
        while (!q.isEmpty()) {
            Point v = q.poll();
            if (BOARD[v.x][v.y] == DOC)
                cnt++;
            for (int[] d: D) {
                Point w = new Point(v.x + d[0], v.y + d[1]);
                if (!w.valid(N, M)) continue;
                if (visit[w.x][w.y]) continue;
                char type = BOARD[w.x][w.y];
                if (type == WALL) continue;
                visit[w.x][w.y] = true;
                if (type == '.' || type == '\0' || type == DOC || 
                        (isKey(type) && keys[type-'a'])) {
                    q.add(w);
                } else {
                    if (isDoor(type)) {
                        //check door can be open
                        if (hasKey(keys, type)) {
                            q.add(w);
                        } else {
                            waitingDoors.putIfAbsent(type-'A', new Stack<>());
                            waitingDoors.get(type-'A').add(w);
                        }
                    } else if (isKey(type) && !keys[type-'a']) {
                        //add all waiting doors in queue
                        if (waitingDoors.containsKey(type-'a')) {
                            Stack<Point> stack = waitingDoors.get(type - 'a');
                            while (!stack.isEmpty())
                                q.add(stack.pop());
                        }
                        q.add(w);
                        keys[type-'a'] = true;
                    }
                }
            }
        }
        return cnt;
    }

    private static boolean isKey(char type) {
        return type >= 'a' && type <= 'z';
    }

    private static boolean isDoor(char type) {
        return type >= 'A' && type <= 'Z';
    }

    private static boolean hasKey(boolean[] keys, int door) {
        return keys[door-'A'];
    }

    private static class Point {
        final int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean valid(int N, int M) {
            return x >= 0 && x < N && y >= 0 && y < M;
        }

        @Override
        public String toString() {
            return "{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
