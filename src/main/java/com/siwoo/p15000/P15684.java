package com.siwoo.p15000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//9% - backtracking need
@Used(algorithm = Algorithm.BACK_TRACKING)
public class P15684 {
    private static BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, H, ROW;
    private static boolean[][] FIXED_CONNECTIONS;
    
    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(READER.readLine());
        N = Integer.parseInt(token.nextToken());
        ROW = N - 1;
        M = Integer.parseInt(token.nextToken());
        H = Integer.parseInt(token.nextToken());
        FIXED_CONNECTIONS = new boolean[H*N][H*N];
        for (int i=0; i<M; i++) {
            token = new StringTokenizer(READER.readLine());
            int x = Integer.parseInt(token.nextToken()) - 1,
                    y = Integer.parseInt(token.nextToken()) - 1;
            FIXED_CONNECTIONS[x][y] = true;
        }
        if (M == 0) {
            System.out.println(0);
            return;
        }
        int x = select(0, new Stack<>());
        System.out.println(x);
    }

    private static int select(int index, Stack<Connection> select) {
        if (select.size() == 3) return calc(select);
        int x = calc(select);
        if (x == 0) return x;
        for (int i=index; i<H*ROW; i++) {
            Connection c = new Connection(i / ROW, i % ROW);
            if (FIXED_CONNECTIONS[c.x][c.y]) continue;
            Connection prev = c.y == 0 ? null: new Connection(c.x, c.y - 1);
            Connection next = c.y+1 == ROW ? null: new Connection(c.x, c.y + 1);
            //if prev, and next is not connection
            if ((prev == null || !FIXED_CONNECTIONS[prev.x][prev.y]) 
                    && (next == null || !FIXED_CONNECTIONS[next.x][next.y])) {   
                select.add(c);
                FIXED_CONNECTIONS[c.x][c.y] = true;
                int nextIndex = c.y+1 == ROW? i+1: i+2; //last connection need to go next x+1
                int t = select(nextIndex, select);
                if (t != -1 && (x == -1 || t < x))
                    x = t;
                select.pop();
                FIXED_CONNECTIONS[c.x][c.y] = false;
                if (x == 0) return x;   //back tracking
            }
        }
        return x;
    }

    private static int calc(Stack<Connection> select) {
        boolean ok = true;
        for (int i=0; i<N; i++) {
            int k = i;
            for (int j=0; j<H; j++) {
                int t = k;
                Connection prev = new Connection(j, t-1);
                if (prev.y >= 0 && FIXED_CONNECTIONS[prev.x][prev.y])
                    k--;
                Connection next = new Connection(j, t);
                if (FIXED_CONNECTIONS[next.x][next.y])
                    k++;
            }
            if (i != k) {
                ok = false;
                break;
            }
        }
        return !ok? -1: select.size();
    }


    private static class Connection {
        private final int x, y;

        public Connection(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public int index() {
            return x * N + y;
        }
    }
}
