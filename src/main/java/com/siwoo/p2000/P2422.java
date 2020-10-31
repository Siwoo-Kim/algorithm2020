package com.siwoo.p2000;

import com.siwoo.util.Algorithm;
import com.siwoo.util.Used;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

@Used(algorithm = Algorithm.COMBINATION)
public class P2422 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, CNT;
    private static boolean[][] pairs;

    public static void main(String[] args) throws IOException {
        StringTokenizer token = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        pairs = new boolean[N+1][N+1];
        for (int i=0; i<M; i++) {
            token = new StringTokenizer(reader.readLine());
            int x = Integer.parseInt(token.nextToken()),
                    y = Integer.parseInt(token.nextToken());
            pairs[x][y] = pairs[y][x] = true;
        }
        int x = go(1, new Stack<>());
        System.out.println(x);
    }

    private static int go(int index, Stack<Integer> stack) {
        if (stack.size() == 3) {
            Integer[] select = stack.toArray(new Integer[0]);
            return pairs[select[0]][select[1]]
                    | pairs[select[0]][select[2]]
                    | pairs[select[1]][select[2]] ? 0: 1;
        }
        if (index > N) return 0;
        stack.push(index);
        int x = go(index+1, stack);
        stack.pop();
        return x + go(index + 1, stack);
    }
}
